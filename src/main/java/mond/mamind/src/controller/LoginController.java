package mond.mamind.src.controller;

import lombok.extern.slf4j.Slf4j;
import mond.mamind.config.BaseException;
import mond.mamind.config.BaseResponse;
import mond.mamind.src.model.Login.PostGoogleReq;
import mond.mamind.src.model.Login.PostGoogleRes;
import mond.mamind.src.model.Login.PostPasswordReq;
import mond.mamind.src.model.Login.PostPasswordRes;
import mond.mamind.src.service.UserService;
import mond.mamind.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import java.util.Map;

import static mond.mamind.config.BaseResponseStatus.INVALID_TOKEN;
import static mond.mamind.config.Constant.GOOGLE_TOKEN_BASE_URL;

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
    public BaseResponse<PostPasswordRes> password(@Valid @RequestBody PostPasswordReq postUserReq) {
        try {
            String token = userService.loginPassword(postUserReq.getUsername(), postUserReq.getPassword());
            PostPasswordRes PostPasswordRes = new PostPasswordRes(token);
            return new BaseResponse<>(PostPasswordRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @PostMapping ("/google")
    public BaseResponse<PostGoogleRes> google(@Valid @RequestBody PostGoogleReq postGoogleReq) {
        try {
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map> resultEntity = restTemplate.getForEntity(GOOGLE_TOKEN_BASE_URL + "?id_token=" + postGoogleReq.getIdToken(), Map.class);
            if (resultEntity.getStatusCode() != HttpStatus.OK) {
                throw new BaseException(INVALID_TOKEN);
            }
            log.error(resultEntity.getBody().get("sub").toString());
            String token = userService.loginGoogle(resultEntity.getBody().get("sub").toString());
            PostGoogleRes postGoogleRes = new PostGoogleRes(token);
            return new BaseResponse<>(postGoogleRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}