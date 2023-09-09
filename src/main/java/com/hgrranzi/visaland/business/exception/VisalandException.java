package com.hgrranzi.visaland.business.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

public class VisalandException extends HttpClientErrorException {
    public VisalandException(HttpStatusCode statusCode, String message) {
        super(statusCode, message);
    }

}
