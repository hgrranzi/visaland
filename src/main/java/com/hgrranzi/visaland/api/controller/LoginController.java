package com.hgrranzi.visaland.api.controller;

import com.hgrranzi.visaland.api.dto.ApplicantDto;
import com.hgrranzi.visaland.business.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
    public String processRegistration(@Valid @ModelAttribute("applicantDto") ApplicantDto applicantDto,
                                      BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors() || userService.existsByUniqueData(applicantDto, bindingResult)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "registration_page";
        }

        userService.registerUserAsApplicant(applicantDto);

        return "redirect:/login";
    }
}
