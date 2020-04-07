package com.rusinek.msscssm.config.guards;

import com.rusinek.msscssm.domain.PaymentEvent;
import com.rusinek.msscssm.domain.PaymentState;
import com.rusinek.msscssm.services.PaymentServiceImpl;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

/**
 * Created by Adrian Rusinek on 07.04.2020
 **/
@Component
public class PaymentIdGuard implements Guard<PaymentState, PaymentEvent> {

    @Override
    public boolean evaluate(StateContext<PaymentState, PaymentEvent> context) {
        return context.getMessageHeader(PaymentServiceImpl.PAYMENT_ID_HEADER) != null;
    }
}
