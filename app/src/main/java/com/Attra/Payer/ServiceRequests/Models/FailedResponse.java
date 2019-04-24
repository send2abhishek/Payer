package com.Attra.Payer.ServiceRequests.Models;

public class FailedResponse {

    private int statusCode;
    private String name;
    private String message;
    private String code;

    public FailedResponse(int statusCode, String name, String message, String code) {
        this.statusCode = statusCode;
        this.name = name;
        this.message = message;
        this.code = code;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
