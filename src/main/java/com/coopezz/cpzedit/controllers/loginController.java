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
public class loginController {

    UserService userSVC;
    SessionService sessionSVC;

    public loginController (UserService userSVC, SessionService sessionSVC) {
        this.userSVC = userSVC;
        this.sessionSVC = sessionSVC;
    }

    @GetMapping("/login")
    public String getLoginPage (Model model) {
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
        return "/loginPage";
    }

    @PostMapping("/loginForm")
    public String postLogin (@RequestParam String login, @RequestParam String password, RedirectAttributes redir) {
        if (login.equals("")) {
            redir.addFlashAttribute("errorStatus", 4);
            return "redirect:login";
        }

        if (password.equals("")) {
            redir.addFlashAttribute("errorStatus", 3);
            return "redirect:login";
        }

        if (userSVC.findByNickname(login) == null) {
            redir.addFlashAttribute("errorStatus", 1);
            return "redirect:login";
            }

        if (userSVC.passChecker(password, userSVC.findByNickname(login).getPassword())) {
            sessionSVC.setLoggedUser(userSVC.findByNickname(login));
            System.out.println("Everything is ok somehow");
            return "redirect:/";
        } else {
            redir.addFlashAttribute("errorStatus", 2);
        }
        return "redirect:login";
    }
}