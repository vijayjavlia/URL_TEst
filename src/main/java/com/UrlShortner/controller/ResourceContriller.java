package com.UrlShortner.controller;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.UrlShortner.configuration.UrlData;
import com.google.common.hash.Hashing;

import redis.clients.jedis.exceptions.InvalidURIException;

@RestController
public class ResourceContriller {
	
	
	
	@Autowired
	private RedisTemplate<String, Object> template;

	@GetMapping("/")
	public String home(@RequestParam(name = "urls") String urls) {

		String[] patern = { "http", "https" };
		UrlValidator url = new UrlValidator(patern);

		if (url.isValid(urls)) {

			String string = Hashing.murmur3_32_fixed().hashString(urls, StandardCharsets.UTF_8).toString();
			System.out.println(string);
			UrlData urlData = new UrlData();
			urlData.setId(string);
			urlData.setDateTime(LocalDateTime.now());
			urlData.setUrl(urls);

			System.out.println(template.isEnableDefaultSerializer());
			template.opsForHash().put("key", urlData.getId(), urlData);

			System.out.println(template.opsForHash().get("key", urlData.getId()));
			return string;
		} else {

			throw new InvalidURIException("Invalid URL " + urls);
		}

	}
	
	@GetMapping("/{id}")
	public String getUrl(@PathVariable String id) {
		if(id == null) throw new InvalidURIException("Invalid ID"+id);
		UrlData urldata= (UrlData) template.opsForHash().get("key",id );
		return urldata.getUrl();
	}
	
	
	@GetMapping("/home")
	public String home1() {
		
		return "hello java";
	}

}
