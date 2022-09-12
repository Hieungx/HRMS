package com.auth.domain.configuration.redisConfiguration;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component("singleRedisBean")
public class RedisRepository {
    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean delete(final String key) {
        redisTemplate.delete(key);
        return Boolean.TRUE;
    }

    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean set(final String key, final int expiresTime, final String value) {
        redisTemplate.opsForValue().set(key, value);
        boolean flag = Boolean.TRUE;
        if (expiresTime > 0) {
            flag = redisTemplate.expire(key, expiresTime, TimeUnit.SECONDS);
        }
        return flag;
    }
    public boolean setObject(final String key, final int expiresTime, final JSONObject value) {
        redisTemplate.opsForValue().set(key, value.toString());
        boolean flag = Boolean.TRUE;
        if (expiresTime > 0) {
            flag = redisTemplate.expire(key, expiresTime, TimeUnit.MINUTES);
        }
        return flag;
    }

    public boolean set(final String key, final String value) {
        return set(key, 0, value);
    }

    public Boolean exists(final String key) {
        return hasKey(key);
    }

    public void setex(String key, int timeout, String value) {
        set(key, timeout, value);
    }

    public boolean expire(final String key, final int seconds) {
        return redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    public boolean delete(final String key, final String shardingKey) {
        redisTemplate.delete(key);
        return Boolean.TRUE;
    }

    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hset(String key, String item, Object value, int time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long ttl(final String lockKey) {
        return redisTemplate.getExpire(lockKey, TimeUnit.SECONDS);
    }

    public long increment(final String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    public int countKeys(String keyPrefix) {
        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
        return keys.size();
    }

    public void deleteKeysLike(String keyPrefix) {
        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
        redisTemplate.delete(keys);
    }
}
