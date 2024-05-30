package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);

        Schedule saveSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(saveSchedule);
    }

    public List<ScheduleResponseDto> getOneSchedules(Long id) {
        return scheduleRepository.findAllByIdOrderByCreatedAtDesc(id).stream().map(ScheduleResponseDto::new).toList();
    }

    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    public Long updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findScheduleByIdAndCheckPassword(id, requestDto);

        schedule.update(requestDto);

        return id;
    }

    public Long deleteSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findScheduleByIdAndCheckPassword(id, requestDto);

        scheduleRepository.delete(schedule);

        return id;
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }

    private Schedule findScheduleByIdAndCheckPassword(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);

        if (!schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 올바르지 않습니다.");
        }

        return schedule;
    }

}
