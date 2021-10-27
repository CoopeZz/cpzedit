package com.coopezz.cpzedit.controllers;

import com.coopezz.cpzedit.services.SessionService;
import com.coopezz.cpzedit.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class registerController {

    UserService userSVC;
    SessionService sessionSVC;

    public registerController (UserService userSVC, SessionService sessionSVC) {
        this.userSVC = userSVC;
        this.sessionSVC = sessionSVC;
    }

    @GetMapping("/register")
    public String getRegisterPage (Model model) {
        if (sessionSVC.getLoggedUser() == null || "".equals(sessionSVC.getLoggedUser().getNickname())) {
            sessionSVC.setLoggedUser(sessionSVC.setDummyUser());
            model.addAttribute("loggedUser", sessionSVC.getLoggedUser());
            model.addAttribute("loginStatus",0);
            model.addAttribute("modStatus", 0);
        } else {
            model.addAttribute("loggedUser", sessionSVC.getLoggedUser());
            model.addAttribute("loginStatus",1);
            model.addAttribute("modStatus", 0);
        }
        return "/registerPage";
    }

    @PostMapping("/register")
    public String postRegistration (@RequestParam String login, @RequestParam String password, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("login", login);
        redirectAttributes.addAttribute("password", password);
        userSVC.registerUser(login, password);
        return "redirect:/";
    }
}