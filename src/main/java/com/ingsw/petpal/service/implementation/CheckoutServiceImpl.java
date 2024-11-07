package com.ingsw.petpal.service.implementation;

import com.ingsw.petpal.dto.PagosDTO;
import com.ingsw.petpal.dto.PagosDetails;
import com.ingsw.petpal.dto.PaymentCaptureResponse;
import com.ingsw.petpal.dto.PaymentOrderResponse;
import com.ingsw.petpal.integration.email.dto.Mail;
import com.ingsw.petpal.integration.email.service.EmailService;
import com.ingsw.petpal.integration.paypal.dto.OrderCaptureResponse;
import com.ingsw.petpal.integration.paypal.dto.OrderResponse;
import com.ingsw.petpal.integration.paypal.service.PayPalService;
import com.ingsw.petpal.service.CheckoutService;
import com.ingsw.petpal.service.PagosService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final PayPalService payPalService;
    private final PagosService pagosService;
    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Override
    public PaymentOrderResponse createPayment(Integer invoiceId, String returnUrl, String cancelUrl) {
        OrderResponse orderResponse = payPalService.createOrder(invoiceId, returnUrl, cancelUrl);

        String paypalUrl = orderResponse
                .getLinks()
                .stream()
                .filter(link -> link.getRel().equals("approve"))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getHref();

        return new PaymentOrderResponse(paypalUrl);
    }

    @Override
    public PaymentCaptureResponse capturePayment(String orderId) throws MessagingException {
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();

            PagosDetails invoiceDetailsDTO = pagosService.confirmPurchase(Integer.parseInt(purchaseIdStr));
            paypalCaptureResponse.setPurchaseId(invoiceDetailsDTO.getId());

            sendPurchaseConfirmationEmail(invoiceDetailsDTO);
        }

        return paypalCaptureResponse;
    }

    private void sendPurchaseConfirmationEmail(PagosDetails pagosDetails) throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();



        Map<String, Object> model = new HashMap<>();
        model.put("user", userEmail);
        model.put("total", pagosDetails.getValorPago());
        model.put("OrderURL", "https://localhost:4200/order/" + pagosDetails.getId());
        Mail mail  = emailService.createMail(
                userEmail,
                "Confirmacion de compra",
                model,
                mailFrom
        );

         emailService.sendEmail(mail, "email/purchase-confirmation-template");
    }

}


