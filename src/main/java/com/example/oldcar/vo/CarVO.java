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

   // List<ArticleHeader> articleHeaders;

   // List<VideoHeader> videoHeaders;

    public CarVO(Integer flag1, List<CarHeader> car01, List<CarHeader> car11, List<CarHeader> car21, List<CarHeader> car31, List<AccessoriesHeader> accessoriesHeaders1) {
        flag = flag1;
        car0 = car01;
        car1 = car11;
        car2 = car21;
        car3 = car31;
        accessoriesHeaders = accessoriesHeaders1;
    }

}
