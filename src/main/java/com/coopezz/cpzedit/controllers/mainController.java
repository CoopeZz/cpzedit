package com.coopezz.cpzedit.controllers;
import com.coopezz.cpzedit.services.PostService;
import com.coopezz.cpzedit.services.SessionService;
import com.coopezz.cpzedit.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    PostService postSVC;
    UserService userSVC;
    SessionService sessionSVC;

    public mainController (PostService postSVC, UserService userSVC, SessionService sessionSVC) {
        this.postSVC = postSVC;
        this.userSVC = userSVC;
        this.sessionSVC = sessionSVC;
    }

    @GetMapping
    public String getHome () {
        return "redirect:home";
    }

    @GetMapping("/home")
    public String getHome (Model model) {
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

        model.addAttribute("svc_post_all", postSVC.findAll());
        return "homePage";
    }
}