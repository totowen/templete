package com.tos.dao;

import com.tos.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qq136 on 2017/6/22.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

        @Transactional
        @Query(value="select * from role r where r.role =:role limit 1",nativeQuery=true)
        Role findOneByRole(@Param("role") String role);

}
