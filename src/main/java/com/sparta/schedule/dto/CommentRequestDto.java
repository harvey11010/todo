package com.sparta.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank
    private Long id;
    @NotBlank
    private String comment;
    @NotBlank
    private Long userId;
    @NotBlank
    private Long scheduleId;
}
