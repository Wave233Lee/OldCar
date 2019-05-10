package com.example.oldcar.service;

import com.example.oldcar.domain.CarBrand;
import com.example.oldcar.domain.CarHeader;
import com.example.oldcar.domain.User;
import com.example.oldcar.domain.UserCarHistory;
import com.example.oldcar.repository.*;
import com.example.oldcar.utils.MatrixUtil;
import com.example.oldcar.utils.MyComparatorUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class CarRecommendService {
    @Autowired
    private CarHeaderRepository carHeaderRepository;

    @Autowired
    private UserCarHistoryRepository userCarHistoryRepository;

    private String path = "C:/matrix/";

    //品牌热门值个数
    private Integer hotnum = 5;

    //车级个数
    private Integer levelnum = 18;

    //价格区间个数
    private Integer pricerangenum = 10;

    //车龄区间个数
    private Integer uselengthrangenum = 5;

    private final static Logger logger = LoggerFactory.getLogger(CarRecommendService.class);

    public void BrandSimilarity() throws IOException {
        //创建文件
        String fileName = path+"brand.txt";
        File file = new File(fileName);
        if(file.exists()){
            return;
        } else{
            file.createNewFile();
        }

        Double[][] hotMatrix = new Double[hotnum][hotnum];
        for(int i=1; i<=hotnum; i++){
            for(int j=1; j<=hotnum; j++){
                hotMatrix[i-1][j-1] = (double)(hotnum-Math.abs(i-j))/(double)hotnum;
            }
        }

        //将矩阵写入文件
        MatrixUtil.writeMatrixFile(fileName,hotMatrix,hotnum);
    }

    public void LevelSimilarity() throws IOException {
        //创建文件
        String fileName = path+"level.txt";
        System.out.println(fileName);
        File file = new File(fileName);
        if(file.exists()){
            return;
        } else{
            file.createNewFile();
        }

        Double[][] levelMatrix = new Double[levelnum][levelnum];
        for(int i=1; i<=levelnum; i++){
            for(int j=1; j<=levelnum; j++){
                levelMatrix[i][j] = (double)(levelnum-Math.abs(i-j))/(double)levelnum;
            }
        }

        //将矩阵写入文件
        MatrixUtil.writeMatrixFile(fileName,levelMatrix,levelnum);
    }

    public void PriceRangeSimilarity() throws IOException {
        //创建文件
        String fileName = path+"pricerange.txt";
        System.out.println(fileName);
        File file = new File(fileName);
        if(file.exists()){
            return;
        } else{
            file.createNewFile();
        }

        Double[][] pricerangeMatrix = new Double[pricerangenum][pricerangenum];
        for(int i=0; i<pricerangenum; i++){
            for(int j=0; j<pricerangenum; j++){
                pricerangeMatrix[i][j] = (double)(pricerangenum-Math.abs(i-j))/(double)pricerangenum;
            }
        }

        //将矩阵写入文件
        MatrixUtil.writeMatrixFile(fileName,pricerangeMatrix,pricerangenum);
    }

    public void UseLengthRangeSimilarity() throws IOException {
        //创建文件
        String fileName = path+"uselengthrange.txt";
        System.out.println(fileName);
        File file = new File(fileName);
        if(file.exists()){
            return;
        } else{
            file.createNewFile();
        }

        Double[][] uselengthrangeMatrix = new Double[uselengthrangenum][uselengthrangenum];
        for(int i=0; i<uselengthrangenum; i++){
            for(int j=0; j<uselengthrangenum; j++){
                uselengthrangeMatrix[i][j] = (double)(uselengthrangenum-Math.abs(i-j))/(double)uselengthrangenum;
            }
        }

        //将矩阵写入文件
        MatrixUtil.writeMatrixFile(fileName,uselengthrangeMatrix,uselengthrangenum);
    }

    public List<CarHeader> CarRecommend(Long id) throws JSONException {
        if(id == null) return null;

        CarHeader car = carHeaderRepository.getOne(id);
        CarBrand carBrand = car.getBrand();
        Long carBrandId = carBrand.getId();
        Long carId = car.getId();
        int carHot = carBrand.getHot();
        int level = car.getLevel().getId();
        int pricerange = car.getPriceRange();
        int uselengthrange = car.getUseLengthRange();

        double Wh = 0.3;
        double Wl = 0.2;
        double Wp = 0.2;
        double Wu = 0.3;

        //依次取出数组
        String fileName = path+"brand.txt";
        Double[][] hotMatrix;
        hotMatrix = MatrixUtil.readMatrixFile(fileName,hotnum);

        fileName = path+"level.txt";
        Double[][] levelMatrix;
        levelMatrix = MatrixUtil.readMatrixFile(fileName,levelnum);

        fileName = path+"pricerange.txt";
        Double[][] pricerangeMatrix;
        pricerangeMatrix = MatrixUtil.readMatrixFile(fileName,pricerangenum);

        fileName = path+"uselengthrange.txt";
        Double[][] uselengthrangeMatrix;
        uselengthrangeMatrix = MatrixUtil.readMatrixFile(fileName,uselengthrangenum);

        //根据车所推荐的车的集合
        List<CarHeader> CarRecommendCar = new ArrayList<>();

        //看过car车的所有用户集合
        List<User> users = new ArrayList<>();

        List<UserCarHistory> userCarHistories = userCarHistoryRepository.findDistinctUserByCar(car);
        for (UserCarHistory a: userCarHistories
             ) {
            users.add(a.getUser());
        }

        //根据车所推荐车的个数
        int recommendCarNum = 10;

        //按照相似度推荐
        if(users.size()>1){
            //获取用户集合浏览过的所有车的集合
            List<CarHeader> allUsersCars = new ArrayList<>();
            for (User a: users
                 ) {
                List<UserCarHistory> userCarHistoryList = userCarHistoryRepository.findByUser(a);
                for (UserCarHistory b:userCarHistoryList
                     ) {
                    allUsersCars.add(b.getCar());
                }
            }

            //按照频次排序
            List<JSONObject> carsFrequency = new ArrayList<>();
            Map<Long,Integer> map = new HashMap<>();
            for (CarHeader a:allUsersCars
                 ) {
                if(map.containsKey(a.getId())){
                    map.put(a.getId(),map.get(a.getId())+1);
                }else{
                    map.put(a.getId(),1);
                }
            }
            //如果车的集合不够
            if (map.size() < recommendCarNum){
                for(Map.Entry<Long,Integer> entry: map.entrySet()){
                    CarRecommendCar.add(carHeaderRepository.getOne(entry.getKey()));
                }
                List<CarHeader> RestCar = carHeaderRepository.findByBrand_Id(carBrandId);
                if(RestCar.size() <= recommendCarNum-CarRecommendCar.size()){
                    RestCar.addAll(carHeaderRepository.findFirst10ByOrderByUseLengthRange());
                }
                for (CarHeader a:RestCar
                     ) {
                    if(! map.containsKey(a.getId())){
                        CarRecommendCar.add(a);
                    }
                    if(CarRecommendCar.size() == recommendCarNum) return CarRecommendCar;
                }
            }

            for(Map.Entry<Long,Integer> entry: map.entrySet()){
                JSONObject obj = new JSONObject();
                if(entry.getKey().equals(carId)){
                    continue;
                }
                obj.put("id",entry.getKey());
                obj.put("value",entry.getValue());
                carsFrequency.add(obj);
            }
            carsFrequency.sort(new MyComparatorUtil());

            //更新相似度后排序
            int maxFrequency = carsFrequency.get(0).getInt("value");
            for(int i=0; i<carsFrequency.size(); i++){
                JSONObject obj = carsFrequency.get(i);
                CarHeader newCar = carHeaderRepository.getOne(obj.getLong("id"));

                int newHot = newCar.getBrand().getHot();
                int newLevel = newCar.getLevel().getId();
                int newPriceRange = newCar.getPriceRange();
                int newUseLengthRange = newCar.getUseLengthRange();
                double similarity = hotMatrix[carHot-1][newHot-1] * Wh + levelMatrix[level-1][newLevel-1] * Wl +
                        pricerangeMatrix[pricerange][newPriceRange] * Wp + uselengthrangeMatrix[uselengthrange][newUseLengthRange] * Wu;

                double value = (double)(obj.getInt("value")/maxFrequency) * similarity;
                JSONObject newobj = new JSONObject();
                newobj.put("id",obj.getLong("id"));
                newobj.put("value",value);
                carsFrequency.set(i,newobj);
            }
            carsFrequency.sort(new MyComparatorUtil());

            for (JSONObject a:carsFrequency
                 ) {
                CarRecommendCar.add(carHeaderRepository.getOne(a.getLong("id")));
                if(CarRecommendCar.size() == recommendCarNum) break;
            }
            if(CarRecommendCar.size() < recommendCarNum){
                int rest = recommendCarNum - CarRecommendCar.size();
                List<CarHeader> restcars = carHeaderRepository.findFirst10ByOrderByUseLengthRange();
                for(int i=0; i<rest; i++){
                    CarRecommendCar.add(restcars.get(i));
                }
            }
        }
        //按照浏览的车推荐
        else{
            List<CarHeader> InitRecommend = carHeaderRepository.findByBrand_Id(carBrandId);
            if(InitRecommend.size() <= recommendCarNum){
                InitRecommend.addAll(carHeaderRepository.findFirst10ByBrand_IdNotOrderByUseLengthRange(carBrandId));
                for (CarHeader a:InitRecommend
                ) {
                    if(!a.getId().equals(carId)) CarRecommendCar.add(a);
                    if(CarRecommendCar.size() == recommendCarNum) break;
                }
            } else{
                for(int i=0; i<recommendCarNum; i++){
                    CarRecommendCar.add(InitRecommend.get(i));
                }
            }
        }
        for (CarHeader a:CarRecommendCar
        ) {
            logger.info(String.valueOf(a.getId()));
            logger.info("==================================");
        }
        return CarRecommendCar;
    }
}
