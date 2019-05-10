package com.example.oldcar.service;

import com.example.oldcar.domain.LeaseOrder;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.LeaseOrderRepository;
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
public class LeaseOrderService {
    @Autowired
    private LeaseOrderRepository leaseOrderRepository;

    /**
     * 新增
     */
    public LeaseOrder add(LeaseOrder informationContent){
        return leaseOrderRepository.save(informationContent);
    }

    /**
     * 更新
     */
    public LeaseOrder update(LeaseOrder informationContent) {

        // 验证是否存在
        Optional<LeaseOrder> informationContent1 = leaseOrderRepository.findById(informationContent.getId());
        if (informationContent.getId() == null || !informationContent1.isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return leaseOrderRepository.save(informationContent);
    }

    /**
     * 更新订单状态
     */
    public LeaseOrder updateState(Long id,Integer state) {

        // 验证是否存在
        LeaseOrder leaseOrder = leaseOrderRepository.getOne(id);
        if (leaseOrder == null ) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        leaseOrder.setState(state);
        return leaseOrderRepository.save(leaseOrder);
    }

    /**
     * 根据id查询
     */
    public LeaseOrder findById(Long id){
        Optional<LeaseOrder> optional = leaseOrderRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 通过id批量删除
     * @param ids
     */
    @Transactional
    public void deleteInBatch(Long[] ids){
        leaseOrderRepository.deleteInBatch(leaseOrderRepository.findAllById(Arrays.asList(ids)));
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<LeaseOrder> findAll() {
        return leaseOrderRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<LeaseOrder> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            LeaseOrder.class.getDeclaredField(sortFieldName);
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
        return leaseOrderRepository.findAll(pageable);
    }
}
