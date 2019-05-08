package com.forum_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Response {
    private Integer id;
    private Integer themeId;
    private Integer responderId;
    private Date recoveryTime;
    private String content;
    private String photo;
    private Integer goods;

}
