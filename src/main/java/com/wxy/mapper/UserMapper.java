package com.wxy.mapper;

import com.wxy.model.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User>{
    int deleteByPrimaryKey(Integer userid);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //byNickname
    User findByName(String account);
}