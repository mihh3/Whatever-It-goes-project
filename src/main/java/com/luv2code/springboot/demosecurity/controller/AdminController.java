package com.luv2code.springboot.demosecurity.controller;

import com.luv2code.springboot.demosecurity.Dtos.UserLastLoginDto;
import com.luv2code.springboot.demosecurity.Repositories.LoginActivityRepository;
import com.luv2code.springboot.demosecurity.ServiceInterfaces.LoginActivityPdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;
import java.util.List;

@Controller
public class AdminController {

    private final LoginActivityRepository loginActivityRepository;
    private final LoginActivityPdfService pdfService;


    public AdminController(LoginActivityRepository loginActivityRepository, LoginActivityPdfService pdfService) {
        this.loginActivityRepository = loginActivityRepository;
        this.pdfService = pdfService;
    }

    @GetMapping("/last-logins")
    public String showLastLogins(Model model) {
        List<UserLastLoginDto> logins = loginActivityRepository.findLastLoginTimes();
        model.addAttribute("logins", logins);
        return "last-logins";
    }

    @PreAuthorize("hasRole('ADMIN') or #username == authentication.name")
    @GetMapping(
            value = "/api/logins/{username}/export/pdf",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public void exportUserLoginsPdf(@PathVariable String username, HttpServletResponse response) throws Exception {

        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + username + "-logins.pdf");
        pdfService.exportUserLoginsPdf(
                username,
                response.getOutputStream()
        );
    }
}
