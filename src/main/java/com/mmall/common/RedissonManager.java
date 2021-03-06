package com.mmall.common;

import com.mmall.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @auther lyd
 * @createDate 2019/3/27 16:15
 */

@Component
@Slf4j
public class RedissonManager {

    //在redis环境没有搭建起来之前，这里先注释上，否则项目启动不起来。

    // 声明一个Config类，用于存放连接redis配置
    private Config config = new Config();

    private Redisson redisson = null;

    private static final String redis1Ip = PropertiesUtil.getProperty("redis1.ip");
    private static String redisPassword1 = PropertiesUtil.getProperty("redis1.password");
    private static final Integer redis1Port = Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));
    private static Integer redisTimeOut = Integer.parseInt(PropertiesUtil.getProperty("redis.timeout"));


    //注入到Spring容器的话，使用@PostConstruct或者静态块初始化，效果是一样的{}

    @PostConstruct
    private void init() {
        try {
            //在redis环境没有搭建起来之前，这里先注释上，否则项目启动不起来。

            log.info("初始化Redisson开始！");

            // 使用单个Redis
            config.useSingleServer().setAddress(new StringBuilder().append(redis1Ip).append(":").append(redis1Port).toString())
                    .setPassword(redisPassword1).setTimeout(redisTimeOut);

            //单主模式
//            config.useMasterSlaveServers().setMasterAddress(new StringBuilder().append(redis1Ip).append(":").append(redis1Port).toString());

            //主从模式
//            config.useMasterSlaveServers().setMasterAddress("10.211.55.6:6379").addSlaveAddress("10.211.55.6:6380");

            // 创建Redisson
            redisson = (Redisson) Redisson.create(config);
            log.info("初始化Redisson结束！");
        } catch (Exception e) {
            log.error("redisson init error！", e);
        }
    }


//    {
//        init();
//    }


    public Redisson getRedisson() {
        return redisson;
    }
}
