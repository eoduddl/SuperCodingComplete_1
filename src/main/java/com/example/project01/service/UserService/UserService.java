package com.example.project01.service.UserService;

import com.example.project01.Entity.UserEntity;
import com.example.project01.controller.Dto.UserDto;
import com.example.project01.repository.UserRepository.UserRepository;
import com.example.project01.service.JwtService;
import com.example.project01.service.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;


    public void saveUser(UserDto userDto) {
        UserEntity userEntity = UserMapper.INSTANCE.UserDtoToUserEntity(userDto);
        userRepository.save(userEntity);
    }

    public String userLogout(String token) {
        boolean tokenExpired;

        if (jwtService.isTokenExpired(token)) tokenExpired = true;
        else tokenExpired = false;

        boolean present = jwtService.isPresent(token);

        if(!present){
            return "가입되지 않은 정보입니다.";
        }

        if (tokenExpired) {
            return "만료된 토큰";
        }
        return "로그아웃 되었습니다";
    }
}

