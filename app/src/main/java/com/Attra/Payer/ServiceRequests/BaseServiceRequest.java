package com.Attra.Payer.ServiceRequests;

import com.Attra.Payer.Infrastructure.PayerApp;
import com.squareup.otto.Bus;

public class BaseServiceRequest {

    protected PayerApp application;
    protected Bus bus;


    public BaseServiceRequest(PayerApp application) {
        this.application = application;
        bus=application.getBus();
        bus.register(this);
    }
}
