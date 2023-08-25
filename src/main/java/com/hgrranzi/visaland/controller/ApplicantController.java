package com.hgrranzi.visaland.controller;

import com.hgrranzi.visaland.dto.ApplicantDto;
import com.hgrranzi.visaland.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public String showApplicantPage(Model model) {
        String username = "userok";
        ApplicantDto applicant = userService.createApplicantDtoByUsername(username);
        model.addAttribute("applicant", applicant);
        return "applicant_page";
    }




}
