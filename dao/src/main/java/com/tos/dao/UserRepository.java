package com.tos.dao;

import com.tos.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by qq136 on 2017/6/22.
 */
@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {

        @Cacheable(key = "#p0")
        User findByUsername(String username);

        @CachePut(key = "#p0.name")
        User save(User user);
}
