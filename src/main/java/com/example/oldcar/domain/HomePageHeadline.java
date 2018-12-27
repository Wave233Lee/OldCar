package com.example.oldcar.domain;

import javax.persistence.*;

/**
 * 说明:首页头条
 *
 * @author WaveLee
 * 日期: 2018/12/22
 */
@Entity
@Table(name = "home_page_headline")
public class HomePageHeadline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应首页
     */
    @ManyToOne
    @JoinColumn(name = "home_page_header",referencedColumnName = "id")
    private HomePageHeader homePageHeader;

    /**
     * 头条标题
     */
    private String title;

    /**
     * 头条文章
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article",referencedColumnName = "id")
    private ArticleHeader article;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HomePageHeader getHomePageHeader() {
        return homePageHeader;
    }

    public void setHomePageHeader(HomePageHeader homePageHeader) {
        this.homePageHeader = homePageHeader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArticleHeader getArticle() {
        return article;
    }

    public void setArticle(ArticleHeader article) {
        this.article = article;
    }
}
