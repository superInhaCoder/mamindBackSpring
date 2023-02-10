package mond.mamind.src.controller;

import lombok.extern.slf4j.Slf4j;
import mond.mamind.config.BaseException;
import mond.mamind.config.BaseResponse;
import mond.mamind.src.model.PostLoginReq;
import mond.mamind.src.model.PostLoginRes;
import mond.mamind.src.model.PostUserReq;
import mond.mamind.src.model.PostUserRes;
import mond.mamind.src.security.SecurityUser;
import mond.mamind.src.service.UserService;
import mond.mamind.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping ("/create")
    public BaseResponse<PostUserRes> userCreate(@Valid @RequestBody PostUserReq postUserReq) {
        log.warn("dd");
        try {
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping ("/login")
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