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

/**
 * UserDetailsService 这个接口只定义了一个方法 loadUserByUsername，
 * 顾名思义，就是提供一种从用户名可以查到用户并返回的方法。
 * 注意，不一定是数据库哦，文本文件、xml文件等等都可能成为数据源，
 * 这也是为什么Spring提供这样一个接口的原因：保证你可以采用灵活的数据源。
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Role_UserRepository role_userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserP user = userRepository.findByUsername(username);

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