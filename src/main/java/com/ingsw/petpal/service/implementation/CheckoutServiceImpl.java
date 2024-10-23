package com.ingsw.petpal.service.implementation;
/*
import com.ingsw.petpal.dto.PaymentCaptureResponse;
import com.ingsw.petpal.dto.PaymentOrderResponse;
import com.ingsw.petpal.integration.paypal.dto.OrderCaptureResponse;
import com.ingsw.petpal.integration.paypal.dto.OrderResponse;
import com.ingsw.petpal.integration.paypal.service.PayPalService;
import com.ingsw.petpal.service.CheckoutService;
import com.ingsw.petpal.service.PagosService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {
    /*
    private final PayPalService payPalService;
    private final PagosService pagosService;

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
    public PaymentCaptureResponse capturePayment(String orderId) {
        OrderCaptureResponse orderCaptureResponse = payPalService.captureOrder(orderId);
        boolean completed = orderCaptureResponse.getStatus().equals("COMPLETED");

        PaymentCaptureResponse paypalCaptureResponse = new PaymentCaptureResponse();
        paypalCaptureResponse.setCompleted(completed);

        if (completed) {
            String purchaseIdStr = orderCaptureResponse.getPurchaseUnits().get(0).getReferenceId();

            PagosService invoiceDetailsDTO = invoiceService.confirmPurchase(Integer.parseInt(purchaseIdStr));
            paypalCaptureResponse.setPurchaseId(invoiceDetailsDTO.getId());

            //sendPurchaseConfirmationEmail(purchaseDTO);
        }

        return paypalCaptureResponse;
    }


}

 */
