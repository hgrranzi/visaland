package com.hgrranzi.visaland.controller;

import com.hgrranzi.visaland.dto.ApplicantDto;
import com.hgrranzi.visaland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/applicant")
public class ApplicantController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('APPLICANT')")
    public String showApplicantPage(Model model, Authentication authentication) {
        String username = authentication.getName();
        ApplicantDto applicant = userService.createApplicantDtoByUsername(username);
        model.addAttribute("applicant", applicant);
        return "applicant_page";
    }




}
