package com.example.oldcar.repository;

import com.example.oldcar.domain.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2019/3/6
 */
public interface AccessLogRepository extends JpaRepository<AccessLog,Long> {
}
