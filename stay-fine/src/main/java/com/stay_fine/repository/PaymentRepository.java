package com.stay_fine.repository;

import com.stay_fine.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.id = :id and p.status = 'ATIVO' ")
    Payment findByIdActivePayment(Long id);
}
