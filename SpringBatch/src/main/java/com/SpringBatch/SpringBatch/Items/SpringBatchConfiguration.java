package com.SpringBatch.SpringBatch.Items;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import com.SpringBatch.SpringBatch.Entity.BatchEmploye;
import com.SpringBatch.SpringBatch.Repository.BatchEmployeRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
@Configuration
public class SpringBatchConfiguration {
	@Autowired
	private BatchEmployeRepository batchEmployeRepository;
	@Autowired
	private JobRepository jobRepository;
	
	private PlatformTransactionManager platformtransactionManager;
	
	@Bean
	public FlatFileItemReader<BatchEmploye> itemReader(){
		FlatFileItemReader<BatchEmploye> flatFileItemReader=new FlatFileItemReader<>();
		flatFileItemReader.setResource(new FileSystemResource("D:\\SpringBatchExcel\\employees.csv"));;
	    flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
		
	}

	
	private LineMapper<BatchEmploye> lineMapper() {
		DefaultLineMapper<BatchEmploye> defaultLineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter(",");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames("EMPLOYEE_ID","FIRST_NAME","LAST_NAME","EMAIL","PHONE_NUMBER","HIRE_DATE","JOB_ID","SALARY","MANAGER_ID","DEPARTMENT_ID");
		BeanWrapperFieldSetMapper<BatchEmploye> beanWrapperFieldSetMapper=new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(BatchEmploye.class);
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		return defaultLineMapper;
	}


	@Bean
	public BatchEmployeProcesser itemProcessor() {
		return new BatchEmployeProcesser();
		
	}
	
	public RepositoryItemWriter<BatchEmploye> itemWriter(){
		RepositoryItemWriter<BatchEmploye> repositoryItemWriter=new RepositoryItemWriter<>();
		repositoryItemWriter.setRepository(batchEmployeRepository);
		repositoryItemWriter.setMethodName("save");
		return repositoryItemWriter;
		
	}
	
	@Bean
	public Step batchEmployeStep(JobRepository jobRepository,PlatformTransactionManager platformTransactionManager) {
		return new StepBuilder("BatchEmployeStep",jobRepository).
				<BatchEmploye,BatchEmploye>chunk(10,platformTransactionManager)
		         .reader(itemReader())
		         .processor(itemProcessor())
		         .writer(itemWriter()).build();
	}
	
	@Bean
	public Job runJob(JobRepository jobRepository) {
		return new JobBuilder("csv-job",jobRepository).
				flow(batchEmployeStep(jobRepository,platformtransactionManager)).
				end().build();
				
		
	}
	
	
	
}
