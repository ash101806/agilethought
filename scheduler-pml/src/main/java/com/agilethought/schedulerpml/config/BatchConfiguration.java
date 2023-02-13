package com.agilethought.schedulerpml.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.agilethought.schedulerpml.dao.Account;
import com.agilethought.schedulerpml.listener.JobListenerPML;
import com.agilethought.schedulerpml.models.Persona;
import com.agilethought.schedulerpml.procesor.PersonaItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<Account> reader() {
		return new FlatFileItemReaderBuilder<Account>().name("accountItemReader")
				.resource(new ClassPathResource("accounts-fixed.csv")).delimited()
				.includedFields(new Integer[] {0,1, 2,3,4,5,6})
				.names(new String[] { "id", "zipCode", "state", "country", "accountAge", "registered","paymentInstrumentAgeInAccount"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Account>() {
					{
						setTargetType(Account.class);
						setStrict(false);
					}
				}).build();
	}

	@Bean
	public PersonaItemProcessor processor() {
		return new PersonaItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Account> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Account>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO ACCOUNT (ID, ZIP_CODE, STATE, COUNTRY, ACCOUNT_AGE, USER_REGISTERED,PAYMENT_INSTRUMENT_AGE) VALUES (:id, :zipCode, :state,:country,:accountAge,:registered,:paymentInstrumentAgeInAccount)")
				.dataSource(dataSource).build();
	}

	@Bean
	public Job importPersonaJob(JobListenerPML listener, Step step1) {
		return jobBuilderFactory.get("importPersonaJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1).end().build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Account> writer) {
		return stepBuilderFactory.get("step1").<Account, Account>chunk(1000).reader(reader()).processor(processor())
				.writer(writer).build();
	}
}
