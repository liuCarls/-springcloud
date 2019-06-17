package com.lgx.springmvc;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class MyRedisService {
    private final RedisConnectionFactory connectionFactory;

    public MyRedisService(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    private RedisConnection getConnection() {
        return this.connectionFactory.getConnection();
    }

    public void storeValue(String value) {
        RedisConnection conn = this.getConnection();
        conn.openPipeline();
        conn.set("Test".getBytes(),value.getBytes());
        conn.closePipeline();
        conn.close();
    }
}
