package com.example.demo.controller;


import com.example.demo.model.HotelManager;
import com.example.demo.model.HotelManagerSession;
import com.example.demo.model.LoginModel;
import com.example.demo.repository.HotelManagerRepository;
import com.example.demo.repository.HotelManagerSessionRepository;
import com.example.demo.service.UtilityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/hotel")
public class AuthController {
    private final HotelManagerRepository hotelManagerRepository;
    private final HotelManagerSessionRepository hotelManagerSessionRepository;
    private final UtilityService utilityService;
    public AuthController(HotelManagerRepository hotelManagerRepository, UtilityService utilityService, HotelManagerSessionRepository hotelManagerSessionRepository){
        this.hotelManagerRepository = hotelManagerRepository;
        this.hotelManagerSessionRepository = hotelManagerSessionRepository;
        this.utilityService = utilityService;
    }



    @GetMapping("/registerHotelManager")
    public String getRegister(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(utilityService.isUserLoggedIn(request.getCookies())){
            response.sendRedirect("/dashboard");
            return null;
        }
        model.addAttribute("user", new HotelManager());
        return  "register";
    }

    @PostMapping("/registerHotelManager")
    public String postRegister(@ModelAttribute HotelManager hotelManager, Model model){
        HotelManager existingHotelManager = this.hotelManagerRepository.findByEmail(hotelManager.getEmail());
        if(existingHotelManager != null){
            model.addAttribute("user", hotelManager);
            model.addAttribute("eroor", "The email is already on use!");
            return "register";
        }
        this.hotelManagerRepository.save(hotelManager);
        return "loginNewUser";
    }

    @GetMapping("/login")
    public String getHotelLogin(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(utilityService.isUserLoggedIn(request.getCookies())){
            response.sendRedirect("/dashboard");
            return null;
        }

        model.addAttribute("loginModel", new LoginModel());
        model.addAttribute("loginAction", "/hotel/login");
        return "login";
    }

    @PostMapping("/login")
    public String postHotelLogin(@ModelAttribute LoginModel loginModel, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HotelManager loggedManager = this.hotelManagerRepository.findByEmailAndPassword(loginModel.getEmail(), loginModel.getPassword());

        if(loggedManager == null){
            model.addAttribute("error", "Wrong email or password (or both)");
            model.addAttribute("loginModel", new LoginModel());
            model.addAttribute("loginAction", "/hotel/login");
            return "login";
        }
        Cookie cookie = new Cookie("logged_in", "true");
        cookie.setPath("/");
        response.addCookie(cookie);
        HotelManagerSession hotelManagerSession = new HotelManagerSession();
        hotelManagerSession.setManager(loggedManager);
        hotelManagerSession.setSessionHashCode(utilityService.generateRandomString(30));
        hotelManagerSession.setIpAddress(request.getRemoteAddr());
        hotelManagerSessionRepository.save(hotelManagerSession);
        Cookie sessionCookie = new Cookie("session_id", hotelManagerSession.getSessionHashCode());
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
        response.sendRedirect("/dashboard");
        return null;
    }

}
