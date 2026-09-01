package com.example.portfolio_api.service;

import com.example.portfolio_api.JwtTokenProvider;
import com.example.portfolio_api.dto.SignupRequest;
import com.example.portfolio_api.dto.UserResponse;
import com.example.portfolio_api.entity.User;
import com.example.portfolio_api.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Getter
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

//    public void signup(String email, String rawPassword) {
//        if(userRepository.findByEmail(email).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 있는 계정");
//        }
//        userRepository.save(new User(email, passwordEncoder.encode(rawPassword)));
//    }

    public void signup(SignupRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 있는 계정");
        }
        userRepository.save(new User(request.getEmail(), passwordEncoder.encode(request.getPassword())));
    }

    public String login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패"));
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패");
        }
        return jwtTokenProvider.createToken(user.getEmail());
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        return UserResponse.from(user);
    }
}