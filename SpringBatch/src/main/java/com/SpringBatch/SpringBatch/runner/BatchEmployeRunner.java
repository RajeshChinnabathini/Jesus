package com.SpringBatch.SpringBatch.runner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

@Component
public class BatchEmployeRunner implements CommandLineRunner {
	
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;
	@Override
	public void run(String... args) throws Exception {
		
		JobParameters jobParameter=new JobParametersBuilder().
				addLong("startAt",System.currentTimeMillis()).toJobParameters();
		
		jobLauncher.run(job, jobParameter);
		System.out.println("sending bulk data from csv file to mysql");
		
		
		
	}

}
