package com.hgrranzi.visaland.api.controller;

import com.hgrranzi.visaland.logging.LogUserAction;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @LogUserAction(description = "Redirecting to error page by user")
    public String handleError(HttpServletRequest request, Model model) {

        model.addAttribute("statusCode", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));

        return "error_page";
    }

}

