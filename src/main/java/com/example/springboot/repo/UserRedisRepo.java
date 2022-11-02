package com.example.springboot.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.springboot.model.UserRedis;

public interface UserRedisRepo extends CrudRepository<UserRedis, Long> {

	List<UserRedis> findAllByName(String name);

}
