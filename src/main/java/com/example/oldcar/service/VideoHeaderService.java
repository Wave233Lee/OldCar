package com.example.oldcar.service;

import com.example.oldcar.domain.VideoHeader;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.VideoHeaderRepository;
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
public class VideoHeaderService {
    @Autowired
    private VideoHeaderRepository videoHeaderRepository;


    /**
     * 新增
     */
    public VideoHeader add(VideoHeader informationContent){
        return videoHeaderRepository.save(informationContent);
    }

    /**
     * 更新
     */
    public VideoHeader update(VideoHeader informationContent) {

        // 验证是否存在
        Optional<VideoHeader> informationContent1 = videoHeaderRepository.findById(informationContent.getId());
        if (informationContent.getId() == null || !informationContent1.isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return videoHeaderRepository.save(informationContent);
    }

    /**
     * 根据id查询
     */
    public VideoHeader findById(Long id){
        Optional<VideoHeader> optional = videoHeaderRepository.findById(id);
        return optional.orElse(null);
    }

    /**
     * 通过id批量删除
     * @param ids
     */
    @Transactional
    public void deleteInBatch(Long[] ids){
        videoHeaderRepository.deleteInBatch(videoHeaderRepository.findAllById(Arrays.asList(ids)));
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<VideoHeader> findAll() {
        return videoHeaderRepository.findAll();
    }

    /**
     * 查询所有-分页
     */
    public Page<VideoHeader> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            VideoHeader.class.getDeclaredField(sortFieldName);
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
        return videoHeaderRepository.findAll(pageable);
    }
}
