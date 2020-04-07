package com.rusinek.msscssm.config.actions;

import com.rusinek.msscssm.domain.PaymentEvent;
import com.rusinek.msscssm.domain.PaymentState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

/**
 * Created by Adrian Rusinek on 07.04.2020
 **/
@Component
public class AuthDeclinedAction implements Action<PaymentState, PaymentEvent> {
    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> context) {
        System.out.println("Sending Notification of Auth DECLINED");
    }
}