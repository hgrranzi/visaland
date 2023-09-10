package com.hgrranzi.visaland.api.controller;

import com.hgrranzi.visaland.api.dto.ApplicantDto;
import com.hgrranzi.visaland.business.service.UserService;
import com.hgrranzi.visaland.logging.LogUserAction;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/applicant")
public class ApplicantController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('APPLICANT')")
    @LogUserAction(description = "Request of applicant page by user")
    public String showApplicantPage(Model model, Authentication authentication) {
        ApplicantDto applicant = userService.createApplicantDtoByUsername(authentication.getName());

        model.addAttribute("applicant", applicant);
        return "applicant_page";
    }

    @GetMapping("/update")
    @LogUserAction(description = "Request of update info page by user")
    public String showUpdateClientForm(Model model, Authentication authentication) {
        ApplicantDto applicantDto = userService.createApplicantDtoByUsername(authentication.getName());
        model.addAttribute("applicantDto", applicantDto);

        return "update_info_page";
    }

    @PostMapping("/update")
    @LogUserAction(description = "Request to update applicant info by user")
    public String updateClient(@ModelAttribute("updateClientDto") @Valid ApplicantDto applicantDto,
                               BindingResult bindingResult,
                               Authentication authentication,
                               Model model,
                               HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("applicantDto", applicantDto);
            model.addAttribute("org.springframework.validation.BindingResult.applicantDto", bindingResult);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "update_info_page";
        }
        userService.updateApplicantProfileForUserWithUsername(applicantDto, authentication.getName());

        return "redirect:/applicant";
    }

}