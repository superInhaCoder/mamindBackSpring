package mond.mamind.src.controller;

import mond.mamind.config.BaseException;
import mond.mamind.config.BaseResponse;
import mond.mamind.src.model.PostUserReq;
import mond.mamind.src.model.PostUserRes;
import mond.mamind.src.service.UserService;
import mond.mamind.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
        System.out.println(postUserReq.getPassword());
        try {
            PostUserRes postUserRes = userService.createUser(postUserReq);
            return new BaseResponse<>(postUserRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}