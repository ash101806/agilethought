package com.agilethought.schedulerpml.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

import com.agilethought.schedulerpml.dao.Account;
import com.agilethought.schedulerpml.dao.Transaction;
import com.agilethought.schedulerpml.listener.JobListenerPML;
import com.agilethought.schedulerpml.procesor.TransactionItemProcessor;
import com.agilethought.schedulerpml.writer.CustomItemWriterAshley;
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
	/**
	 * Bean {@link FlatFileItemReader} for read csv for accounts
	 * @return
	 */
	@Bean("accountReader")
	public FlatFileItemReader<Account> reader() {
		return new FlatFileItemReaderBuilder<Account>().name("accountItemReader")
				.resource(new ClassPathResource("accounts-fixed.csv")).delimited()
				.includedFields(new Integer[] { 0, 1, 2, 3, 4, 5, 6 }).names(new String[] { "id", "zipCode", "state",
						"country", "accountAge", "registered", "paymentInstrumentAgeInAccount" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Account>() {
					{
						setTargetType(Account.class);
						setStrict(false);
					}
				}).build();
	}
	/**
	 * Bean of Transaction processor for PML, this instance will be used to detect
	 * suspicious transactions
	 * @return {@link TransactionItemProcessor} instance
	 */
	@Bean
	public TransactionItemProcessor procesorTran() {
		return new TransactionItemProcessor();
	}
	/**
	 * Paged JPA reader for processing transactions, it's limited to 1000 page size
	 * @return {@link JpaPagingItemReader} instance
	 */
	@Bean("transacitionJPAReader")
	public JpaPagingItemReader<Transaction> readerJpaTransactions() {
		return new JpaPagingItemReaderBuilder<Transaction>().name("transactionJPAReader").entityManagerFactory(emf)
				.queryString("from Transaction").pageSize(1000).build();
	}
	/**
	 * Bean {@link FlatFileItemReader} for read csv for Transactions
	 * @return {@link FlatFileItemReader} instance
	 */
	@Bean("fileTransactionReader")
	public FlatFileItemReader<Transaction> FileReaderTransaction() {
		return new FlatFileItemReaderBuilder<Transaction>().name("transactionFileReader")
				.resource(new ClassPathResource("transactions.csv")).delimited()
				.names(new String[] { "accountId", "id", "amount", "currencyCode", "localHour", "scenario", "type",
						"ipAddress", "ipState", "ipPostalCode", "ipCountry", "isProxy", "browserLanguage",
						"paymentInstrumentType", "cardType", "paymentBillingPostalCode", "paymentBillingState",
						"paymentBillingCountryCode", "shippingPostalCode", "shippingState", "shippingCountry",
						"cvvVerifyResult", "digitalItemCount", "physicalItemCount", "transactionDateTime" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Transaction>() {
					{
						setTargetType(Transaction.class);
						setStrict(false);
					}
				}).build();
	}
	/**
	 * Bean of writer for bulk transactions to DB
	 * @param dataSource of dataBase to batch insertions
	 * @return {@link JdbcBatchItemWriter} instance
	 */
	@Bean("writeTransaction")
	public ItemWriter<Transaction> writerTransaction() {
		return new CustomItemWriterAshley();
	}
	/**
	 * Bean of writer to save RISK transactions
	 * @param dataSource of dataBase to batch insertions
	 * @return {@link JdbcBatchItemWriter} instance
	 */
	@Bean("wirteRiskTransaction")
	public JdbcBatchItemWriter<Transaction> writerRiskTransaction(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Transaction>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO TRANSACTION_RISK (ID, AMOUNT, IP) VALUES (:id,:amount,:ipAddress)").dataSource(dataSource).build();
	}
	/**
	 * Bean of writer for bulk accounts to database
	 * @param dataSource of dataBase to batch insertions
	 * @return {@link JdbcBatchItemWriter} instance
	 */
	@Bean
	public JdbcBatchItemWriter<Account> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Account>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO ACCOUNT (ID, ZIP_CODE, STATE, COUNTRY, ACCOUNT_AGE, USER_REGISTERED,PAYMENT_INSTRUMENT_AGE) VALUES (:id, :zipCode, :state,:country,:accountAge,:registered,:paymentInstrumentAgeInAccount)")
				.dataSource(dataSource).build();
	}
	/**
	 * Job Definition for initial data bulk
	 * @param step1 step for account bulk
	 * @param step2 step for transaction bulk
	 * @return {@link Job} instance with "initialSeedJob" qualifier
	 */
	@Bean("initialSeedJob")
	public Job initialSeedJob(JobListenerPML listener, @Qualifier("step1") Step step1, @Qualifier("step2") Step step2) {
		return jobBuilderFactory.get("initialSeedJob").incrementer(new RunIdIncrementer()).flow(step1).next(step2).end()
				.build();
	}
	/**
	 * Job for transactions analysis
	 * @param listener to catch the end and send resume to whatsapp
	 * @param stepAnalyze step for transactions analysis
	 * @return {@link Job} instance with "analyzeTransactionsJob" qualifier
	 */
	@Bean("analyzeTransactionsJob")
	public Job processTransactionJob(JobListenerPML listener, @Qualifier("analyzeTransactionsStep") Step stepAnalyze) {
		return jobBuilderFactory.get("analyzeTransactionsJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(stepAnalyze).end().build();
	}
	/**
	 * Step for bulk accounts
	 * @param writer {@link JdbcBatchItemWriter} for {@link Account} type
	 * @param reader {@link FlatFileItemReader} for {@link Account} type
	 * @return {@link Step} instance with "step1" qualifier
	 */
	@Bean("step1")
	public Step step1(JdbcBatchItemWriter<Account> writer, FlatFileItemReader<Account> reader) {
		return stepBuilderFactory.get("step1").<Account, Account>chunk(1000).reader(reader).writer(writer).build();
	}
	/**
	 * Step for bulk transactions
	 * @param writer {@link JdbcBatchItemWriter} for {@link Transacion} type
	 * @param reader {@link FlatFileItemReader} for {@link Transacion} type
	 * @return {@link Step} instance with "step1" qualifier
	 */
	@Bean("step2")
	public Step step2(@Qualifier("writeTransaction") ItemWriter<Transaction> writer,
			@Qualifier("fileTransactionReader") FlatFileItemReader<Transaction> reader, 
			TransactionItemProcessor procesorTran
			,@Qualifier("analizeTaskExecutor") TaskExecutor te) {
		return stepBuilderFactory.get("step2").<Transaction, Transaction>chunk(10000)
				.reader(reader)
				.processor(procesorTran).writer(writer)
				.taskExecutor(te)
				.throttleLimit(10)
				.build();
	}
	/**
	 * Step for transaction analysis
	 * @param writer {@link JdbcBatchItemWriter} for {@link Transacion} type
	 * @param reader {@link JpaPagingItemReader} for {@link Transacion} type
	 * @param transactionProcessor {@link TransactionItemProcessor} argument
	 * @return {@link Step} instance with "analyzeTransactionsStep" qualifier
	 */
	@Bean("analyzeTransactionsStep")
	public Step analyzeTransactionsStep(@Qualifier("wirteRiskTransaction") JdbcBatchItemWriter<Transaction> writer,
			@Qualifier("transacitionJPAReader") JpaPagingItemReader<Transaction> reader,
			@Qualifier("analizeTaskExecutor") TaskExecutor te, TransactionItemProcessor transactionProcessor) {
		return stepBuilderFactory.get("analyzeTransactionsStep").<Transaction, Transaction>chunk(1000).reader(reader)
				.processor(transactionProcessor).writer(writer).taskExecutor(te).throttleLimit(20).build();
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
