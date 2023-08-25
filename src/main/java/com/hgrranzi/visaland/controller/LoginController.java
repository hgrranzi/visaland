package com.hgrranzi.visaland.controller;

import com.hgrranzi.visaland.dto.ApplicantDto;
import com.hgrranzi.visaland.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model, Authentication authentication) {
        model.addAttribute("loggedInMessage", null);

        return "login_page";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("applicantDto", new ApplicantDto());
        return "registration_page";
    }

    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("applicantDto") ApplicantDto applicantDto,
                                      BindingResult result, HttpServletResponse response) {
        if (result.hasErrors() || userService.existsByUniqueData(applicantDto, result)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "registration_page";
        }

        userService.registerUserAsApplicant(applicantDto);

        return "redirect:/login";
    }
}
