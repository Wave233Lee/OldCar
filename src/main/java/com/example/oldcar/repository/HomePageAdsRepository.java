package com.example.oldcar.repository;

import com.example.oldcar.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/21
 */
public interface HomePageAdsRepository extends JpaRepository<Ad,Long> {
}
