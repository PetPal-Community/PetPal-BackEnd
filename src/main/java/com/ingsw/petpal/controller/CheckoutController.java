package com.ingsw.petpal.controller;

import com.ingsw.petpal.dto.PaymentCaptureResponse;
import com.ingsw.petpal.dto.PaymentOrderResponse;
import com.ingsw.petpal.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
@PreAuthorize("hasRole('CUSTOMER')")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping("/create")
    public ResponseEntity<PaymentOrderResponse> createPaymentOrder(
            @RequestParam Integer invoiceId,
            @RequestParam String returnUrl,
            @RequestParam String cancelUrl,
            @RequestParam(required = false, defaultValue = "paypal") String paymentProvider
    ) /* throws MessagingException */{
        PaymentOrderResponse response = checkoutService.createPayment(invoiceId, returnUrl, cancelUrl);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/capture")
    public ResponseEntity<PaymentCaptureResponse> capturePaymentOrder(
            @RequestParam String orderId,
            @RequestParam(required = false, defaultValue = "paypal") String paymentProvider
    ) /*throws MessagingException*/ {
        PaymentCaptureResponse response = checkoutService.capturePayment(orderId);

        if (response.isCompleted()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}