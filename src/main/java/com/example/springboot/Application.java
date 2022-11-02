package com.example.springboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.springboot.service.BookService;
import com.example.springboot.service.S3Service;
import com.example.springboot.service.UserRedisService;


@SpringBootApplication
public class Application implements ApplicationRunner {
	
	private static Logger log = LogManager.getLogger(Application.class);
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserRedisService userRedisService;
	
	@Autowired
	S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("========================================");
		log.info("RDS Test...");
		
		bookService.saveBook("Book Name 11");
		bookService.saveBook("Book Name 12");
		bookService.saveBook("Book Name 13");
		bookService.saveBook("Book Name 14");
		
		bookService.getAllBook();
		
		log.info("========================================");
		log.info("Redis on EC2 Test...");
		
		userRedisService.addSomeUsers();
		userRedisService.getAllUsers();
		
		log.info("========================================");
		log.info("S3 Test...");
		s3Service.checkS3();
		
		log.info("End test...");
		log.info("========================================");
	}
}
