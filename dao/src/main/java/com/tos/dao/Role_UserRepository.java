package com.tos.dao;

import com.tos.domain.Role_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qq136 on 2017/6/22.
 */
@Repository
public interface Role_UserRepository extends JpaRepository<Role_User, Long> {

    @Transactional
    @Query(value="select * from role_user r where r.user_id =:user_id",nativeQuery=true)
    List<Role_User> findAllByUser(@Param("user_id") Long user_id);
}
