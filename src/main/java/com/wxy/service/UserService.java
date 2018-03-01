package com.wxy.service;

import com.wxy.model.User;

import java.util.List;

/**
 * @author wxy
 * @create 2018-01-11 09:57
 * @desc ${DESCRIPTION}
 **/
public interface UserService extends IService<User>{

    User findByName(String account);
}
