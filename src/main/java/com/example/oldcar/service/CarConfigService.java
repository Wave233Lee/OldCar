package com.example.oldcar.service;

import com.example.oldcar.domain.CarConfig;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.CarConfigRepository;
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
public class CarConfigService {
    @Autowired
    private CarConfigRepository carConfigRepository;

    /**
     * 新增
     */
    public CarConfig add(CarConfig informationContent){
        return carConfigRepository.save(informationContent);
    }

    /**
     * 更新
     */
    public CarConfig update(CarConfig informationContent) {

        // 验证是否存在
        Optional<CarConfig> informationContent1 = carConfigRepository.findById(informationContent.getId());
        if (informationContent.getId() == null || !informationContent1.isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return informationContent;
    }

    /**
     * 根据id查询
     */
    public CarConfig findById(Long id){
        Optional<CarConfig> optional = carConfigRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 通过id批量删除
     * @param ids
     */
    @Transactional
    public void deleteInBatch(Long[] ids){
        carConfigRepository.deleteInBatch(carConfigRepository.findAllById(Arrays.asList(ids)));
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<CarConfig> findAll() {
        return carConfigRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<CarConfig> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            CarConfig.class.getDeclaredField(sortFieldName);
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
        return carConfigRepository.findAll(pageable);
    }
}
