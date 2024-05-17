package com.sparta.todo.entity;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "todo")
@NoArgsConstructor
public class Todo extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "todotitle", nullable = false, length = 200)
    private String todotitle;
    @Column(name = "todocontent", nullable = false, length = 1000)
    private String todocontent;
    @Column(name = "writer", nullable = false)
    private String writer;
    @Column(name = "password", nullable = false)
    private String password;

    public Todo(TodoRequestDto requestDto) {
        this.todotitle = requestDto.getTodotitle();
        this.todocontent = requestDto.getTodocontent();
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
    }

    public void update(TodoRequestDto requestDto) {
        this.todotitle = requestDto.getTodotitle();
        this.todocontent = requestDto.getTodocontent();
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
    }
}