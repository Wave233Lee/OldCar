package com.example.oldcar.repository;

import com.example.oldcar.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/12
 */
public interface UserRepository extends JpaRepository<User,Long> {
}
