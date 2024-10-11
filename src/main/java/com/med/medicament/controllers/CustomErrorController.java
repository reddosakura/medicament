package com.med.medicament.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Arrays;

@Controller
public class CustomErrorController extends AbstractErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        System.out.println();
        model.addAttribute("exception", Arrays.toString(errorAttributes.getError(servletWebRequest).getStackTrace()).replace(", ", "\n"));
        model.addAttribute("message", errorAttributes.getError(servletWebRequest).getMessage());
        model.addAttribute("statusCode", response.getStatus());
        return "error";
    }
}