package com.example.demo.service;


import com.example.demo.model.Manager;
import com.example.demo.model.ManagerSession;
import com.example.demo.repository.ManagerRepository;

import com.example.demo.repository.ManagerSessionRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UtilityService {

    private ManagerRepository managerRepository;
    private ManagerSessionRepository managerSessionRepository;

    public UtilityService(ManagerRepository managerRepository, ManagerSessionRepository managerSessionRepository) {
        this.managerRepository = managerRepository;
        this.managerSessionRepository = managerSessionRepository;
    }

    public boolean isUserLoggedIn(Cookie[] cookies) {
        return this.getLoggedInUser(cookies) != null;
    }

    private Manager getLoggedInUser(Cookie[] cookies) {
        if (cookies == null) {
            return null;
        }

        Optional<Cookie> cookieOptional = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("logged_in")
                        && c.getValue().equals("true"))
                .findFirst();

        if (!cookieOptional.isPresent()) {
            return null;
        }

        Optional<Cookie> sessionCookieOptional = Arrays.stream(cookies).filter(c -> c.getName().equals("session_id")).findFirst();
        if(!sessionCookieOptional.isPresent()){
            return null;
        }

        ManagerSession managerSession = this.managerSessionRepository.findBySessionHashCode(sessionCookieOptional.get().getValue());
        if(managerSession == null){
            return null;
        }

    return managerSession.getManager();
    };

}
