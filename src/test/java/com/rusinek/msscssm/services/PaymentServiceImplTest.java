package com.rusinek.msscssm.services;

import com.rusinek.msscssm.domain.Payment;
import com.rusinek.msscssm.domain.PaymentEvent;
import com.rusinek.msscssm.domain.PaymentState;
import com.rusinek.msscssm.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder().amount(new BigDecimal("12.99")).build();
    }

    @Transactional
    @Test
    void preAuth() {
        Payment savedPayment = paymentService.newPayment(this.payment);

        System.out.println("Should be NEW");
        System.out.println(savedPayment.getState());

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());

        Payment preAuthPayment = paymentRepository.getOne(savedPayment.getId());

        System.out.println("Should be PRE_AUTH");
        System.out.println(sm.getState().getId());

        System.out.println(preAuthPayment);
    }

    @Transactional
    @RepeatedTest(10)
    void Auth() {
        Payment savedPayment = paymentService.newPayment(this.payment);

        StateMachine<PaymentState, PaymentEvent> preAuthSM = paymentService.preAuth(savedPayment.getId());

        if (preAuthSM.getState().getId() == PaymentState.PRE_AUTH) {
            System.out.println("Payment is pre authorized");

            StateMachine<PaymentState, PaymentEvent> authSM = paymentService.authorizePayment(savedPayment.getId());

            System.out.println("result of auth: " + authSM.getState().getId());
        } else {
            System.out.println("Payment failed pre auth");
        }
    }
}