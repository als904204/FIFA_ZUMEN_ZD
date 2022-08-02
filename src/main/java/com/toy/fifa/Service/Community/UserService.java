package com.toy.fifa.Service.Community;


import com.toy.fifa.Config.ExceptionConfig.DataNotFoundException;
import com.toy.fifa.Config.ExceptionConfig.DuplicatedUsernameException;
import com.toy.fifa.Config.ExceptionConfig.DuplicatedUserException;
import com.toy.fifa.Config.ExceptionConfig.NicknameNotFound;
import com.toy.fifa.Entity.User;
import com.toy.fifa.Entity.RoleType;
import com.toy.fifa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // TODO : User Entity -> DTO 로 받고 DTO -> Entity 로 변환 후 db 에 save
    @Transactional
    public User join(String nickname, String username, String password) {
        duplicatedUserByNickname(nickname);
        duplicatedUserByUsername(username);
        User joinUser = new User();
        joinUser.setRoleType(RoleType.USER);
        String encodedPassword = passwordEncoder.encode(password);
        joinUser.setNickname(nickname);
        joinUser.setUsername(username);
        joinUser.setPassword(encodedPassword);
        userRepository.save(joinUser);
        return joinUser;
    }

    // 중복 닉네임
    private void duplicatedUserByNickname(String nickname) {
        userRepository.findByNickname(nickname).ifPresent(user -> {
            throw new DuplicatedUserException();
        });
    }

    // 중복 메일
    private void duplicatedUserByUsername(String username) {
        if (findByUsername(username).isPresent()) {
            throw new DuplicatedUsernameException();
        }
    }


    public User findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 유저 ID 를 찾을 수 없습니다");
        });
        return user;
    }

    public Optional<User> findByNickname(String nickname) {
        User user = userRepository.findByNickname(nickname).orElseThrow(() -> {
            throw new DataNotFoundException("해당 닉네임 유저를 찾을 수 없습니다");
        });
        return userRepository.findByNickname(nickname);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
