package com.stay_fine.controller;

import com.stay_fine.controller.request.PaymentRequest;
import com.stay_fine.controller.response.PaymentResponse;
import com.stay_fine.mapper.PaymentMapper;
import com.stay_fine.model.entity.Payment;
import com.stay_fine.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> post(@RequestBody PaymentRequest request) {
        Payment paymentSaved = paymentService.create(PaymentMapper.paymentRequestToEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(PaymentMapper.paymentEntityToResponse(paymentSaved));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {
        List<Payment> paymentList = paymentService.findAll();

        List<PaymentResponse> responseList = paymentList.stream()
                .map(PaymentMapper::paymentEntityToResponse)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getById(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(PaymentMapper.paymentEntityToResponse(payment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> update(@PathVariable Long id, @RequestBody PaymentRequest request) {
        Payment payment = paymentService.update(id, PaymentMapper.paymentRequestToEntity(request));
        return ResponseEntity.status(HttpStatus.OK).body(PaymentMapper.paymentEntityToResponse(payment));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
