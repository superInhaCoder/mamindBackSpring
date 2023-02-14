package mond.mamind.src.security;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import mond.mamind.config.BaseException;
import mond.mamind.config.BaseResponse;
import mond.mamind.utils.JwtService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static mond.mamind.config.BaseResponseStatus.*;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final SecurityUserDetailsService customUserDetailsService;
    private final JwtService jwtService;
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    public JwtAuthenticationFilter(SecurityUserDetailsService customUserDetailsService, JwtService jwtService,HandlerExceptionResolver resolver) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtService = jwtService;
        this.resolver = resolver;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)
            throws ServletException {
        String path = request.getRequestURI();
        String[] exclude = {"/register/password", "/login/password", "/login/google"};
        for (String ex : exclude) {
            if (ex.equals(path)) return true;
        }
        return false;
    }

// 나중에 provider로 바꾸면 좋음
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        try {
            Long userId = Long.valueOf(jwtService.getUserId());
            UserDetails userDetails = customUserDetailsService.loadUserByUserid(userId);

            UsernamePasswordAuthenticationToken auth =
                    // 여기에서 super.setAuthenticated(true) 세팅
                    new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (BaseException exception) {
            resolver.resolveException(request, response, null, exception);
            return;
        } catch (UsernameNotFoundException exception) {
            resolver.resolveException(request, response, null, new BaseException(USER_NOT_FOUND));
            return;
        } catch (Exception exception) {
            resolver.resolveException(request, response, null, exception);
            return;
        }
        filterChain.doFilter(request, response);
    }
}