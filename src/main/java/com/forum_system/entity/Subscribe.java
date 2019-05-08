package com.forum_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Subscribe {
    private Integer themeId;
    private Integer userId;
}
