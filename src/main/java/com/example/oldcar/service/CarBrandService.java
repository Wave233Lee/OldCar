package com.example.oldcar.service;

import com.example.oldcar.domain.CarBrand;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.CarBrandRepository;
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
 * 日期: 2018/12/28
 */
@Service
public class CarBrandService {
    @Autowired
    private CarBrandRepository carBrandRepository;

    /**
     * 新增
     */
    public CarBrand add(CarBrand informationContent){
        return carBrandRepository.save(informationContent);
    }

    /**
     * 更新
     */
    public CarBrand update(CarBrand informationContent) {

        // 验证是否存在
        Optional<CarBrand> informationContent1 = carBrandRepository.findById(informationContent.getId());
        if (informationContent.getId() == null || !informationContent1.isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return carBrandRepository.save(informationContent);
    }

    /**
     * 根据id查询
     */
    public CarBrand findById(Long id){
        Optional<CarBrand> optional = carBrandRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 通过id批量删除
     * @param ids
     */
    @Transactional
    public void deleteInBatch(Long[] ids){
        carBrandRepository.deleteInBatch(carBrandRepository.findAllById(Arrays.asList(ids)));
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<CarBrand> findAll() {
        return carBrandRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<CarBrand> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            CarBrand.class.getDeclaredField(sortFieldName);
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
        return carBrandRepository.findAll(pageable);
    }
}
