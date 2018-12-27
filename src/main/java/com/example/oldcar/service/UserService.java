package com.example.oldcar.service;

import com.example.oldcar.domain.User;
import com.example.oldcar.exception.CarException;
import com.example.oldcar.exception.EnumExceptions;
import com.example.oldcar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

/**
 * 说明:
 *
 * @author WaveLee
 * 日期: 2018/12/12
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * 新增
     *
     * @param login
     * @return
     */
    public User save(User login) {

        // 验证是否存在
        if (login == null || (login.getId() != null && userRepository.findById(login.getId()).isPresent())) {
            throw new CarException(EnumExceptions.ADD_FAILED_DUPLICATE);
        }

        return userRepository.save(login);
    }

    /**
     * 更新
     *
     * @param login
     * @return
     */
    public User update(User login) {

        // 验证是否存在
        if (login == null || login.getId() == null || !userRepository.findById(login.getId()).isPresent()) {
            throw new CarException(EnumExceptions.UPDATE_FAILED_NOT_EXIST);
        }

        return userRepository.save(login);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Long id) {

        // 验证是否存在
        if (!userRepository.findById(id).isPresent()) {
            throw new CarException(EnumExceptions.DELETE_FAILED_NOT_EXIST);
        }
        userRepository.deleteById(id);
    }

    /**
     * 批量删除
     *
     * @param users
     */
    @Transactional
    public void deleteInBatch(Collection<User> users) {
        userRepository.deleteInBatch(users);
    }

    /**
     * 通过编码查询
     *
     * @param id
     * @return
     */
    public User findOne(Long id) {

        // 验证是否存在
        if (!userRepository.findById(id).isPresent()) {
            throw new CarException(EnumExceptions.DATA_NOT_EXIST);
        }
        return userRepository.getOne(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 查询所有-分页
     *
     * @param page
     * @param size
     * @param sortFieldName
     * @param asc
     * @return
     */
    public Page<User> findAllByPage(Integer page, Integer size, String sortFieldName, Integer asc) {

        // 判断排序字段名是否存在
        try {
            User.class.getDeclaredField(sortFieldName);
        } catch (Exception e) {
            // 如果不存在就设置为id
            sortFieldName = "id";
        }
        Sort sort;
        if (asc == 0) {
            sort = new Sort(Sort.Direction.DESC, sortFieldName);
        } else {
            sort = new Sort(Sort.Direction.ASC, sortFieldName);
        }

        Pageable pageable = new PageRequest(page, size, sort);
        return userRepository.findAll(pageable);
    }

}
