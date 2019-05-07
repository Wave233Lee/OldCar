package com.example.oldcar.repository;

import com.example.oldcar.domain.CarHeader;
import com.example.oldcar.domain.User;
import com.example.oldcar.domain.UserCarHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2019/5/2
 */
public interface UserCarHistoryRepository extends JpaRepository<UserCarHistory,Long> {
    List<UserCarHistory> findByUser(User user);
    List<UserCarHistory> findDistinctUserByCar(CarHeader Car);
}
