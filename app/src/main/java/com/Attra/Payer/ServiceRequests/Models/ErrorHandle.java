package com.Attra.Payer.ServiceRequests.Models;

public class ErrorHandle {

    private FailedResponse error;

    public ErrorHandle(FailedResponse error) {
        this.error = error;
    }

    public FailedResponse getError() {
        return error;
    }
}
