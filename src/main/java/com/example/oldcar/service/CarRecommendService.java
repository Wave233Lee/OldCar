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

    public String path = "E:/matrix/";

    //品牌热门值个数
    public Integer hotnum = 5;

    //车级个数
    public Integer levelnum = 5;

    //价格区间个数
    public Integer pricerangenum = 10;

    //车龄区间个数
    public Integer uselengthrangenum = 5;

    private void BrandSimilarity() throws IOException {
        //创建文件
        String fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"brand.txt";
        System.out.println(fileName);
        File file = new File(fileName);
        if(file.exists()){
            return;
        }
        file.createNewFile();

        Double[][] hotMatrix = new Double[hotnum][hotnum];
        for(int i=1; i<=hotnum; i++){
            for(int j=1; j<=hotnum; j++){
                hotMatrix[i-1][j-1] = (double)(hotnum-Math.abs(i-j))/(double)hotnum;
            }
        }

        //将矩阵写入文件
        MatrixUtil.writeMatrixFile(fileName,hotMatrix,hotnum);
    }

    private void LevelSimilarity() throws IOException {
        //创建文件
        String fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"level.txt";
        System.out.println(fileName);
        File file = new File(fileName);
        if(file.exists()){
            return;
        }
        file.createNewFile();

        Double[][] levelMatrix = new Double[levelnum][levelnum];
        for(int i=0; i<levelnum; i++){
            for(int j=0; j<levelnum; j++){
                levelMatrix[i-1][j-1] = (double)(levelnum-Math.abs(i-j))/(double)levelnum;
            }
        }

        //将矩阵写入文件
        MatrixUtil.writeMatrixFile(fileName,levelMatrix,levelnum);
    }

    private void PriceRangeSimilarity() throws IOException {
        //创建文件
        String fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"pricerange.txt";
        System.out.println(fileName);
        File file = new File(fileName);
        if(file.exists()){
            return;
        }
        file.createNewFile();

        Double[][] pricerangeMatrix = new Double[pricerangenum][pricerangenum];
        for(int i=0; i<pricerangenum; i++){
            for(int j=0; j<pricerangenum; j++){
                pricerangeMatrix[i-1][j-1] = (double)(pricerangenum-Math.abs(i-j))/(double)pricerangenum;
            }
        }

        //将矩阵写入文件
        MatrixUtil.writeMatrixFile(fileName,pricerangeMatrix,pricerangenum);
    }

    private void UseLengthRangeSimilarity() throws IOException {
        //创建文件
        String fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"uselengthrange.txt";
        System.out.println(fileName);
        File file = new File(fileName);
        if(file.exists()){
            return;
        }
        file.createNewFile();

        Double[][] uselengthrangeMatrix = new Double[uselengthrangenum][uselengthrangenum];
        for(int i=0; i<uselengthrangenum; i++){
            for(int j=0; j<uselengthrangenum; j++){
                uselengthrangeMatrix[i-1][j-1] = (double)(uselengthrangenum-Math.abs(i-j))/(double)uselengthrangenum;
            }
        }

        //将矩阵写入文件
        MatrixUtil.writeMatrixFile(fileName,uselengthrangeMatrix,uselengthrangenum);
    }

    public List<CarHeader> CarRecommend(CarHeader car) throws JSONException {
        CarBrand carBrand = car.getBrand();
        Long carBrandId = carBrand.getId();
        Long carId = car.getId();
        Integer carHot = carBrand.getHot();
        Integer level = car.getLevel();
        Integer pricerange = car.getPriceRange();
        Integer uselengthrange = car.getUseLengthRange();

        Double Wh = 0.3;
        Double Wl = 0.2;
        Double Wp = 0.2;
        Double Wu = 0.3;

        //依次取出数组
       String fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"brand.txt";
        Double[][] hotMatrix = new Double[hotnum][hotnum];
        hotMatrix = MatrixUtil.readMatrixFile(fileName,hotnum);

        fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"level.txt";
        Double[][] levelMatrix = new Double[levelnum][levelnum];
        levelMatrix = MatrixUtil.readMatrixFile(fileName,levelnum);

        fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"pricerange.txt";
        Double[][] pricerangeMatrix = new Double[pricerangenum][pricerangenum];
        pricerangeMatrix = MatrixUtil.readMatrixFile(fileName,pricerangenum);

        fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"uselengthrange.txt";
        Double[][] uselengthrangeMatrix = new Double[uselengthrangenum][uselengthrangenum];
        uselengthrangeMatrix = MatrixUtil.readMatrixFile(fileName,uselengthrangenum);

        //根据车所推荐车的个数
        int recommendCarNum = 10;

        //根据车所推荐的车的集合
        List<CarHeader> CarRecommendCar = null;

        //看过car车的所有用户集合
        List<User> users = null;

        List<UserCarHistory> userCarHistories = userCarHistoryRepository.findDistinctUserByCar(car);
        for (UserCarHistory a: userCarHistories
             ) {
            users.add(a.getUser());
        }
        //按照相似度推荐
        if(users.size()>1){
            //获取用户集合浏览过的所有车的集合
            List<CarHeader> allUsersCars = null;
            for (User a: users
                 ) {
                List<UserCarHistory> userCarHistoryList = userCarHistoryRepository.findByUser(a);
                for (UserCarHistory b:userCarHistoryList
                     ) {
                    allUsersCars.add(b.getCar());
                }
            }

            //按照频次排序
            List<JSONObject> carsFrequency = new ArrayList<JSONObject>();
            Map<Long,Integer> map = new HashMap<Long,Integer>();
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
                Integer rest = recommendCarNum - map.size();
                List<CarHeader> RestCar = carHeaderRepository.findByBrand_Id(carBrandId);
                for(int i=0; i<RestCar.size(); i++){
                    if(! map.containsKey(RestCar.get(i).getId())){
                        CarRecommendCar.add(RestCar.get(i));
                    }
                    if(CarRecommendCar.size() == recommendCarNum) return CarRecommendCar;
                }
            }

            for(Map.Entry<Long,Integer> entry: map.entrySet()){
                JSONObject obj = null;
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
                Integer newHot = newCar.getBrand().getHot();
                Integer newLevel = newCar.getLevel();
                Integer newPriceRange = newCar.getPriceRange();
                Integer newUseLengthRange = newCar.getUseLengthRange();
                double similirity = hotMatrix[carHot][newHot] * Wh + levelMatrix[level][newLevel] * Wl +
                        pricerangeMatrix[pricerange][newPriceRange] * Wp + uselengthrangeMatrix[uselengthrange][newUseLengthRange] * Wu;
                Double value = (double)(obj.getInt("value")/maxFrequency) * similirity;
                JSONObject newobj = null;
                newobj.put("id",obj.getLong("id"));
                newobj.put("value",value);
                carsFrequency.set(i,newobj);
            }
            carsFrequency.sort(new MyComparatorUtil());

            for(int i=0; i<recommendCarNum; i++){
                JSONObject obj = carsFrequency.get(i);
                CarHeader theCar = carHeaderRepository.getOne(obj.getLong("id"));
                CarRecommendCar.add(theCar);
            }
        }
        //按照浏览的车推荐
        else{
            List<CarHeader> InitRecommend = carHeaderRepository.findFirst10ByBrand_IdAndLevelAndPriceRangeAndUseLengthRange(
                    carBrandId, level, pricerange, uselengthrange);
            int rest = recommendCarNum - InitRecommend.size();
            for(int i=0; i<InitRecommend.size(); i++){
                CarHeader c = InitRecommend.get(i);
                if(!c.getId().equals(carId)){
                    CarRecommendCar.add(InitRecommend.get(i));
                }
                else{
                    rest++;
                    recommendCarNum++;
                }
            }
            if(InitRecommend.size() < recommendCarNum){
                List<CarHeader> RestRecommend = carHeaderRepository.findByBrand_Id(carBrandId);
                for(int i=0; i<rest; i++){
                    CarRecommendCar.add(RestRecommend.get(i));
                }
            }
        }
        return CarRecommendCar;
    }

}
