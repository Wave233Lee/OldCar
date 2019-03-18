package com.example.oldcar.service;

import com.example.oldcar.domain.AccessoriesTag;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.AccessoriesTagRepository;
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
public class AccessoriesTagService {
    @Autowired
    private AccessoriesTagRepository accessoriesTagRepository;

    /**
     * 新增
     */
    public AccessoriesTag add(AccessoriesTag informationContent){
        return accessoriesTagRepository.save(informationContent);
    }

    /**
     * 更新
     */
    public AccessoriesTag update(AccessoriesTag informationContent) {

        // 验证是否存在
        Optional<AccessoriesTag> informationContent1 = accessoriesTagRepository.findById(informationContent.getId());
        if (informationContent.getId() == null || !informationContent1.isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return accessoriesTagRepository.save(informationContent);
    }

    /**
     * 根据id查询
     */
    public AccessoriesTag findById(Long id){
        Optional<AccessoriesTag> optional = accessoriesTagRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 通过id批量删除
     * @param ids
     */
    @Transactional
    public void deleteInBatch(Long[] ids){
        accessoriesTagRepository.deleteInBatch(accessoriesTagRepository.findAllById(Arrays.asList(ids)));
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<AccessoriesTag> findAll() {
        return accessoriesTagRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<AccessoriesTag> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            AccessoriesTag.class.getDeclaredField(sortFieldName);
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
        return accessoriesTagRepository.findAll(pageable);
    }
}
