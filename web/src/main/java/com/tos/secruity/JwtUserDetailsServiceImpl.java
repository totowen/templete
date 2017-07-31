package com.tos.secruity;


import com.tos.dao.Role_UserRepository;
import com.tos.dao.UserRepository;
import com.tos.domain.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service

public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Role_UserRepository role_userRepository;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        List<Role_User> allByUser = role_userRepository.findAllByUser(user.getId());
        List<String> collect = allByUser.stream()
                .map(s -> s.getRole().getRole())
                .collect(toList());
        user.setRoles(collect);

        if (user == null) {

            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));

        } else {

            return JwtUserFactory.create(user);

        }

    }

}