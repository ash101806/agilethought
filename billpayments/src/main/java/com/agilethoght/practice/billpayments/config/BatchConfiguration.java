package com.agilethoght.practice.billpayments.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.kafka.KafkaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.kafka.core.KafkaTemplate;

import com.agilethoght.practice.billpayments.models.BillPayment;
/**
 * Configuration class for beans (readers, writers, procesors, steps and JOBS) and desing of solution flow
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Autowired
	private KafkaTemplate<String, BillPayment> kafkaTemplate;
	@Autowired
	private EntityManagerFactory emf;
	@Autowired
	JobRepository jobrepository;
	/**
	 * Bean for async execution of JOBS, returns a JOB Launcher
	 * @return instance of {@link JobLauncher} async setted a SimpleAsyncTaskExecutor
	 * @throws Exception
	 */
	@Bean(name = "myJobLauncher")
	public JobLauncher simpleJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobrepository);
		jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}


	@Bean("transacitionJPAReader")
	public JpaPagingItemReader<BillPayment> readerJpaTransactions() {
		return new JpaPagingItemReaderBuilder<BillPayment>().name("transactionJPAReader").entityManagerFactory(emf)
				.queryString("from BillPayment").pageSize(1).maxItemCount(1).build();
	}

	@Bean
	public KafkaItemWriter<String, BillPayment> kafkaItemWriter() throws Exception{
		KafkaItemWriter<String, BillPayment> writer = new KafkaItemWriter<>();
		writer.setKafkaTemplate(kafkaTemplate);
		writer.setItemKeyMapper(BillPayment::getId);
		writer.setDelete(false);
		
		writer.afterPropertiesSet();
		return writer;
	}
	/**
	 * Job for transactions analysis
	 * @param listener to catch the end and send resume to whatsapp
	 * @param step1 step for transactions analysis
	 * @return {@link Job} instance with "analyzeTransactionsJob" qualifier
	 */
	@Bean("analyzeTransactionsJob")
	public Job processTransactionJob( @Qualifier("step1") Step step1) {
		return jobBuilderFactory.get("analyzeTransactionsJob").incrementer(new RunIdIncrementer())
				.flow(step1).end().build();
	}
	/**
	 * Step for bulk accounts
	 * @param writer {@link JdbcBatchItemWriter} for {@link Account} type
	 * @param reader {@link FlatFileItemReader} for {@link Account} type
	 * @return {@link Step} instance with "step1" qualifier
	 */
	@Bean("step1")
	public Step step1(JpaPagingItemReader<BillPayment> reader, KafkaItemWriter<String, BillPayment> writer, @Qualifier("analizeTaskExecutor") TaskExecutor te ) {
		return stepBuilderFactory.get("step1").<BillPayment, BillPayment>chunk(1000).reader(reader)
				.writer(writer).taskExecutor(te).throttleLimit(20).build();
	}
	
	/**
	 * Executer for multi-threading step execution
	 * @return {@link TaskExecutor} instance
	 */
	@Bean("analizeTaskExecutor")
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor ste = new SimpleAsyncTaskExecutor();
		ste.setThreadNamePrefix("transactionAnalyze-");
		return ste;
	}
}
