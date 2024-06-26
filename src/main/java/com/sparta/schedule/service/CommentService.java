package com.sparta.schedule.service;

import com.sparta.schedule.dto.CommentRequestDto;
import com.sparta.schedule.dto.CommentResponseDto;
import com.sparta.schedule.entity.Comment;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.repository.CommentRepository;
import com.sparta.schedule.repository.ScheduleRepository;
import com.sparta.schedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Schedule schedule = findSchedule(requestDto.getScheduleId());
        User user = findUser(requestDto.getUserId());
        Comment comment = commentRepository.save(new Comment(requestDto, user, schedule));
        return new CommentResponseDto(comment, user, schedule);
    }

    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        Schedule schedule = findSchedule(requestDto.getScheduleId());
        User user = findUser(requestDto.getUserId());
        Comment comment = findComment(id);

        // 작성자가 동일하지 않는 경우
        if (!Objects.equals(comment.getUser(), user)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        comment.update(requestDto.getComment());

        return new CommentResponseDto(comment, user, schedule);
    }

    public Long deleteComment(Long id, CommentRequestDto requestDto) {
        Comment comment = findComment(id);

        User user = findUser(requestDto.getUserId());

        // 작성자가 동일하지 않는 경우
        if (!Objects.equals(comment.getUser(), user)) {
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        }

        commentRepository.delete(comment);

        return id;
    }

    private Comment findComment(Long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 댓글은 존재하지 않습니다.")
        );
    }

    private User findUser(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 사용자는 존재하지 않습니다.")
        );
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }

}
