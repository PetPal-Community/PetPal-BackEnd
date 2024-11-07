package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.PaymentCaptureResponse;
import com.ingsw.petpal.dto.PaymentOrderResponse;
import jakarta.mail.MessagingException;

public interface CheckoutService {

    PaymentOrderResponse createPayment(Integer invoiceId, String returnUrl, String cancelUrl) throws MessagingException;

    PaymentCaptureResponse capturePayment(String orderId) throws MessagingException;
}
