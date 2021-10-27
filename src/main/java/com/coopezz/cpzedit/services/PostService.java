package com.coopezz.cpzedit.services;

import com.coopezz.cpzedit.models.entity.Post;
import com.coopezz.cpzedit.models.entity.User;
import com.coopezz.cpzedit.repositories.PostRepo;
import com.coopezz.cpzedit.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends SaveService{
    public PostService(PostRepo postRepo, UserRepo userRepo) {
        super(postRepo, userRepo);
    }

    public Post createPost(String content, User user) {
        Post post = new Post(content);
        post.setUser(user);
        user.increasePostCount();
        postRepo.save(post);
        return post;
    }

    public List<Post> findAll () {
        return postRepo.findAll();
    }

    public List<Post> findByUserID (Long Id) { return postRepo.findPostsByUserId(Id);}

    public void save (Post post) {
        postRepo.save(post);
    }
}
