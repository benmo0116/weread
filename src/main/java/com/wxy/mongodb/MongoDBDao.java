package com.wxy.mongodb;

import com.wxy.model.User;

import java.util.List;

/**
 * @author wxy
 * @create 2018-03-24 16:18
 * @desc ${DESCRIPTION}
 **/
public interface MongoDBDao {
    List<User> findAll();

    User getUser(Integer id);

    void update(User user);

    void insert(User user);

    void insertAll(List<User> users);

    void remove(Integer id);
}
