package com.tos.auth;


import com.tos.domain.UserP;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    @Transactional
    UserP register(UserP userToAdd);

    @Transactional
    String login(String username, String password);

    @Transactional
    String refresh(String oldToken);
}
