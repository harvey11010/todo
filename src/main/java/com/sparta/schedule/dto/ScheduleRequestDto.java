package com.sparta.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;
}
