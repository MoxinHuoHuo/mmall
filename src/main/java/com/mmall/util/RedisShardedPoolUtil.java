package com.mmall.util;

import com.mmall.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;

/**
 * @auther lyd
 * @createDate 2019/2/27 21:29
 */
@Slf4j
public class RedisShardedPoolUtil {


    public static Long expire(String key, Integer outTime){
        ShardedJedis jedis = null;
        Long result=null;

        try {

            jedis = RedisShardedPool.getJedis();
            result = jedis.expire(key, outTime);

        } catch (Exception e) {

            log.error("expire key:{} value:{} error", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;

        }

        RedisShardedPool.returnResource(jedis);

        return result;
    }

    /**
     * @param key
     * @param outTime 单位是s
     * @param value
     * @return
     */
    public static String setEx(String key, Integer outTime, String value){
        ShardedJedis jedis = null;
        String result=null;

        try {

            jedis = RedisShardedPool.getJedis();
            result = jedis.setex(key, outTime, value);

        } catch (Exception e) {

            log.error("setex key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;

        }

        RedisShardedPool.returnResource(jedis);

        return result;
    }

    public static String set(String key, String value){
        ShardedJedis jedis = null;
        String result=null;

        try {

            jedis = RedisShardedPool.getJedis();
            result = jedis.set(key, value);

        } catch (Exception e) {

            log.error("set key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;

        }

        RedisShardedPool.returnResource(jedis);

        return result;
    }

    public static String get(String key){
        ShardedJedis jedis = null;
        String result=null;

        try {

            jedis = RedisShardedPool.getJedis();
            result = jedis.get(key);

        } catch (Exception e) {
            log.error("get key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }

        RedisShardedPool.returnResource(jedis);

        return result;
    }

    public static String getSet(String key, String value){
        ShardedJedis jedis = null;
        String result=null;

        try {

            jedis = RedisShardedPool.getJedis();
            result = jedis.getSet(key, value);

        } catch (Exception e) {
            log.error("getSet key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }

        RedisShardedPool.returnResource(jedis);

        return result;
    }


    public static Long del(String key){
        ShardedJedis jedis = null;
        Long result=null;

        try {

            jedis = RedisShardedPool.getJedis();
            result = jedis.del(key);

        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }

        RedisShardedPool.returnResource(jedis);

        return result;
    }


    public static Long setnx(String key, String value){
        ShardedJedis jedis = null;
        Long result=null;

        try {

            jedis = RedisShardedPool.getJedis();
            result = jedis.setnx(key, value);

        } catch (Exception e) {

            log.error("setnx key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;

        }

        RedisShardedPool.returnResource(jedis);

        return result;
    }

    // ------------test------------------------------------------
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            RedisPoolUtil.set("stu"+i, "li"+i);
        }
        System.out.println("program is end!");

    }


}
