package com.hgrranzi.visaland.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class VisalandAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("APPLICANT")) {
                response.sendRedirect("/applicant");
                return;
            } else if (authority.getAuthority().equals("CONSUL")) {
                response.sendRedirect("/consul");
                return;
            } else if (authority.getAuthority().equals("ADMIN")) {
                response.sendRedirect("/admin");
                return;
            }
        }
    }
}
