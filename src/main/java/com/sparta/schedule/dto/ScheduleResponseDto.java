package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Schedule;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    @Getter(value = AccessLevel.PRIVATE)
    private final String password;
    private final LocalDateTime createdAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.writer = schedule.getWriter();
        this.password = schedule.getPassword();
        this.createdAt = schedule.getCreatedAt();
    }
}
