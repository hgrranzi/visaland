package com.hgrranzi.visaland.controller;

import com.hgrranzi.visaland.entity.Country;
import com.hgrranzi.visaland.entity.VisaCategory;
import com.hgrranzi.visaland.repository.CountryRepository;
import com.hgrranzi.visaland.repository.VisaCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/visa")
public class VisaController {

    private final VisaCategoryRepository visaCategoryRepository;

    private final CountryRepository countryRepository;

    @GetMapping("/category")
    @PreAuthorize("hasRole('APPLICANT')")
    public String showSelectCategoryPage(Model model, Authentication authentication) {
        List<VisaCategory> categories = visaCategoryRepository.findAll().stream().toList();
        List<Country> country = countryRepository.findAll().stream().toList();
        model.addAttribute("countries", country);
        model.addAttribute("categories", categories);
        return "category_page";
    }
}
