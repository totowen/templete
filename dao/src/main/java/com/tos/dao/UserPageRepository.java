package com.tos.dao;

import com.tos.domain.UserP;
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
public interface UserPageRepository extends PagingAndSortingRepository<UserP,Long> {

    Page<UserP> findByUsername(String username, Pageable pageable);

    List<UserP> findByUsername(String username, Sort sort);

}
