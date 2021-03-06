package com.example.oldcar.repository;

import com.example.oldcar.domain.AccessoriesHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/28
 */
public interface AccessoriesHeaderRepository extends JpaRepository<AccessoriesHeader,Long> {
    List<AccessoriesHeader> findByNameLike(String name);
}
