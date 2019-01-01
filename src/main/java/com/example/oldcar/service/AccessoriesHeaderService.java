package com.example.oldcar.service;

import com.example.oldcar.domain.AccessoriesHeader;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.AccessoriesHeaderRepository;
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
public class AccessoriesHeaderService {
    @Autowired
    private AccessoriesHeaderRepository accessoriesHeaderRepository;

    /**
     * 新增
     */
    public AccessoriesHeader add(AccessoriesHeader informationContent){
        return accessoriesHeaderRepository.save(informationContent);
    }

    /**
     * 更新
     */
    public AccessoriesHeader update(AccessoriesHeader informationContent) {

        // 验证是否存在
        Optional<AccessoriesHeader> informationContent1 = accessoriesHeaderRepository.findById(informationContent.getId());
        if (informationContent.getId() == null || !informationContent1.isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return informationContent;
    }

    /**
     * 根据id查询
     */
    public AccessoriesHeader findById(Long id){
        Optional<AccessoriesHeader> optional = accessoriesHeaderRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 通过id批量删除
     * @param ids
     */
    @Transactional
    public void deleteInBatch(Long[] ids){
        accessoriesHeaderRepository.deleteInBatch(accessoriesHeaderRepository.findAllById(Arrays.asList(ids)));
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<AccessoriesHeader> findAll() {
        return accessoriesHeaderRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<AccessoriesHeader> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            AccessoriesHeader.class.getDeclaredField(sortFieldName);
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
        return accessoriesHeaderRepository.findAll(pageable);
    }
}
