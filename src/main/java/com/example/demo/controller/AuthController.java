package com.example.demo.controller;


import com.example.demo.repository.ManagerRepository;
import com.example.demo.service.UtilityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {
    private final ManagerRepository managerRepository;
    private final UtilityService utilityService;
    public AuthController(ManagerRepository managerRepository, UtilityService utilityService){
        this.managerRepository = managerRepository;
        this.utilityService = utilityService;
    }



    @GetMapping("/register-hotel-manager")
    public String getRegister(Model model, HttpServletRequest request, HttpServletResponse response){
//        if(utilityService.isLoggedIn())
        return  "register";
    }

}
