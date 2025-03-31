package com.stay_fine.service;

import com.stay_fine.enums.Error;
import com.stay_fine.enums.PaymentStatus;
import com.stay_fine.exception.NotFoundException;
import com.stay_fine.model.entity.Payment;
import com.stay_fine.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public Payment create(Payment payment) {

        Payment paymentBuilder = Payment.builder()
                .description(payment.getDescription())
                .tipo(payment.getTipo())
                .build();

        return paymentRepository.save(paymentBuilder);
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));
    }

    public Payment update(Long id, Payment payment) {
        Payment existsPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        existsPayment.setUpdateDate(LocalDateTime.now());
        paymentRequestToEntityDB(payment, existsPayment);
        return paymentRepository.save(existsPayment);
    }

    public void delete(Long id) {
        Payment existsPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        existsPayment.setStatus(PaymentStatus.EXCLUIDO);
        existsPayment.setUpdateDate(LocalDateTime.now());
        paymentRepository.save(existsPayment);
    }

    private void paymentRequestToEntityDB(Payment payment, Payment existsPayment) {
        if (payment.getDescription() != null && !Objects.equals(payment.getDescription(), existsPayment.getDescription())) {
            existsPayment.setDescription(payment.getDescription());
        }

        if (payment.getTipo() != null && !Objects.equals(payment.getTipo(), existsPayment.getTipo())) {
            existsPayment.setTipo(payment.getTipo());
        }
    }


}
