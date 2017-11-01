package com.tos.quartz.two;

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
public interface SysConfRepository extends JpaRepository<SysConf, Long> {

}
