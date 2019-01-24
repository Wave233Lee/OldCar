package com.example.oldcar.repository;

import com.example.oldcar.domain.VideoHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/27
 */
public interface VideoHeaderRepository extends JpaRepository<VideoHeader,Long> {
    /**
     * 通过作者名称模糊查询
     */
    List<VideoHeader> findByPublisher_UserName(String keyWord);

    
    VideoHeader findByTitle(String title);
    
    /**
     * 通过文章名称模糊查询
     */
    List<VideoHeader> findByTitleLike(String keyWord);
}
