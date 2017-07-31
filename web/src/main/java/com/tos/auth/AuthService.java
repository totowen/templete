package com.tos.auth;


import com.tos.domain.User;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    @Transactional
    User register(User userToAdd);

    @Transactional
    String login(String username, String password);

    @Transactional
    String refresh(String oldToken);
}
