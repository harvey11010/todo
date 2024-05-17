package com.sparta.todo.dto;

import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.Todo;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private Long id;
    private String todotitle;
    private String todocontent;
    private String writer;
    @Getter(value = AccessLevel.PRIVATE)
    private String password;
    private LocalDateTime datecreated;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.todotitle = todo.getTodotitle();
        this.todocontent = todo.getTodocontent();
        this.writer = todo.getWriter();
        this.password = todo.getPassword();
        this.datecreated = todo.getCreatedAt();
    }
}