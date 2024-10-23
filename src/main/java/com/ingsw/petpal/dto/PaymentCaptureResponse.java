package com.ingsw.petpal.dto;

import lombok.Data;

@Data
public class PaymentCaptureResponse {
    private boolean completed;
    private Integer purchaseId;
}
