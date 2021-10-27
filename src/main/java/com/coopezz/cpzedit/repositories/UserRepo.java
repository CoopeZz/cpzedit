package com.coopezz.cpzedit.repositories;

import com.coopezz.cpzedit.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByNickname (String nickname);
}
