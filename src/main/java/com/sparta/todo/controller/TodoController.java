package com.sparta.todo.controller;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todo")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.createTodo(requestDto);
    }

    @GetMapping("/todo/{id}")
    public List<TodoResponseDto> getIdTodo(@PathVariable Long id) {
        return todoService.getIdTodo(id);
    }

    @GetMapping("/todo")
    public List<TodoResponseDto> getTodo() {
        return todoService.getTodo();
    }

    @PutMapping("/todo/{id}")
    public Long updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }

    @DeleteMapping("/todo/{id}")
    public Long deleteTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.deleteTodo(id, requestDto);
    }

}