package com.wxy.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wxy
 * @create 2018-03-27 13:46
 * @desc ${DESCRIPTION}
 **/
public abstract class IRedisService<T> {
    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;
    @Resource
    protected HashOperations<String, String, T> hashOperations;
    @Resource
    protected ValueOperations<String, String> valueOperations;
    @Resource
    protected ListOperations<String, T> listOperations;
    @Resource
    protected SetOperations<String, T> setOperations;
    @Resource
    protected ZSetOperations<String, T> zSetOperations;

    /**
     * 存入redis中的key
     *
     * @return
     */
    protected abstract String getRedisKey();

    //    ============================================================

    //    =============================================string================
    public void set(String key, String value) {
        valueOperations.set(key, value);
    }

    public String get(String key) {
        return valueOperations.get(key);
    }

    //    =======================================================list
    public void lset(String key, T value, long expire) {
        listOperations.set(key, expire, value);
    }

    public void rightPushAll(String key, List<T> vars) {
        listOperations.rightPushAll(key, vars);
    }

    public void lpush(String key, T value) {
        listOperations.rightPush(key, value);
    }

    public T lpop(String key) {
        return listOperations.rightPop(key);
    }

    //    =======================================================set
    public void sadd(String key, T value) {
        setOperations.add(key, value);
    }

    //移除并返回集合中的一个随机元素。
    public T spop(String key) {
        return setOperations.pop(key);
    }

    //不移除返回集合中的一个随机元素。
    public T srandomMember(String key) {
        return setOperations.randomMember(key);
    }
//    =======================================================zset

    public void zsadd(String key, T value, double score) {
        zSetOperations.add(key, value, score);
    }
//    ==================================================hash==================

    /**
     * 添加
     *
     * @param key    key
     * @param doamin 对象
     * @param expire 过期时间(单位:秒),传入 -1 时表示不设置过期时间
     */

    public void hput(String key, T doamin, long expire) {
        hashOperations.put(getRedisKey(), key, doamin);
        if (expire != -1) {
            redisTemplate.expire(getRedisKey(), expire, TimeUnit.SECONDS);
        }
    }

    /**
     * 删除
     *
     * @param key 传入key的名称
     */
    public void hremove(String key) {
        hashOperations.delete(getRedisKey(), key);
    }

    /**
     * 查询
     *
     * @param key 查询的key
     * @return
     */
    public T hget(String key) {
        return hashOperations.get(getRedisKey(), key);
    }

    /**
     * 获取当前redis库下所有对象
     *
     * @return
     */
    public List<T> hgetAll() {
        return hashOperations.values(getRedisKey());
    }

    /**
     * 查询查询当前redis库下所有key
     *
     * @return
     */
    public Set<String> hgetKeys() {
        return hashOperations.keys(getRedisKey());
    }

    /**
     * 判断key是否存在redis中
     *
     * @param key 传入key的名称
     * @return
     */
    public boolean ishKeyExists(String key) {
        return hashOperations.hasKey(getRedisKey(), key);
    }

    /**
     * 查询当前key下缓存数量
     *
     * @return
     */
    public long hcount() {
        return hashOperations.size(getRedisKey());
    }

    /**
     * 清空redis
     */
    public void hempty() {
        Set<String> set = hashOperations.keys(getRedisKey());
        set.stream().forEach(key -> hashOperations.delete(getRedisKey(), key));
    }
}