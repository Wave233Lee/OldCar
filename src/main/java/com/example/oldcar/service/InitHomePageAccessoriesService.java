package com.example.oldcar.service;

import com.example.oldcar.domain.InitHomePageAccessories;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.InitHomePageAccessoriesRepository;
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
public class InitHomePageAccessoriesService {
    @Autowired
    private InitHomePageAccessoriesRepository initHomePageAccessoriesRepository;

    /**
     * 新增
     */
    public InitHomePageAccessories add(InitHomePageAccessories informationContent){
        return initHomePageAccessoriesRepository.save(informationContent);
    }

    /**
     * 更新
     */
    public InitHomePageAccessories update(InitHomePageAccessories informationContent) {

        // 验证是否存在
        Optional<InitHomePageAccessories> informationContent1 = initHomePageAccessoriesRepository.findById(informationContent.getId());
        if (informationContent.getId() == null || !informationContent1.isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return informationContent;
    }

    /**
     * 根据id查询
     */
    public InitHomePageAccessories findById(Long id){
        Optional<InitHomePageAccessories> optional = initHomePageAccessoriesRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 通过id批量删除
     * @param ids
     */
    @Transactional
    public void deleteInBatch(Long[] ids){
        initHomePageAccessoriesRepository.deleteInBatch(initHomePageAccessoriesRepository.findAllById(Arrays.asList(ids)));
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<InitHomePageAccessories> findAll() {
        return initHomePageAccessoriesRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<InitHomePageAccessories> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            InitHomePageAccessories.class.getDeclaredField(sortFieldName);
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
        return initHomePageAccessoriesRepository.findAll(pageable);
    }
}
