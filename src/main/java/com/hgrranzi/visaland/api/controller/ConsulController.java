package com.hgrranzi.visaland.api.controller;

import com.hgrranzi.visaland.api.dto.ConsulDto;
import com.hgrranzi.visaland.business.service.UserService;
import com.hgrranzi.visaland.logging.LogUserAction;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/consul")
public class ConsulController {

    private final UserService userService;

    @GetMapping()
    @PreAuthorize("hasRole('CONSUL')")
    @LogUserAction(description = "Request of consul page by user")
    public String showConsulPage(Authentication authentication, Model model) {
        ConsulDto consul = userService.createConsulDtoByUsername(authentication.getName());

        model.addAttribute("consul", consul);
        return "consul_page";
    }
}
