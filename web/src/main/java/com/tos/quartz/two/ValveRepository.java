package com.tos.quartz.two;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by qq136 on 2017/6/22.
 */
@Repository
public interface ValveRepository extends JpaRepository<Valve, Long> {

}
