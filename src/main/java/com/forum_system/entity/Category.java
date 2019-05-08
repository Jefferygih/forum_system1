package com.forum_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class Category implements Serializable {
    private Integer id;
    private String title;
    private String content;
    private Integer moderatorId;
    private String status;
    private String color;
}
