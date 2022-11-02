package com.example.springboot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

@Service
public class S3Service {
	
	private static Logger log = LogManager.getLogger(S3Service.class);
	
	private static final AmazonS3 s3Client = AmazonS3Client.builder()
			.withCredentials(new DefaultAWSCredentialsProviderChain())
			.withRegion(Regions.AP_SOUTHEAST_1)
			.build();
	
	public void checkS3() {
		log.info("Check credentials:");
		log.info(s3Client.getS3AccountOwner().getDisplayName());
		log.info(s3Client.getRegion().toString());
		log.info("=================================");
		log.info("List existing buckets.");
		for (Bucket s : s3Client.listBuckets()) {
			log.info(s.getName());
		}	
	}

}
