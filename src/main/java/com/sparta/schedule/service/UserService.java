package com.sparta.schedule.service;

import com.sparta.schedule.dto.SignupRequestDto;
import com.sparta.schedule.entity.User;
import com.sparta.schedule.entity.UserRoleEnum;
import com.sparta.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // username 유효성 검사
        if (!username.matches("^[a-z0-9]{4,10}$")) {
            throw new IllegalArgumentException("username 은 최소 4자 이상, 10자 이하이며 알파벳 소문자(a-z), 숫자(0-9)로 구성되어야 합니다.");
        }

        // password 유효성 검사
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,15}$")) {
            throw new IllegalArgumentException("password 는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a-z, A-Z)와 숫자(0-9)로 구성되어야 합니다.");
        }

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 username 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(nickname, username, password, role);
        userRepository.save(user);
    }
}
