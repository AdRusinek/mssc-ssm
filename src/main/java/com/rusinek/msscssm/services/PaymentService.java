package com.rusinek.msscssm.services;

import com.rusinek.msscssm.domain.Payment;
import com.rusinek.msscssm.domain.PaymentEvent;
import com.rusinek.msscssm.domain.PaymentState;
import org.springframework.statemachine.StateMachine;

/**
 * Created by Adrian Rusinek on 07.04.2020
 **/
public interface PaymentService {

    Payment newPayment(Payment payment);

    StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);

    StateMachine<PaymentState, PaymentEvent> delineAuth(Long paymentId);
}
