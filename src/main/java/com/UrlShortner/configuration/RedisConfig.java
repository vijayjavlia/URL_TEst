package com.UrlShortner.configuration;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableCaching
public class RedisConfig {

//	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory(new RedisStandaloneConfiguration("167.86.78.200" ,6379),
				JedisClientConfiguration.builder().connectTimeout(Duration.ofMillis(50 * 1000)).usePooling().build());
	}

//	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

}
