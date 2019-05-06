package com.example.oldcar.service;

import com.example.oldcar.domain.ArticleHeader;
import com.example.oldcar.domain.CarHeader;
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
    //0是老爷车，1是进口车，2是二手车，3是新能源
    public List<CarHeader> searchCar0(String keyWord){
        //对keyWord进行分词，ansj
        //只关注这些词性的词
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
       // String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;
        Result result = ToAnalysis.parse(keyWord); //分词结果的一个封装，主要是一个List<Term>的terms
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

        /*car0操作
        老爷车特征向量q0,赋予初始随机值
        */
        List<Double> q0=new ArrayList<Double>();
        int carsize0=cars0.size();
        for(int i=0;i<carsize0;i++){
            Double temp=Math.random();
            q0.add(temp);
        }
        printMatrix(getG(ALPHA,carsize0));
        List<Double> pageRank0 = calPageRank(q0, ALPHA,carsize0);
        //排序
        for(int i=0;i<carsize0;i++){
            for(int j=0;j<carsize0;j++){
                if(pageRank0.get(i)<pageRank0.get(j)){
                    double temp=pageRank0.get(i);
                    pageRank0.set(i,pageRank0.get(j));
                    pageRank0.set(j,temp);
                    CarHeader k=cars0.get(i);
                    cars0.set(i,cars0.get(j));
                    cars0.set(j,k);
                }
            }
        }
        //使用VO对象存储、处理数据库元数据
        /*
        List<CarVO> carVOS = null;
        for (CarHeader c:cars
             ) {
        }
        //将处理后的cars,articles,videos封装成一个对象

        List<SearchResultVO> result = null;
        */
        return cars0;
    }
    public List<CarHeader> searchCar1(String keyWord){
        //对keyWord进行分词，ansj
        //只关注这些词性的词
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
       // String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;
        Result result = ToAnalysis.parse(keyWord); //分词结果的一个封装，主要是一个List<Term>的terms
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
        List<CarHeader> cars1 = carHeaderRepository.findByTypeAndSeries_NameLike(1,"%" + terms.get(tempi).getName() + "%");
        /*car1操作
        进口车特征向量q1,赋予初始随机值
        */
        List<Double> q1=new ArrayList<Double>();
        int carsize1=cars1.size();
        for(int i=0;i<carsize1;i++){
            Double temp=Math.random();
            q1.add(temp);
        }
        printMatrix(getG(ALPHA,carsize1));
        List<Double> pageRank1 = calPageRank(q1, ALPHA,carsize1);
        //排序
        for(int i=0;i<carsize1;i++){
            for(int j=0;j<carsize1;j++){
                if(pageRank1.get(i)<pageRank1.get(j)){
                    double temp=pageRank1.get(i);
                    pageRank1.set(i,pageRank1.get(j));
                    pageRank1.set(j,temp);
                    CarHeader k=cars1.get(i);
                    cars1.set(i,cars1.get(j));
                    cars1.set(j,k);
                }
            }
        }
        return cars1;
    }
    public List<CarHeader> searchCar2(String keyWord){
        //对keyWord进行分词，ansj
        //只关注这些词性的词
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
       // String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;
        Result result = ToAnalysis.parse(keyWord); //分词结果的一个封装，主要是一个List<Term>的terms
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
        List<CarHeader> cars2 = carHeaderRepository.findByTypeAndSeries_NameLike(2,"%" + terms.get(tempi).getName() + "%");
        /*car2操作
        二手车特征向量q2,赋予初始随机值
        */
        List<Double> q2=new ArrayList<Double>();
        int carsize2=cars2.size();
        for(int i=0;i<carsize2;i++){
            Double temp=Math.random();
            q2.add(temp);
        }
        printMatrix(getG(ALPHA,carsize2));
        List<Double> pageRank2 = calPageRank(q2, ALPHA,carsize2);
        //排序
        for(int i=0;i<carsize2;i++){
            for(int j=0;j<carsize2;j++){
                if(pageRank2.get(i)<pageRank2.get(j)){
                    double temp=pageRank2.get(i);
                    pageRank2.set(i,pageRank2.get(j));
                    pageRank2.set(j,temp);
                    CarHeader k=cars2.get(i);
                    cars2.set(i,cars2.get(j));
                    cars2.set(j,k);
                }
            }
        }
        return cars2;
    }
    public List<CarHeader> searchCar3(String keyWord){
        //对keyWord进行分词，ansj
        //只关注这些词性的词
        Set<String> expectedNature = new HashSet<String>() {{
            add("n");add("v");add("vd");add("vn");add("vf");
            add("vx");add("vi");add("vl");add("vg");
            add("nt");add("nz");add("nw");add("nl");
            add("ng");add("userDefine");add("wh");
        }};
       // String str = "欢迎使用ansj_seg,(ansj中文分词)在这里如果你遇到什么问题都可以联系我.我一定尽我所能.帮助大家.ansj_seg更快,更准,更自由!" ;
        Result result = ToAnalysis.parse(keyWord); //分词结果的一个封装，主要是一个List<Term>的terms
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
        List<CarHeader> cars3 = carHeaderRepository.findByTypeAndSeries_NameLike(3,"%" + terms.get(tempi).getName() + "%");
        /*car3操作
        新能源车特征向量q3,赋予初始随机值
        */
        List<Double> q3=new ArrayList<Double>();
        int carsize3=cars3.size();
        for(int i=0;i<carsize3;i++){
            Double temp=Math.random();
            q3.add(temp);
        }
        printMatrix(getG(ALPHA,carsize3));
        List<Double> pageRank3 = calPageRank(q3, ALPHA,carsize3);
        //排序
        for(int i=0;i<carsize3;i++){
            for(int j=0;j<carsize3;j++){
                if(pageRank3.get(i)<pageRank3.get(j)){
                    double temp=pageRank3.get(i);
                    pageRank3.set(i,pageRank3.get(j));
                    pageRank3.set(j,temp);
                    CarHeader k=cars3.get(i);
                    cars3.set(i,cars3.get(j));
                    cars3.set(j,k);
                }
            }
        }
        return cars3;
    }
    public List<ArticleHeader> article(String keyWord) {
        //从数据库提取文章数据
        List<ArticleHeader> articles = articleHeaderRepository.findByAuthor_UserNameLike("%" + keyWord + "%");
        articles.addAll(articleHeaderRepository.findByTitleLike("%" + keyWord + "%"));
        return articles;
    }
    public List<VideoHeader> video(String keyWord) {
        List<VideoHeader> videos = videoHeaderRepository.findByPublisher_UserName("%" + keyWord + "%");
        videos.addAll(videoHeaderRepository.findByTitleLike("%" + keyWord + "%"));
        return videos;
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
        List<List<Double>> nU=numberMulMatrix(getU(n), (1 - a) / n);
        List<List<Double>> g = addMatrix(aS, nU);
        return g;
    }

    //两个矩阵的和
    public static List<List<Double>> addMatrix(List<List<Double>> list1,List<List<Double>> list2){
        List<List<Double>> list=new ArrayList<List<Double>>();
        if(list1.size()!=list2.size()||list1.size()<=0||list2.size()<=0){
            return null;
        }
        for(int i=0;i<list1.size();i++){
            list.add(new ArrayList<Double>());
            for(int j=0;j<list1.get(i).size();j++){
                double temp=list1.get(i).get(j).doubleValue()+list2.get(i).get(j).doubleValue();
                list.get(i).add(new Double(temp));
            }
        }
        return list;
    }
    //一个数*矩阵
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
    public static List<List<Double>> getU(int n){
        List<List<Double>> s = new ArrayList<List<Double>>();
        for(int i=0;i<n;i++){
            s.add(new ArrayList<Double>());
            for(int j=0;j<n;j++){
                s.get(i).add(new Double(1));
            }
        }
        return s;
    }

    public static List<List<Double>> getS(int n){
        List<List<Double>> s = new ArrayList<List<Double>>();
        List<List<Double>> w = new ArrayList<List<Double>>();
        for(int i=0;i<n;i++){
            s.add(new ArrayList<Double>());
            /*
            Set<Integer> set = new HashSet<Integer>();
            while(set.size()<2){
                set.add((int)((n-0+1)*Math.random()+0));
            }
            Object[] ints = set.toArray();*/
            int temp1=(int)((n-0+1)*Math.random()+0);
            int temp2=0;
            while(true){
                temp2=(int)((n-0+1)*Math.random()+0);
                if(temp2!=temp1){
                    break;
                }
            }
            for(int j=0;j<n;j++){
                if(temp1==j){
                    s.get(i).add(new Double(1 / 2.0));
                }
                if(temp2==j){
                    s.get(i).add(new Double(1 / 2.0));
                }
                else{
                    s.get(i).add(new Double(0));
                }
            }
        }
        //矩阵转置
        for(int i=0;i<n;i++){
            w.add(new ArrayList<Double>());
            for(int j=0;j<n;i++){
                s.get(i).add(s.get(j).get(i));
            }
        }
        return w;
    }
    //计算两个向量的距离
    public static double calDistance(List<Double> q1,List<Double> q2){
        double sum=0;
        if(q1.size()!=q2.size()){
            return -1;
        }
        for(int i=0;i<q1.size();i++){
            sum+=Math.pow(q1.get(i).doubleValue()-q2.get(i).doubleValue(),2);
        }
        return Math.sqrt(sum);
    }

    //pagerank
    public static List<Double> calPageRank(List<Double> q1,double a,int n){
        List<List<Double>> g=getG(a,n);
        List<Double> q=null;
        while(true){
            q=vectorMulMatrix(g,q1);
            double dis=calDistance(q,q1);
            System.out.println(dis);
            if(dis<=Distance){
                System.out.println("q1:");
                printVec(q1);
                System.out.println("q:");
                printVec(q);
                break;
            }
            q1=q;
        }
        return q;
    }
    //打印一个向量
    public static void printVec(List<Double> v){
        for(int i=0;i<v.size();i++){
            System.out.print(v.get(i)+",");
        }
        System.out.println();
    }
    //矩阵*向量
    public static List<Double> vectorMulMatrix(List<List<Double>> m,List<Double> v){
        if(m==null||v==null||m.size()<0||m.get(0).size()!=v.size()){
            return null;
        }
        List<Double> list=new ArrayList<Double>();
        for(int i=0;i<m.size();i++){
            double sum=0;
            for(int j=0;j<m.get(i).size();j++){
                double temp=m.get(i).get(j).doubleValue()*v.get(j).doubleValue();
                sum+=temp;
            }
            list.add(sum);
        }
        return list;
    }
}
