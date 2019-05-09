package com.example.oldcar.vo;

import com.example.oldcar.domain.AccessoriesHeader;
import com.example.oldcar.domain.ArticleHeader;
import com.example.oldcar.domain.CarHeader;
import com.example.oldcar.domain.VideoHeader;

import java.util.List;

public class CarVO {
    Integer flag;

    List<CarHeader> car0;

    List<CarHeader> car1;

    List<CarHeader> car2;

    List<CarHeader> car3;

    List<AccessoriesHeader> accessoriesHeaders;

    List<ArticleHeader> articleHeaders;

    List<VideoHeader> videoHeaders;

    public CarVO() {
    }

    public CarVO(Integer flag, List<CarHeader> car0, List<CarHeader> car1, List<CarHeader> car2, List<CarHeader> car3, List<AccessoriesHeader> accessoriesHeaders, List<ArticleHeader> articleHeaders, List<VideoHeader> videoHeaders) {
        this.flag = flag;
        this.car0 = car0;
        this.car1 = car1;
        this.car2 = car2;
        this.car3 = car3;
        this.accessoriesHeaders = accessoriesHeaders;
        this.articleHeaders = articleHeaders;
        this.videoHeaders = videoHeaders;
    }
}
