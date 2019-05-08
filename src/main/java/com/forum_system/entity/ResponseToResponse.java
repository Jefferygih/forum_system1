package com.forum_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class ResponseToResponse {
    private Integer id;
    private Integer responseId;
    private Integer responseToresponseId;
    private Integer themeId;
    private Integer userId;
    private String content;
    private Date recoveryTime;
    private Integer goods;
    private String photo;

}
