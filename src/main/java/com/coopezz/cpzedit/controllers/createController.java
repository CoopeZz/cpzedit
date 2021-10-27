package com.coopezz.cpzedit.controllers;

import com.coopezz.cpzedit.services.PostService;
import com.coopezz.cpzedit.services.SessionService;
import com.coopezz.cpzedit.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class createController {

    PostService postSVC;
    SessionService sessionSVC;
    UserService userSVC;

    public createController (PostService postSVC, SessionService sessionSVC, UserService userSVC) {
        this.postSVC = postSVC;
        this.sessionSVC = sessionSVC;
        this.userSVC = userSVC;
    }

    @GetMapping("/create")
    public String getRegisterPage (Model model) {
        if (sessionSVC.getLoggedUser() == null || "".equals(sessionSVC.getLoggedUser().getNickname())) {
            return "redirect:/login";
        } else {
            model.addAttribute("loggedUser", sessionSVC.getLoggedUser());
            model.addAttribute("loginStatus",1);
            model.addAttribute("modStatus", 0);
        }
        return "/createPage";
    }

    @PostMapping("/submitPost")
    public String postRegistration (@RequestParam String content, RedirectAttributes redir) {
        if (content.equals("")) {
            redir.addFlashAttribute("errorStatus", 1);
            return "redirect:create";
        }
        postSVC.createPost(content, userSVC.findById(sessionSVC.getLoggedUser().getId()));
        return "redirect:/";
    }
}