package com.Attra.Payer.Infrastructure;

import android.app.Application;

import com.Attra.Payer.ServiceRequests.Module;
import com.squareup.otto.Bus;

public class PayerApp extends Application {

    private Bus bus;

    public PayerApp() {

        bus=new Bus();
        Module.register(this);
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Bus getBus() {
        return bus;
    }
}
