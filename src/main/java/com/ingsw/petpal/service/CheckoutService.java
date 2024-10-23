package com.ingsw.petpal.service;

import com.ingsw.petpal.dto.PaymentCaptureResponse;
import com.ingsw.petpal.dto.PaymentOrderResponse;

public interface CheckoutService {

    PaymentOrderResponse createPayment(Integer invoiceId, String returnUrl, String cancelUrl);

    PaymentCaptureResponse capturePayment(String orderId);
}
