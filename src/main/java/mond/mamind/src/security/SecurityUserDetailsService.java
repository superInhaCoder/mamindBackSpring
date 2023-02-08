package mond.mamind.src.security;

import lombok.RequiredArgsConstructor;
import mond.mamind.src.domain.User;
import mond.mamind.src.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("해당 유저가 존재하지 않습니다.");
        }
        return new SecurityUser(user);
    }
    public UserDetails loadUserByUserid(Long userId)
            throws UsernameNotFoundException {

        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("해당 유저가 존재하지 않습니다.");
        }
        return new SecurityUser(user.get());
    }
}