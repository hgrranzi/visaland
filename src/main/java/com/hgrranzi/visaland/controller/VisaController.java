package com.hgrranzi.visaland.controller;

import com.hgrranzi.visaland.dto.ApplicationDto;
import com.hgrranzi.visaland.entity.Applicant;
import com.hgrranzi.visaland.entity.Country;
import com.hgrranzi.visaland.entity.VisaCategory;
import com.hgrranzi.visaland.repository.ApplicantRepository;
import com.hgrranzi.visaland.repository.CountryRepository;
import com.hgrranzi.visaland.repository.VisaCategoryRepository;
import com.hgrranzi.visaland.service.ApplicationService;
import com.hgrranzi.visaland.service.VisaService;
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

    private final VisaCategoryRepository visaCategoryRepository;

    private final CountryRepository countryRepository;

    private final ApplicantRepository applicantRepository;

    private final ApplicationService applicationService;

    private final VisaService visaService;

    @GetMapping("/category")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showSelectCategoryPage(Model model, Authentication authentication) {
        List<VisaCategory> categories = visaCategoryRepository.findAll().stream().toList();
        List<Country> country = countryRepository.findAll().stream().toList();
        model.addAttribute("countries", country);
        model.addAttribute("categories", categories);
        return "category_page";
    }

    @GetMapping("/select")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showApplyForVisaPage(@ModelAttribute(name = "category") String categoryName,
                                       @ModelAttribute(name = "country") String country, Model model) {
        model.addAttribute("applicationDto", ApplicationDto.builder()
                                                 .visaCategory(categoryName)
                                                 .country(country)
                                                 .build());
        return "apply_for_visa_page";
    }

    @PostMapping("/select")
    @PreAuthorize("hasRole('APPLICANT')")
    public String apply(@ModelAttribute("applicationDto") ApplicationDto applicationDto) {
        System.err.println(applicationDto);
        //check for errors
        //add application to db
        return "redirect:/visa/applications";
    }

    @GetMapping("/applications")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showApplicationsPage(Model model, Authentication authentication) {
        //check for errors
        Applicant applicant = applicantRepository.findByUserUsername(authentication.getName()).orElse(null);

        model.addAttribute("applications", applicationService.findAllApplicationsForApplicant(applicant));

        return "applications_page";
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showVisasPage(Model model, Authentication authentication) {
        //check for errors
        Applicant applicant = applicantRepository.findByUserUsername(authentication.getName()).orElse(null);

        model.addAttribute("visas", visaService.findAllVisasForApplicant(applicant));

        return "visas_page";
    }
}
