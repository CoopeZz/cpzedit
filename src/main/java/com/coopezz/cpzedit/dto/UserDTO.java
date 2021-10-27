package com.coopezz.cpzedit.dto;

import com.coopezz.cpzedit.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private long ID;
    private String nickname;
    private int posts;

    public static UserDTO buildUserDTO (User user) {
        return new UserDTO(user.getId(), user.getNickname(), user.getPostCount());
    }
}