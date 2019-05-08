package com.forum_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String type;
    private String twoFactor;
    private boolean activated;
    private String activateCode;
    private String cookie;
    private String status;
    private String photo;
}
