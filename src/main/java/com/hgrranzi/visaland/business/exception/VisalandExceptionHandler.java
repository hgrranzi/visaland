package com.hgrranzi.visaland.business.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VisalandExceptionHandler {

    @ExceptionHandler(VisalandException.class)
    public String handleVisalandException(VisalandException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_page";
    }

}

