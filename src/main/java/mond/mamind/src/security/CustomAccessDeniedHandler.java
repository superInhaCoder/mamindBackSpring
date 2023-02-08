package mond.mamind.src.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import mond.mamind.config.BaseException;
import mond.mamind.config.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static mond.mamind.config.BaseResponseStatus.*;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {

        // log.error("Forbidden!!! message : " + e.getMessage());
        System.out.println("Forbidden!!! message : " + e.getMessage());

        // response에 넣기
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        try (OutputStream os = response.getOutputStream()) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(os, new BaseResponse<>(new BaseException(FORBIDDEN).getStatus()));
            os.flush();
        }
    }
}