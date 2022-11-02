package com.example.springboot.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Data;

@Data
@RedisHash("userRedis")
public class UserRedis implements Serializable {

	@Id
	private Long userId;
	private String name;
	@Indexed
	private String emailid;

		public UserRedis(Long userId, String name, String emailid) {
			this.userId = userId;
			this.name = name;
			this.emailid = emailid;
		}
		
		public UserRedis() {
			super();
		}


}