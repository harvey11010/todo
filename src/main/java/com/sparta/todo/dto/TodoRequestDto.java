package com.sparta.todo.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String todotitle;
    private String todocontent;
    private String writer;
    private String password;
}