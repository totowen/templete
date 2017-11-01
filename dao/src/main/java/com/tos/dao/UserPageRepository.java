package com.tos.dao;

import com.tos.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qq136
 * @date 2017/10/20.
 */
@Repository
public interface UserPageRepository extends PagingAndSortingRepository<User,Long> {

    Page<User> findByUsername(String username, Pageable pageable);

    List<User> findByUsername(String username, Sort sort);

}
