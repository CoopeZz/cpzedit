package com.coopezz.cpzedit.services;

import com.coopezz.cpzedit.repositories.PostRepo;
import com.coopezz.cpzedit.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveService {
    public final PostRepo postRepo;
    public final UserRepo userRepo;
}
