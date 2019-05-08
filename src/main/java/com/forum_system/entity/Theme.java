package com.forum_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Theme {
    private Integer id;
    private Integer categoryId;
    private Integer creatorId;
    private String title;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date publishTime;
    private String photo;
    private String status;
    private String resNumber;
    private Integer goods;


}
