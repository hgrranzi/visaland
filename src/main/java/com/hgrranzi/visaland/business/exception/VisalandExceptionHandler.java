package com.hgrranzi.visaland.business.exception;

import com.hgrranzi.visaland.logging.LogUserAction;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VisalandExceptionHandler {

    @ExceptionHandler(VisalandException.class)
    @LogUserAction(description = "Redirecting to error page by user")
    public String handleVisalandException(VisalandException ex, HttpServletResponse response, Model model) {
        response.setStatus(ex.getStatusCode().value());
        model.addAttribute("statusCode", ex.getStatusCode());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error_page";
    }

}

