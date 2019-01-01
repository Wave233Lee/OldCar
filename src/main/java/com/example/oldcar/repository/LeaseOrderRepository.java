package com.example.oldcar.repository;

import com.example.oldcar.domain.LeaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/28
 */
public interface LeaseOrderRepository extends JpaRepository<LeaseOrder,Long> {
}
