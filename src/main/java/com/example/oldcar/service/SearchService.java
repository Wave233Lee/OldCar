package com.example.oldcar.service;

import com.example.oldcar.domain.ArticleHeader;
import com.example.oldcar.domain.CarHeader;
import com.example.oldcar.domain.CarSeries;
import com.example.oldcar.domain.VideoHeader;
import com.example.oldcar.repository.*;
import com.example.oldcar.vo.CarVO;
import com.example.oldcar.vo.SearchResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

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
    private CarSeriesRepository carSeriesRepository;

    @Autowired
    private ArticleHeaderRepository articleHeaderRepository;

    @Autowired
    private VideoHeaderRepository videoHeaderRepository;

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Autowired
    private AccessoriesHeaderRepository accessoriesHeaderRepository;

    private static final double ALPHA=0.85;
    private static final double Distance=0.0000001;
    //搜索接口

    public List<CarHeader> searchCar(String keyWord){
        //对keyWord进行分词，ansj
        //只关注这些词性的词
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
        String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;
        Result result = ToAnalysis.parse(str); //分词结果的一个封装，主要是一个List<Term>的terms
        System.out.println(result.getTerms());

        List<Term> terms = result.getTerms(); //拿到terms
        System.out.println(terms.size());
/*
        for(int i=0; i<terms.size(); i++) {
            String word = terms.get(i).getName(); //拿到词
            String natureStr = terms.get(i).getNatureStr(); //拿到词性
            if(expectedNature.contains(natureStr)) {
                System.out.println(word + ":" + natureStr);
            }
        }
*/
        int flagjump=0;
        //判断搜索内容是配件还是车型，先搜索配件数据库
        for(int i=0;i<terms.size();i++){
            if(accessoriesHeaderRepository.findByNameLike(terms.get(i).getName())!=null){
                flagjump=1;
            }
        }


        //采取类似pagerank排序
        //q=Gq
        //G=aS+(1-a)*1/n*U,S网页指向原始矩阵，n网页数量，U全为1的n阶矩阵
        //从数据库提取汽车数据
        //0是老爷车，1是进口车，2是二手车，3是新能源
        int tempi=0;
        for(int i=0;i<terms.size();i++){
            if(carHeaderRepository.findByNameLike(terms.get(i).getName())!=null){
                tempi=i;
            }
        }
        List<CarHeader> cars0 = carHeaderRepository.findByTypeAndSeries_NameLike(0,"%" + terms.get(tempi).getName() + "%");
        List<CarHeader> cars1 = carHeaderRepository.findByTypeAndSeries_NameLike(1,"%" + terms.get(tempi).getName() + "%");
        List<CarHeader> cars2 = carHeaderRepository.findByTypeAndSeries_NameLike(2,"%" + terms.get(tempi).getName() + "%");
        List<CarHeader> cars3 = carHeaderRepository.findByTypeAndSeries_NameLike(3,"%" + terms.get(tempi).getName() + "%");

        //car0操作
        //汽车特征向量q,赋予初始随机值
        List<Double> q0=new ArrayList<Double>();
        int carsize0=cars0.size();
        for(int i=0;i<carsize0;i++){
            Double temp=Math.random();
            q0.add(temp);
        }
        printMatrix(getG(ALPHA,carsize0));


        //从数据库提取文章数据
        List<ArticleHeader> articles = articleHeaderRepository.findByAuthor_UserNameLike("%" + keyWord + "%");
        articles.addAll(articleHeaderRepository.findByTitleLike("%" + keyWord + "%"));

        //从数据库提取视频数据
        List<VideoHeader> videos = videoHeaderRepository.findByPublisher_UserName("%" + keyWord + "%");
        videos.addAll(videoHeaderRepository.findByTitleLike("%" + keyWord + "%"));

        //使用VO对象存储、处理数据库元数据
        /*
        List<CarVO> carVOS = null;
        for (CarHeader c:cars
             ) {
            CarVO carVO = new CarVO(c,0);
        }
        //将处理后的cars,articles,videos封装成一个对象

        List<SearchResultVO> result = null;
        */
        return result;
    }

    //打印矩阵
    public static void printMatrix(List<List<Double>> m){
        for(int i=0;i<m.size();i++){
            for(int j=0;j<m.get(i).size();j++){
                System.out.print(m.get(i).get(j)+",");
            }
            System.out.println();
        }
    }

    //q=Gq
    //G=aS+(1-a)*1/n*U,S网页指向原始矩阵，n网页数量，U全为1的n阶矩阵
    public static List<List<Double>> getG(double a,int n){
        List<List<Double>> aS=numberMulMatrix(getS(n), a);
        List<List<Double>> nU=numberMulMatrix(getU(), (1 - a) / n);
        List<List<Double>> g = addMatrix(aS, nU);
        return g;
    }
    public static List<List<Double>> numberMulMatrix(List<List<Double>> s,double a){
        List<List<Double>> list=new ArrayList<List<Double>>();
        for(int i=0;i<s.size();i++){
            list.add(new ArrayList<Double>());
            for(int j=0;j<s.get(i).size();j++){
                double temp=a*s.get(i).get(j).doubleValue();
                list.get(i).add(new Double(temp));
            }
        }
        return list;
    }
    public static List<List<Double>> getU(){

    }

    public static List<List<Double>> getS(int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<3;j++){
                cars(i)
            }
        }
    }
}
