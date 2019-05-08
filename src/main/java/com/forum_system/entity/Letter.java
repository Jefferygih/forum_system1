package com.forum_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class Letter {
    private Integer id;
    private Integer recipientId;
    private Integer senderId;
    private String sendContent;
    private Date sendTime;
    private String adminContent;
}
