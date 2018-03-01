package com.wxy.service.impl;

import com.github.pagehelper.PageHelper;
import com.sun.org.apache.xml.internal.security.Init;
import com.wxy.mapper.UserMapper;
import com.wxy.model.User;
import com.wxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wxy
 * @create 2018-01-11 09:57
 * @desc ${DESCRIPTION}
 **/
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响
    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */

    /**
     * @Cacheable  ：先从缓存中查询，如果没有则查询数据库
       @CacheEvict  :清空缓存
       @CachePut  : 更新的时候刷新到缓存
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Cacheable(value = "user")//3
    @Override
    public List<User> findAll(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAll();
    }

    @CachePut(value = "user", key = "#user.userid")
    @Override
    public int insertOne(User user) {
        return userMapper.insertSelective(user);
    }

    @CacheEvict(value = "user", key = "#user.userid")//2
    @Override
    public int deleteOneByPrimaryKey(Integer userid) {
        return userMapper.deleteByPrimaryKey(userid);
    }

    @Override
    public int updateOne(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public User selectOneByPrimaryKey(Integer userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

    @Override
    public User findByName(String account) {
        return userMapper.findByName(account);
    }

    @Override
    public boolean exist(int id){
        return userMapper.exist(id) == 1;
    }
}
