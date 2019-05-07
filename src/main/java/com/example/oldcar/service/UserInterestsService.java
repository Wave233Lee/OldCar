package com.example.oldcar.service;

import com.example.oldcar.domain.*;
import com.example.oldcar.repository.*;
import com.example.oldcar.utils.JsonUtil;
import com.example.oldcar.utils.MyComparatorUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserInterestsService {
    @Autowired
    private CarHeaderRepository carHeaderRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarLevelRepository carLevelRepository;

    @Autowired
    private BuyCarRepository buyCarRepository;

    @Autowired
    private  UserCarCollectionRepository userCarCollectionRepository;

    public String path = "E:/json/";

    private void Init() throws IOException, JSONException {
        //获取最新购买车辆的时间
        String timeLog = buyCarRepository.findFirstByOrderByIdDesc().getDate().toString();

        //创建文件
        String fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"init.json";
        System.out.println(fileName);
        File file = new File(fileName);
        if(file.exists()){
            //读取json文件
            JSONObject oldJson = new JSONObject(JsonUtil.readJsonFile(fileName));

            if(oldJson.get("timeLog") != null && oldJson.get("timeLog").equals(timeLog))
                return;
        }
            file.createNewFile();


        Integer carsnum = buyCarRepository.findAll().size();

        List<CarBrand> carBrand = carBrandRepository.findAll();

        //初始化品牌评分
        JSONArray jsonBrand = new JSONArray();
        for (CarBrand b:carBrand
        ) {
            Long index = b.getId();
            double value = 0.0;
            if(carsnum != 0)
                value = (double)buyCarRepository.findByCar_Brand(b).size()/(double)carsnum;
            JSONObject tmp = null;
            tmp.put("id",index);
            tmp.put("value",value);
            jsonBrand.put(tmp);
        }

        //初始化车级评分
        JSONArray jsonLevel = new JSONArray();
        int levelType = 5;
        for(int i=0; i<levelType; i++){
            double value = 0.0;
            if(carsnum != 0)
                value = (double)buyCarRepository.findByCar_Level(i).size()/(double)carsnum;
            JSONObject tmp = null;
            tmp.put("id",i);
            tmp.put("value",value);
            jsonLevel.put(tmp);
        }

        //初始化价格区间评分
        JSONArray jsonPriceRange = new JSONArray();
        int priceRangeType = 10;
        for(int i=0; i<priceRangeType; i++){
            double value = 0.0;
            if(carsnum != 0)
                value = (double)buyCarRepository.findByCar_PriceRange(i).size()/(double)carsnum;
            JSONObject tmp = null;
            tmp.put("id",i);
            tmp.put("value",value);
            jsonPriceRange.put(tmp);
        }


        //初始化车龄区间评分
        JSONArray jsonUseLengthRange = new JSONArray();
        int useLengthRangeType = 5;
        for(int i=0; i<useLengthRangeType; i++){
            double value = 0.0;
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
        jsonInit.put("carLevel",jsonLevel);
        jsonInit.put("priceRange",jsonPriceRange);
        jsonInit.put("useLengthRange",jsonUseLengthRange);

        String initJsonString = jsonInit.toString();
        JsonUtil.writeJsonFile(initJsonString, fileName);
    }

    private void Final(User user) throws JSONException, IOException {

        if(user == null) return;

        //获取最新收藏车辆的时间
        String collection_timeLog = userCarCollectionRepository.findFirstByUserOrderByIdDesc(user).getDate().toString();
        //获取最新购买车辆的时间
        String buy_timeLog = buyCarRepository.findFirstByBuyerOrderByIdDesc(user).getDate().toString();

        String fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"final.json";
        System.out.println(fileName);
        File file = new File(fileName);
        if(file.exists()){
            //读取json文件
            JSONObject oldJson = new JSONObject(JsonUtil.readJsonFile(fileName));
            if(oldJson.get("collection_timeLog") != null && oldJson.get("collection_timeLog").equals(collection_timeLog)
                    && oldJson.get("buy_timeLog") != null && oldJson.get("buy_timeLog").equals(buy_timeLog))
                return;
        }
            file.createNewFile();


        fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"init.json";
        //读取json文件
        JSONObject oldJson = new JSONObject(JsonUtil.readJsonFile(fileName));
        JSONArray jsonBrand = oldJson.getJSONArray("carBrand");
        JSONArray jsonLevel = oldJson.getJSONArray("carLevel");
        JSONArray jsonPriceRange = oldJson.getJSONArray("priceRange");
        JSONArray jsonUseLengthRange = oldJson.getJSONArray("useLengthRange");

        Integer collectionNum = userCarCollectionRepository.findByUser(user).size();
        Integer buyNum = buyCarRepository.findByBuyer(user).size();
        double Wo = 0.3;
        double Wn = 0.7;
        Integer Wc = 3;
        Integer Wb = 5;
        int base = Wc * collectionNum + Wb * buyNum;

        //更新品牌评分
        if(jsonBrand.length()>0){
            for(int i=0; i<jsonBrand.length(); i++){
                JSONObject newJson = jsonBrand.getJSONObject(i);
                CarBrand a = carBrandRepository.getOne(newJson.getLong("id"));
                Double newvalue = Wo * newJson.getDouble("value") + Wn * (double)((userCarCollectionRepository.findByUserAndCar_Brand(user,a).size() * Wc
                + buyCarRepository.findByBuyerAndCar_Brand(user,a).size() * Wb)/base);
                newJson.put(newJson.getString("id"),newvalue);
            }
        }

        //更新车级评分
        if(jsonLevel.length()>0){
            for(int i=0; i<jsonLevel.length(); i++){
                JSONObject newJson = jsonLevel.getJSONObject(i);
                Integer a = newJson.getInt("id");
                Double newvalue = Wo * newJson.getDouble("value") + Wn * (double)((userCarCollectionRepository.findByUserAndCar_Level(user,carLevelRepository.getOne(a)).size() * Wc
                        + buyCarRepository.findByBuyerAndCar_Level(user,a).size() * Wb)/base);
                newJson.put(newJson.getString("id"),newvalue);
            }
        }

        //更新价格区间评分
        if(jsonPriceRange.length()>0){
            for(int i=0; i<jsonPriceRange.length(); i++){
                JSONObject newJson = jsonPriceRange.getJSONObject(i);
                Integer a = newJson.getInt("id");
                Double newvalue = Wo * newJson.getDouble("value") + Wn * (double)((userCarCollectionRepository.findByUserAndCar_PriceRange(user,a).size() * Wc
                        + buyCarRepository.findByBuyerAndCar_PriceRange(user,a).size() * Wb)/base);
                newJson.put(newJson.getString("id"),newvalue);
            }
        }

        //更新车龄区间评分
        if(jsonUseLengthRange.length()>0){
            for(int i=0; i<jsonUseLengthRange.length(); i++){
                JSONObject newJson = jsonUseLengthRange.getJSONObject(i);
                Integer a = newJson.getInt("id");
                Double newvalue = Wo * newJson.getDouble("value") + Wn * (double)((userCarCollectionRepository.findByUserAndCar_UseLengthRange(user,a).size() * Wc
                        + buyCarRepository.findByBuyerAndCar_UseLengthRange(user,a).size() * Wb)/base);
                newJson.put(newJson.getString("id"),newvalue);
            }
        }

        JSONObject jsonFinal = null;
        jsonFinal.put("collection_timeLog",collection_timeLog);
        jsonFinal.put("buy_timeLog",buy_timeLog);
        jsonFinal.put("carBrand",jsonBrand);
        jsonFinal.put("carLevel",jsonLevel);
        jsonFinal.put("priceRange",jsonPriceRange);
        jsonFinal.put("useLengthRange",jsonUseLengthRange);

        fileName = path+ File.pathSeparator+"File"+
                File.pathSeparator+"final.json";
        String finalJsonString = jsonFinal.toString();
        JsonUtil.writeJsonFile(finalJsonString, fileName);
    }

    public List<CarHeader> UserRecommend(User user) throws JSONException {
        int recommendCarNum = 10;
        List<CarHeader> UserRecommendCar = null;
        String fileName;
        if(user == null){
            fileName = path+ File.pathSeparator+"File"+
                    File.pathSeparator+"init.json";
            System.out.println(fileName);
        }
        else{
            fileName = path+ File.pathSeparator+"File"+
                    File.pathSeparator+"final.json";
            System.out.println(fileName);
        }

        JSONObject useJson = new JSONObject(JsonUtil.readJsonFile(fileName));
        JSONArray jsonBrand = useJson.getJSONArray("carBrand");
        JSONArray jsonLevel = useJson.getJSONArray("carLevel");
        JSONArray jsonPriceRange = useJson.getJSONArray("priceRange");
        JSONArray jsonUseLengthRange = useJson.getJSONArray("useLengthRange");

        //将车品牌降序排序
        List<JSONObject> brandList = new ArrayList<JSONObject>();
        for(int i=0; i<jsonBrand.length(); i++) {
            JSONObject jsonObj = jsonBrand.getJSONObject(i);
            brandList.add(jsonObj);
        }
        brandList.sort(new MyComparatorUtil());

        //筛选出需要推荐的车品牌和个数
        JSONArray jsonRecommendBrand = new JSONArray();
        int flag = 0;
        for (JSONObject a:brandList
             ) {
            //向上取整
            int num = (int)Math.ceil((a.getDouble("value") * (double)recommendCarNum));
            flag+=num;
            if(flag<recommendCarNum){
                a.put("id",a.getLong("id"));
                a.put("value",num);
                jsonRecommendBrand.put(a);
            }
            else{
                num = num - (flag-recommendCarNum);
                a.put("id",a.getLong("id"));
                a.put("value",num);
                jsonRecommendBrand.put(a);
                break;
            }
        }

        //取车级评分最大值
        List<JSONObject> levelList = new ArrayList<JSONObject>();
        for(int i=0; i<jsonLevel.length(); i++) {
            JSONObject jsonObj = jsonLevel.getJSONObject(i);
            levelList.add(jsonObj);
        }
        levelList.sort(new MyComparatorUtil());
        Integer maxLevel = levelList.get(0).getInt("id");

        //取价格区间评分最大值
        List<JSONObject> pricerangeList = new ArrayList<JSONObject>();
        for(int i=0; i<jsonPriceRange.length(); i++) {
            JSONObject jsonObj = jsonPriceRange.getJSONObject(i);
            pricerangeList.add(jsonObj);
        }
        pricerangeList.sort(new MyComparatorUtil());
        Integer maxPriceRange = pricerangeList.get(0).getInt("id");

        //取车龄区间评分最大值
        List<JSONObject> uselengthrangeList = new ArrayList<JSONObject>();
        for(int i=0; i<jsonUseLengthRange.length(); i++) {
            JSONObject jsonObj = jsonUseLengthRange.getJSONObject(i);
            uselengthrangeList.add(jsonObj);
        }
        uselengthrangeList.sort(new MyComparatorUtil());
        Integer maxUseLengthRange = uselengthrangeList.get(0).getInt("id");

        for(int i=0; i<jsonRecommendBrand.length(); i++){
            JSONObject obj = jsonRecommendBrand.getJSONObject(i);
            int num = obj.getInt("value");
            Long id = obj.getLong("id");
            List<CarHeader> InitRecommend = carHeaderRepository.findFirst10ByBrand_IdAndLevelAndPriceRangeAndUseLengthRange(id, maxLevel, maxPriceRange, maxUseLengthRange);
            if (InitRecommend.size()<num){
                UserRecommendCar.addAll(InitRecommend);

                int rest = num - InitRecommend.size();
                List<CarHeader> RestRecommend = carHeaderRepository.findFirst10ByBrand_IdAndLevelNotAndPriceRangeAndUseLengthRange(id, maxLevel, maxPriceRange, maxUseLengthRange);
                if (RestRecommend.size()<rest){
                    UserRecommendCar.addAll(RestRecommend);

                    int rrest = rest - RestRecommend.size();
                    List<CarHeader> RrestRecommend = carHeaderRepository.findFirst10ByBrand_IdAndLevelNotAndPriceRangeNotAndUseLengthRange(id, maxLevel, maxPriceRange, maxUseLengthRange);
                    if (RrestRecommend.size() < rrest){
                        UserRecommendCar.addAll(RrestRecommend);

                        int rrrest = rrest - RrestRecommend.size();
                        List<CarHeader> RrrestRecommend = carHeaderRepository.findFirst10ByBrand_IdAndLevelNotAndPriceRangeNotAndUseLengthRangeNot(id, maxLevel, maxPriceRange, maxUseLengthRange);
                        for(int j=0; j<rrrest; j++){
                            UserRecommendCar.add(RrrestRecommend.get(j));
                        }
                    }
                }
            }
            else{
                for(int j=0; j<num; j++){
                    UserRecommendCar.add(InitRecommend.get(j));
                }
            }
        }
        return UserRecommendCar;
    }
}
