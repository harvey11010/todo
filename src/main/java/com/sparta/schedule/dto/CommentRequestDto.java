package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    private Long id;
    private String comment;
    private Long userId;
    private Long scheduleId;
}
