package com.wxy.service;

import com.wxy.model.User;

import java.util.List;

/**
 * @author wxy
 * @create 2018-01-12 10:27
 * @desc ${DESCRIPTION}
 **/
public interface IService<T> {
    List<T> findAll(int pageNum, int pageSize);

    int insertOne(T t);

    int deleteOneByPrimaryKey(Integer userid);

    int updateOne(T t);

    T selectOneByPrimaryKey(Integer userid);

    boolean exist(int id);
}
