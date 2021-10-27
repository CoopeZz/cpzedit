package com.coopezz.cpzedit.controllers;

import com.coopezz.cpzedit.services.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class logoutController {

    SessionService sessionSVC;

    public logoutController (SessionService sessionSVC) {
        this.sessionSVC = sessionSVC;
    }

    @GetMapping("/logout")
    public String logoutUser() {
        sessionSVC.setLoggedUser(sessionSVC.setDummyUser());
        return "redirect:/";
    }
}