package com.tos.auth;


import com.tos.dao.RoleRepository;
import com.tos.dao.Role_UserRepository;
import com.tos.dao.UserRepository;
import com.tos.domain.*;
import com.tos.secruity.JwtTokenUtil;
import com.tos.secruity.JwtUser;
import com.tos.web.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private JwtTokenUtil jwtTokenUtil;

    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Role_UserRepository role_userRepository;


    @Value("${jwt.tokenHead}")
    private String tokenHead;



    @Autowired
    public AuthServiceImpl(

            AuthenticationManager authenticationManager,

            UserDetailsService userDetailsService,

            JwtTokenUtil jwtTokenUtil,

            UserRepository userRepository) {

        this.authenticationManager = authenticationManager;

        this.userDetailsService = userDetailsService;

        this.jwtTokenUtil = jwtTokenUtil;

        this.userRepository = userRepository;

    }



    @Override

    public User register(User userToAdd) {

        final String username = userToAdd.getUsername();

        if(userRepository.findByUsername(username)!=null) {

            return null;

        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        final String rawPassword = userToAdd.getPassword();

        userToAdd.setPassword(encoder.encode(rawPassword));

        userToAdd.setLastPasswordResetDate(new Date());

        //查找默认权限
        Role oneByRole = roleRepository.findOneByRole(Content.ROLE_USER);
        //设置关联默认权限
        Role_User role_user = new Role_User();
        role_user.setRole(oneByRole);
        role_user.setUser(userToAdd);
        role_userRepository.save(role_user);

        return userRepository.save(userToAdd);

    }



    @Override

    public String login(String username, String password) {

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);

        // Perform the security

        final Authentication authentication = authenticationManager.authenticate(upToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);



        // Reload password post-security so we can generate token

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        final String token = jwtTokenUtil.generateToken(userDetails);

        return token;

    }



    @Override

    public String refresh(String oldToken) {

        final String token = oldToken.substring(tokenHead.length());

        String username = jwtTokenUtil.getUsernameFromToken(token);

        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){

            return jwtTokenUtil.refreshToken(token);

        }



        return null;

    }

}