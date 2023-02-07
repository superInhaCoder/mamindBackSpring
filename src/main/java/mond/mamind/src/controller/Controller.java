package mond.mamind.src.controller;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import mond.mamind.utils.JwtService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class Controller {

    private final JwtService jwtService;

    @Autowired //readme 참고
    public Controller(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/first")
    public Map<String, Object> firstController(@RequestParam("id") UUID id) {
        String jwt = jwtService.createJwt(1L);
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", jwt);
        return map;
    }

    @GetMapping("/second")
    public Map<String, Object> firstController(@RequestParam("id") String token) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            String uuid = jwtService.getUserId();
            map.put("id", uuid);
        } catch (Exception BaseException) {
        }
        return map;
    }
}