package com.example.oldcar.service;

import com.example.oldcar.domain.HomePageHeader;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.HomePageHeaderRepository;
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
 * 日期: 2018/12/21
 */
@Service
public class HomePageHeaderService {
    @Autowired
    private HomePageHeaderRepository homePageHeaderRepository;

    /**
     * 新增
     */
    public HomePageHeader add(HomePageHeader informationContent){
        return homePageHeaderRepository.save(informationContent);
    }

    /**
     * 更新
     */
    public HomePageHeader update(HomePageHeader informationContent) {

        // 验证是否存在
        Optional<HomePageHeader> informationContent1 = homePageHeaderRepository.findById(informationContent.getId());
        if (informationContent.getId() == null || !informationContent1.isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return informationContent;
    }

    /**
     * 根据id查询
     */
    public HomePageHeader findById(Long id){
        Optional<HomePageHeader> optional = homePageHeaderRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 通过id批量删除
     * @param ids
     */
    @Transactional
    public void deleteInBatch(Long[] ids){
        homePageHeaderRepository.deleteInBatch(homePageHeaderRepository.findAllById(Arrays.asList(ids)));
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<HomePageHeader> findAll() {
        return homePageHeaderRepository.findAll();
    }

    /**
     * 查询所有-分页
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<HomePageHeader> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            HomePageHeader.class.getDeclaredField(sortFieldName);
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
        return homePageHeaderRepository.findAll(pageable);
    }

}
