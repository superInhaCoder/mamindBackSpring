package mond.mamind.src.controller;

import lombok.extern.slf4j.Slf4j;
import mond.mamind.config.BaseException;
import mond.mamind.config.BaseResponse;
import mond.mamind.src.model.Register.PostUserPasswordReq;
import mond.mamind.src.model.Register.PostUserPasswordRes;
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
@RequestMapping("/register")
public class RegisterController {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public RegisterController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping ("/password")
    public BaseResponse<PostUserPasswordRes> postUserPassword(@Valid @RequestBody PostUserPasswordReq postUserPasswordReq) {
        try {
            PostUserPasswordRes postUserPasswordRes = userService.createUser(postUserPasswordReq);
            return new BaseResponse<>(postUserPasswordRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}