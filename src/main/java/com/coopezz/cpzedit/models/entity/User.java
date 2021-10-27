package com.coopezz.cpzedit.models.entity;

import com.coopezz.cpzedit.services.PostService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Column
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @CreationTimestamp
    private LocalDateTime created;

    @Column
    private String nickname;

    @Column
    private String password;

    @Column
    private String role;

    @Column
    private int postCount;

    public User (String nickname) {this.nickname = nickname;}

    public void increasePostCount () {
        this.postCount++;
    }
}
