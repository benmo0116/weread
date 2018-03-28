package com.wxy.cache.redis;

import org.springframework.stereotype.Service;

/**
 * @author wxy
 * @create 2018-03-27 13:48
 * @desc ${DESCRIPTION}
 **/
@Service
public class RedisServiceImpl<T> extends IRedisService<T> {
    private static final String REDIS_KEY = "TEST_REDIS_KEY";

    @Override
    protected String getRedisKey() {
        return this.REDIS_KEY;
    }
}
