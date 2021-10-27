package com.coopezz.cpzedit.services;

import com.coopezz.cpzedit.models.entity.Post;
import com.coopezz.cpzedit.models.entity.User;
import com.coopezz.cpzedit.repositories.PostRepo;
import com.coopezz.cpzedit.repositories.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends SaveService {

    private final BCryptPasswordEncoder bCryptEncoder;

    public UserService(PostRepo postRepo, UserRepo userRepo, BCryptPasswordEncoder bCryptEncoder) {
        super(postRepo, userRepo);
        this.bCryptEncoder = bCryptEncoder;
    }

    public User registerUser(String nickname, String password) {
        User user = new User();
        user.setNickname(nickname);
        user.setPassword(password);
        user.setRole("Member");
        userRepo.save(user);
        return user;
    }

    public void assignPost(User user, Post post) {
        post.setUser(user);
        userRepo.save(user);
        postRepo.save(post);
    }

    public List<User> findAll () {
        return userRepo.findAll();
    }

    public User findById (Long id) {
        return userRepo.findUserById(id);
    }

    public User findByNickname (String nickname) {return userRepo.findUserByNickname(nickname); }

    public void save (User user) {
        userRepo.save(user);
    }

    public boolean passChecker (String rawPassword, String savedPassword) {
        return rawPassword.equals(savedPassword);
    }

    public String encode (String password) {
        return bCryptEncoder.encode(password);
    }
}
