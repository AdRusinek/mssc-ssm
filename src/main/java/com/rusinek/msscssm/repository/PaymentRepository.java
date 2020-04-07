package com.rusinek.msscssm.repository;

import com.rusinek.msscssm.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Adrian Rusinek on 07.04.2020
 **/
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
