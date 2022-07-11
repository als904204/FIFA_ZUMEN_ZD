package com.toy.fifa.Controller.ApiController;


import com.toy.fifa.Entity.DTO.UserApiResponseDto;
import com.toy.fifa.Entity.DTO.UserInfoResponseDto;
import com.toy.fifa.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @GetMapping("/api/v1/user/{nickname}")
    public String requestUserInfo(@PathVariable String nickname) {
        UserApiResponseDto userApiResponseDto = userService.requestUserInfo(nickname);
        return userService.save(userApiResponseDto);
    }


    @GetMapping("/api/v1/user/accessId/{nickname}")
    public String requestUserAccessId(@PathVariable String nickname) {
        UserInfoResponseDto userInfoResponseDto = userService.findUserInfoById(nickname);
        return userInfoResponseDto.getAccessId();
    }
}
