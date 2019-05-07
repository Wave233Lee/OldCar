package com.example.oldcar.service;

import com.example.oldcar.domain.CarHeader;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.CarHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/27
 */
@Service
public class CarHeaderService {
    @Autowired
    private CarHeaderRepository carHeaderRepository;

    /**
     * 新增
     */
    public CarHeader add(CarHeader informationContent){
        Double carprice = informationContent.getBuyPrice();
        Integer carage = informationContent.getUseLength();
        //价格区间转换
        if(carprice<=3.0){
            informationContent.setPriceRange(0);
        }
        else if(carprice>3.0 && carprice<=5.0){
            informationContent.setPriceRange(1);
        }
        else if(carprice>5.0 && carprice<=8.0){
            informationContent.setPriceRange(2);
        }
        else if(carprice>8.0 && carprice<=10.0){
            informationContent.setPriceRange(3);
        }
        else if(carprice>10.0 && carprice<=15.0){
            informationContent.setPriceRange(4);
        }
        else if(carprice>15.0 && carprice<=20.0){
            informationContent.setPriceRange(5);
        }
        else if(carprice>20.0 && carprice<=30.0){
            informationContent.setPriceRange(6);
        }
        else if(carprice>30.0 && carprice<=50.0){
            informationContent.setPriceRange(7);
        }
        else if(carprice>50.0 && carprice<=100.0){
            informationContent.setPriceRange(8);
        }
        else
            informationContent.setPriceRange(9);
        //车龄区间转换
        if(carage<=2){
            informationContent.setUseLengthRange(0);
        }
        else if(carage <= 4){
            informationContent.setUseLengthRange(1);
        }
        else if(carage <= 6){
            informationContent.setUseLengthRange(2);
        }
        else if(carage <= 8){
            informationContent.setUseLengthRange(3);
        }
        else
            informationContent.setUseLengthRange(4);

        return carHeaderRepository.save(informationContent);
    }

    /**
     * 更新
     */
    public CarHeader update(CarHeader informationContent) {

        // 验证是否存在
        Optional<CarHeader> informationContent1 = carHeaderRepository.findById(informationContent.getId());
        if (informationContent.getId() == null || !informationContent1.isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return carHeaderRepository.save(informationContent);
    }

    /**
     * 根据id查询
     */
    public CarHeader findById(Long id){
        Optional<CarHeader> optional = carHeaderRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 通过id批量删除
     * @param ids
     */
    @Transactional
    public void deleteInBatch(Long[] ids){
        carHeaderRepository.deleteInBatch(carHeaderRepository.findAllById(Arrays.asList(ids)));
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<CarHeader> findAll() {
        return carHeaderRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<CarHeader> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            CarHeader.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        return carHeaderRepository.findAll(pageable);
    }

    /**
     * 通过品牌查询
     */
    public Page<CarHeader> findByBrandByPage(Long brandId,Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            CarHeader.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        return carHeaderRepository.findByBrand_Id(brandId,pageable);
    }

    /**
     * 通过级别查询
     */
    public Page<CarHeader> findByLevelByPage(Integer level,Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            CarHeader.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为level
            sortFieldName = "level";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        return carHeaderRepository.findByLevel(level,pageable);
    }

    /**
     * 通过价格区间查询
     */
    public Page<CarHeader> findByBuyPriceBetweenByPage(Double start,Double end,Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            CarHeader.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为price
            sortFieldName = "buyPrice";
        }

        Sort sort = null;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = PageRequest.of(page, size, sort);
        return carHeaderRepository.findByBuyPriceBetween(start,end,pageable);
    }
}
