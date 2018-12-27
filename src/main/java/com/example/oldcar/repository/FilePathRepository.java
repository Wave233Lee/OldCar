package com.example.oldcar.repository;

import com.example.oldcar.domain.FilePath;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/20
 */
public interface FilePathRepository extends JpaRepository<FilePath, Long> {
}
