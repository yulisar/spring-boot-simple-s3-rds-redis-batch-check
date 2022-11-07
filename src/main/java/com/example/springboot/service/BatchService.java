package com.example.springboot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.amazonaws.services.batch.AWSBatch;
import com.amazonaws.services.batch.AWSBatchClientBuilder;
import com.amazonaws.services.batch.model.SubmitJobRequest;
import com.amazonaws.services.batch.model.SubmitJobResult;

@Service
public class BatchService {

	private static Logger log = LogManager.getLogger(BatchService.class);

	AWSBatch client = AWSBatchClientBuilder.standard().withRegion("ap-southeast-1").build();

	public void submitJob(String jobName, String jobQueue, String jobDef) {
		log.info("Sending job, with Name: {}, JobQueue: {}, JobDefinition: {}");
		SubmitJobRequest request = new SubmitJobRequest()
				.withJobName(jobName)
				.withJobQueue(jobQueue)
				.withJobDefinition(jobDef);
		SubmitJobResult response = client.submitJob(request);
		System.out.println(response);
	}

}
