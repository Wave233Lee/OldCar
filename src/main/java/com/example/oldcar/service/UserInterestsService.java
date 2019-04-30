package com.example.oldcar.service;

import com.example.oldcar.domain.*;
import com.example.oldcar.repository.*;
import com.example.oldcar.utils.JsonUtils;
import com.sun.xml.internal.fastinfoset.util.CharArrayIntMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserInterestsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessLogRepository accessLogRepository;

    @Autowired
    private CarHeaderRepository carHeaderRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarSeriesRepository carSeriesRepository;

    @Autowired
    private BuyCarRepository buyCarRepository;

    @Autowired
    private  UserCollectionRepository userCollectionRepository;

    public Integer Wc = 3;

    public Integer Wb = 5;

    private void Init() throws IOException, JSONException {
        //获取最新购买车辆的时间
        String timeLog = buyCarRepository.findFirstByOrderByIdDesc().getDate().toString();

        //创建文件
        String path = "E:/json/";
        System.out.println(path);
        String fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"init.json";
        File file = new File(fileName);
        if(file.exists()){
            //读取json文件
            JSONObject oldJson = new JSONObject(JsonUtils.readJsonFile(fileName));

            if(oldJson.get("timeLog") != null && oldJson.get("timeLog").equals(timeLog))
                return;
        }
            file.createNewFile();


        Integer carsnum = buyCarRepository.findAll().size();

        List<CarBrand> carBrand = carBrandRepository.findAll();
        List<CarSeries> carSeries = carSeriesRepository.findAll();

        //初始化品牌评分
        JSONArray jsonBrand = new JSONArray();
        for (CarBrand b:carBrand
        ) {
            Long index = b.getId();
            Double value = 0.0;
            if(carsnum != 0)
                value = (double)buyCarRepository.findByCar_Brand(b).size()/(double)carsnum;
            JSONObject tmp = null;
            tmp.put("id",index);
            tmp.put("value",value);
            jsonBrand.put(tmp);
        }

        //初始化车系评分
        JSONArray jsonSeries = new JSONArray();
        for (CarSeries b:carSeries
        ) {
            Long index = b.getId();
            Double value = 0.0;
            if(carsnum != 0)
                value = (double)buyCarRepository.findByCar_Series(b).size()/(double)carsnum;
            JSONObject tmp = null;
            tmp.put("id",index);
            tmp.put("value",value);
            jsonSeries.put(tmp);
        }

        //初始化车级评分
        JSONArray jsonLevel = new JSONArray();
        Integer levelType = 5;
        for(int i=0; i<levelType; i++){
            Double value = 0.0;
            if(carsnum != 0)
                value = (double)buyCarRepository.findByCar_Level(i).size()/(double)carsnum;
            JSONObject tmp = null;
            tmp.put("id",i);
            tmp.put("value",value);
            jsonLevel.put(tmp);
        }

        //初始化价格区间评分
        JSONArray jsonPriceRange = new JSONArray();
        Integer priceRangeType = 10;
        for(int i=0; i<priceRangeType; i++){
            Double value = 0.0;
            if(carsnum != 0)
                value = (double)buyCarRepository.findByCar_PriceRange(i).size()/(double)carsnum;
            JSONObject tmp = null;
            tmp.put("id",i);
            tmp.put("value",value);
            jsonPriceRange.put(tmp);
        }


        //初始化车龄区间评分
        JSONArray jsonUseLengthRange = new JSONArray();
        Integer useLengthRangeType = 5;
        for(int i=0; i<useLengthRangeType; i++){
            Double value = 0.0;
            if(carsnum != 0)
                value = (double)buyCarRepository.findByCar_UseLengthRange(i).size()/(double)carsnum;
            JSONObject tmp = null;
            tmp.put("id",i);
            tmp.put("value",value);
            jsonUseLengthRange.put(tmp);
        }

        //将初始化json数组写入文件
        JSONObject jsonInit = null;
        jsonInit.put("timeLog",timeLog);
        jsonInit.put("carBrand",jsonBrand);
        jsonInit.put("carSeries",jsonSeries);
        jsonInit.put("carLevel",jsonLevel);
        jsonInit.put("priceRange",jsonPriceRange);
        jsonInit.put("useLengthRange",jsonUseLengthRange);

        String initJsonString = jsonInit.toString();
        JsonUtils.writeJsonFile(initJsonString, fileName);
    }

    private void Final(User user) throws JSONException, IOException {

        if(user == null) return;
        //则拷贝副本

        //获取最新收藏车辆的时间
        String collection_timeLog = userCollectionRepository.findFirstByUserAndTypeOrderByIdDesc(user,2).getDate().toString();
        //获取最新购买车辆的时间
        String buy_timeLog = buyCarRepository.findFirstByBuyerOrderByIdDesc(user).getDate().toString();

        String path = "E:/json/";
        System.out.println(path);
        String fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"final.json";
        File file = new File(fileName);
        if(file.exists()){
            //读取json文件
            JSONObject oldJson = new JSONObject(JsonUtils.readJsonFile(fileName));
            if(oldJson.get("collection_timeLog") != null && oldJson.get("collection_timeLog").equals(collection_timeLog)
                    && oldJson.get("buy_timeLog") != null && oldJson.get("buy_timeLog").equals(buy_timeLog))
                return;
        }
        file.createNewFile();


        //收藏的二手车集合
        List<UserCollection> CollectList= user.getCollections();
        List<CarHeader> CollectCars = null ;
        for (int i=0; i<CollectList.size(); i++) {
            if (CollectList.get(i).getType() == 2) {
                CollectCars.add( carHeaderRepository.getOne(CollectList.get(i).getCollectId()) );
            }
        }
        //收藏数
        Integer CollectNum = CollectCars.size();

        //购买的二手车集合
        List<CarHeader> BuyList= null;
        for (BuyCar b:buyCarRepository.findByBuyer(user)
            ) {
            BuyList.add(b.getCar());
        }
        //购买数
        Integer BuyNum = BuyList.size();

        fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"init.json";
        //读取json文件
        JSONObject oldJson = new JSONObject(JsonUtils.readJsonFile(fileName));
        JSONArray jsonBrand = oldJson.getJSONArray("carBrand");
        JSONArray jsonSeries = oldJson.getJSONArray("carSeries");

/*        if(jsonBrand.length()>0){
            for(int i=0; i<jsonBrand.length(); i++){
                JSONObject newJson = jsonBrand.getJSONObject(i);
                carHeaderRepository.findByBrand_Id(newJson.getLong("id"));
            }
        }*/

        Map<CarBrand,Integer> carBrandIntegerMap = null;
        List<CarBrand> carBrands = null;
        for (CarHeader a:CollectCars
             ) {
            if (carBrands.contains(a.getBrand())){
                continue;
            }
            else{
                carBrands.add(a.getBrand());
            }
        }

        for (CarBrand a:carBrands
             ) {
            Integer flag = 0;
            for (CarHeader b:CollectCars
                 ) {
                if(b.getBrand().equals(a));
                flag++;
            }
            carBrandIntegerMap.put(a,flag);
        }


        JSONObject jsonFinal = null;
        jsonFinal.put("collection_timeLog",collection_timeLog);
        jsonFinal.put("buy_timeLog",buy_timeLog);
        jsonFinal.put("carBrand",jsonBrand);
        jsonFinal.put("carSeries",jsonSeries);

        fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"final.json";
        String finalJsonString = jsonFinal.toString();
        JsonUtils.writeJsonFile(finalJsonString, fileName);
    }

    private List<CarHeader> Recommand(User user){

        return null;
    }
}
