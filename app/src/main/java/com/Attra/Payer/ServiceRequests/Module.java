package com.Attra.Payer.ServiceRequests;

import com.Attra.Payer.Infrastructure.PayerApp;

public class Module {

    public static void register(PayerApp application){


        new LiveAccountService(application);
    }
}
