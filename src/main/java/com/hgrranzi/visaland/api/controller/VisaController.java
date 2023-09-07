package com.hgrranzi.visaland.api.controller;

import com.hgrranzi.visaland.api.dto.ApplicationDto;
import com.hgrranzi.visaland.api.dto.VisaDto;
import com.hgrranzi.visaland.business.service.ApplicationService;
import com.hgrranzi.visaland.business.service.VisaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visa")
public class VisaController {

    private final ApplicationService applicationService;

    private final VisaService visaService;

    @GetMapping("/category")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showSelectCategoryPage(Model model) {
        model.addAttribute("countries", visaService.findAllCountries());
        model.addAttribute("categories", visaService.findAllCategories());

        return "category_page";
    }

    @PostMapping("/select")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showApplyForVisaPage(@ModelAttribute(name = "category") String categoryName,
                                       @ModelAttribute(name = "country") String country, Model model) {
        model.addAttribute("applicationDto", ApplicationDto.builder()
                                                 .visaCategory(categoryName)
                                                 .country(country)
                                                 .build());
        return "apply_for_visa_page";
    }

    @PostMapping("/apply")
    @PreAuthorize("hasRole('APPLICANT')")
    public String apply(@ModelAttribute("applicationDto") ApplicationDto applicationDto, Authentication auth) {
        //check for errors

        applicationService.saveNewApplicationFromApplicantWithUsername(applicationDto, auth.getName());

        return "redirect:/visa/applications";
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showApplicationsPage(Model model, Authentication auth) {
        List<ApplicationDto> apps = applicationService.findAllApplicationsForApplicantWithUsername(auth.getName());
        model.addAttribute("applications", apps);

        return "applications_page";
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showVisasPage(Model model, Authentication auth) {
        List<VisaDto> visas = visaService.findAllVisasForApplicantWithUsername(auth.getName());
        model.addAttribute("visas", visas);

        return "visas_page";
    }
}
