package com.example.springboot.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.UserRedis;
import com.example.springboot.repo.UserRedisRepo;

@Service
public class UserRedisService {

	private static Logger log = LogManager.getLogger(UserRedisService.class);

	@Autowired
	private UserRedisRepo userRedisRepo;

	public void addSomeUsers() {
		log.info("======Saving some data to Redis===========================");
		log.info("Using hardcoded data====================");
		userRedisRepo.save(new UserRedis(55L, "UserName25", "EmaildId25"));
		userRedisRepo.save(new UserRedis(56L, "UserName26", "EmaildId26"));
		userRedisRepo.save(new UserRedis(57L, "UserName27", "EmaildId27"));
	}

	public String getAllUsers() {
		log.info("======Get some data from Redis============================");
		List<UserRedis> lUserRedis = (List<UserRedis>) userRedisRepo.findAll();
		for (UserRedis userRedis : lUserRedis) {
			log.info("User Redis List:");
			log.info(userRedis.toString());
		}
		return "";
	}

}
