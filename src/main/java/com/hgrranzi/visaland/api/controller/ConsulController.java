package com.hgrranzi.visaland.api.controller;

import com.hgrranzi.visaland.api.dto.ApplicantInfoDto;
import com.hgrranzi.visaland.api.dto.ApplicationDto;
import com.hgrranzi.visaland.api.dto.ConsulDto;
import com.hgrranzi.visaland.business.service.ApplicationService;
import com.hgrranzi.visaland.business.service.UserService;
import com.hgrranzi.visaland.logging.LogUserAction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.hgrranzi.visaland.persistence.entity.AppStatus.OPEN;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consul")
public class ConsulController {

    private final UserService userService;

    private final ApplicationService applicationService;

    @GetMapping()
    @PreAuthorize("hasRole('CONSUL')")
    @LogUserAction(description = "Request of consul page by user")
    public String showConsulPage(Authentication authentication, Model model) {
        ConsulDto consul = userService.createConsulDtoByUsername(authentication.getName());

        model.addAttribute("consul", consul);
        return "consul_page";
    }

    @GetMapping("/open")
    @PreAuthorize("hasRole('CONSUL')")
    @LogUserAction(description = "Request of open applications page by user")
    public String showOpenApplicationsPage(Authentication authentication, Model model) {
        List<ApplicationDto> applications = applicationService.findAllApplicationsWithStatus(OPEN);

        model.addAttribute("applications", applications);
        return "open_applications_page";
    }

    @PostMapping("/processing")
    @PreAuthorize("hasRole('CONSUL')")
    @LogUserAction(description = "Request of processing applications page by user")
    public String showProcessingApplicationsPage(Authentication authentication,
                                                 @RequestParam("appId") Long appId,
                                                 Model model) {


        ApplicantInfoDto applicant = userService.findApplicantOfApplicationWithId(appId);
        System.err.println(applicant);

        model.addAttribute("application", applicant.currentApp());
        model.addAttribute("applicant", applicant);

        return "process_application_page";
    }

    @PostMapping("/resolve")
    @PreAuthorize("hasRole('CONSUL')")
    @LogUserAction(description = "Request of processing applications page by user")
    public String resolveApplication(Authentication authentication,
                                     @RequestParam("approved") Boolean approved,
                                     Model model) {
        return "resolved_applications_page";
    }


    @GetMapping("/resolved")
    @PreAuthorize("hasRole('CONSUL')")
    @LogUserAction(description = "Request of resolved applications page by user")
    public String showResolvedApplicationsPage(Authentication authentication, Model model) {
        return "";
    }
}
