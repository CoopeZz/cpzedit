package com.coopezz.cpzedit.services;

import com.coopezz.cpzedit.models.entity.User;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
@Data
public class SessionService {

    private User loggedUser;

    public User setDummyUser () {
        User user = new User();
        user.setNickname("");
        return user;
    }
}
