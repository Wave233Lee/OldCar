package com.example.oldcar.repository;

import com.example.oldcar.domain.ArticleHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/25
 */
public interface ArticleHeaderRepository extends JpaRepository<ArticleHeader,Long> {
    /**
     * 通过作者名称模糊查询
     */
    List<ArticleHeader> findByAuthor_UserNameLike(String keyWord);

    /**
     * 通过文章名称模糊查询
     */
    List<ArticleHeader> findByTitleLike(String keyWord);
}
