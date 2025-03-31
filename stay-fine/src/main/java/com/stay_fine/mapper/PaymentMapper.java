package com.stay_fine.mapper;

import com.stay_fine.controller.request.PaymentRequest;
import com.stay_fine.controller.response.PaymentResponse;
import com.stay_fine.enums.PaymentType;
import com.stay_fine.model.entity.Payment;

public class PaymentMapper {

    public static Payment paymentRequestToEntity(PaymentRequest request) {
        return Payment.builder()
                .description(request.getDescription())
                .tipo(PaymentType.valueOf(request.getTipo()))
                .build();
    }

    public static PaymentResponse paymentEntityToResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .description(payment.getDescription())
                .registrationDate(payment.getRegistrationDate())
                .updateDate(payment.getUpdateDate())
                .status(payment.getStatus().name())
                .tipo(payment.getTipo().name())
                .build();
    }
}
