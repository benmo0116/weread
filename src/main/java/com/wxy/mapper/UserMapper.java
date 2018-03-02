package com.wxy.mapper;

import com.wxy.model.User;

public interface UserMapper extends BaseMapper<User>{
    int deleteByPrimaryKey(Integer userid);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //byNickname
//    @Select("SELECT * FROM user WHERE nickname = #{account}") 可以这样写 也可以写mapper.xml 两种方式
    User findByName(String account);
}