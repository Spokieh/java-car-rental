package com.beadando.beadando.controller;

import com.beadando.beadando.service.PDFGeneratorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PDFGeneratorController {
    private final PDFGeneratorService pdfGeneratorService;

    public PDFGeneratorController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate")
    @PreAuthorize("hasAuthority('MAINTAINER') or hasAuthority('ADMIN') or hasAuthority('USER')")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime+ ".pdf";
        response.setHeader(headerKey,headerValue);

        this.pdfGeneratorService.export(response);
    }


}
