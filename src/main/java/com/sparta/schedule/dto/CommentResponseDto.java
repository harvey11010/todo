package com.sparta.schedule.dto;

import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String comment;
    private Long userId;
    private Long scheduleId;

    public CommentResponseDto(Comment comment, User user, Schedule schedule) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.userId = user.getId();
        this.scheduleId = schedule.getId();
    }
}
