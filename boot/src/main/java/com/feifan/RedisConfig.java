package com.feifan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Donald
 * @create 2019-01-27 18:45
 */
@Configuration
public class RedisConfig {

    //获取属性文件中的值
    @Value("${spring.redis.cluster.nodes}")
    private String redisNode;

    @Bean
    public JedisCluster getJedisCluster(){
        //获取集群数据
        Set<HostAndPort> nodes = new HashSet<>();
        String [] hosts = redisNode.split(",");
        for (String host:hosts)
        {
            String [] port = host.split(":");
            HostAndPort hp = new HostAndPort(port[0],Integer.valueOf(port[1]));
            nodes.add(hp);
        }
        JedisCluster jedisCluster = new JedisCluster(nodes);
        return jedisCluster;
    }

}
