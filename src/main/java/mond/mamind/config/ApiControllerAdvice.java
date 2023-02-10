package mond.mamind.config;

import com.fasterxml.jackson.databind.ser.Serializers;
import mond.mamind.src.model.PostUserRes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static mond.mamind.config.BaseResponseStatus.REQUEST_ERROR;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException exception){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return new BaseResponse<Map<String, String>>(REQUEST_ERROR, errors);
    }

    @ExceptionHandler(Exception.class)
    public Exception e(Exception e){
        System.out.println(e);
        return e;
    }

    @ExceptionHandler(BaseException.class)
    public BaseResponse<BaseException> baseException(BaseException e) {
        return new BaseResponse<>(e.getStatus());
    }

}