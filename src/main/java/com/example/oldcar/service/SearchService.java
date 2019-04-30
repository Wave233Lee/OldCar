package com.example.oldcar.service;

import com.example.oldcar.domain.ArticleHeader;
import com.example.oldcar.domain.CarHeader;
import com.example.oldcar.domain.VideoHeader;
import com.example.oldcar.repository.ArticleHeaderRepository;
import com.example.oldcar.repository.CarHeaderRepository;
import com.example.oldcar.repository.VideoHeaderRepository;
import com.example.oldcar.vo.CarVO;
import com.example.oldcar.vo.SearchResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 说明:综合搜索接口
 *
 * @author WaveLee
 * 日期: 2019/1/24
 */
@Service
public class SearchService {
    @Autowired
    private CarHeaderRepository carHeaderRepository;

    @Autowired
    private ArticleHeaderRepository articleHeaderRepository;

    @Autowired
    private VideoHeaderRepository videoHeaderRepository;

    //搜索接口
    public List<SearchResultVO> search(String keyWord){
        //从数据库提取汽车数据
        List<CarHeader> cars = carHeaderRepository.findByBrand_NameLike("%" + keyWord + "%");
        cars.addAll(carHeaderRepository.findByConfig_NameLike("%" + keyWord + "%"));
        cars.addAll(carHeaderRepository.findBySeries_NameLike("%" + keyWord + "%"));

        //从数据库提取文章数据
        List<ArticleHeader> articles = articleHeaderRepository.findByAuthor_UserNameLike("%" + keyWord + "%");
        articles.addAll(articleHeaderRepository.findByTitleLike("%" + keyWord + "%"));

        //从数据库提取视频数据
        List<VideoHeader> videos = videoHeaderRepository.findByPublisher_UserName("%" + keyWord + "%");
        videos.addAll(videoHeaderRepository.findByTitleLike("%" + keyWord + "%"));

        //使用VO对象存储、处理数据库元数据
        List<CarVO> carVOS = null;
        for (CarHeader c:cars
             ) {
        }







        //将处理后的cars,articles,videos封装成一个对象

        List<SearchResultVO> result = null;

        return result;
    }
}
