package com.tos.secruity;

import java.util.List;

import java.util.stream.Collectors;


import com.tos.domain.UserP;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


/**
 * 为了写起来简单，我们写一个工厂类来由领域对象创建 JwtUser，这个工厂就叫 JwtUserFactory
 */
public final class JwtUserFactory {

    private JwtUserFactory() {

    }

    public static JwtUser create(UserP user) {

        return new JwtUser(

                user.getId(),

                user.getUsername(),

                user.getPassword(),

                user.getEmail(),

                mapToGrantedAuthorities(user.getRoles()),

                user.getLastPasswordResetDate()

        );

    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {

        return authorities.stream()

                .map(SimpleGrantedAuthority::new)

                .collect(Collectors.toList());

    }

}
