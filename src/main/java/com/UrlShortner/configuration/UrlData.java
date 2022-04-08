package com.UrlShortner.configuration;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("UrlData")
public class UrlData implements Serializable {

	private String  id;
	private String url;
	private LocalDateTime dateTime;
	private String status;

}
