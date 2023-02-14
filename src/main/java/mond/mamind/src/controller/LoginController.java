package mond.mamind.src.controller;

import lombok.extern.slf4j.Slf4j;
import mond.mamind.config.BaseException;
import mond.mamind.config.BaseResponse;
import mond.mamind.src.model.PostLoginReq;
import mond.mamind.src.model.PostLoginRes;
import mond.mamind.src.service.UserService;
import mond.mamind.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public LoginController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping ("/password")
    public BaseResponse<PostLoginRes> test(@Valid @RequestBody PostLoginReq postUserReq) {
        try {
            String token = userService.loginUser(postUserReq.getUsername(), postUserReq.getPassword());
            PostLoginRes PostLoginRes = new PostLoginRes(token);
            return new BaseResponse<>(PostLoginRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}