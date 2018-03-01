package com.wxy.mapper;

import java.util.List;

/**
 * @author wxy
 * @create 2018-01-12 11:18
 * @desc 补充公共方法 已有的公共方法不再抽取
 **/
public interface BaseMapper<T> {
//    int insert(T t);
//    int insertSelective(T t);

    List<T> selectAll();
    int exist(int id);
}
