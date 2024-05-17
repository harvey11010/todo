package com.sparta.todo.service;

import com.sparta.todo.dto.TodoRequestDto;
import com.sparta.todo.dto.TodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);

        Todo saveTodo = todoRepository.save(todo);

        return new TodoResponseDto(saveTodo);
    }

    public List<TodoResponseDto> getIdTodo(Long id) {
        return todoRepository.findAllByIdOrderByCreatedAtDesc(id).stream().map(TodoResponseDto::new).toList();
    }

    public List<TodoResponseDto> getTodo() {
        return todoRepository.findAllByOrderByCreatedAtDesc().stream().map(TodoResponseDto::new).toList();
    }

    @Transactional
    public Long updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = findTodoByIdAndCheckPassword(id, requestDto);

        todo.update(requestDto);

        return id;
    }

    public Long deleteTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = findTodoByIdAndCheckPassword(id, requestDto);

        todoRepository.delete(todo);

        return id;
    }

    private Todo findTodo(Long id) {
        return todoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

    private Todo findTodoByIdAndCheckPassword(Long id, TodoRequestDto requestDto) {
        Todo todo = findTodo(id);

        if (!todo.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return todo;
    }

}