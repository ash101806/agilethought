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
	 * Bean for asyn execution of JOBS
	 * @return JobLaunches async
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
	 * Bean of reader csv for accounts
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
	 * Bean of Transaction processor for PML
	 * @return
	 */
	@Bean
	public TransactionItemProcessor procesorTran() {
		return new TransactionItemProcessor();
	}
	/**
	 * Paged JPA reader for processing transactions
	 * @return
	 */
	@Bean("transacitionJPAReader")
	public JpaPagingItemReader<Transaction> readerJpaTransactions() {
		return new JpaPagingItemReaderBuilder<Transaction>().name("transactionJPAReader").entityManagerFactory(emf)
				.queryString("from Transaction").pageSize(1000).build();
	}
	/**
	 * CSV reader for bulk all transactions into a DB
	 * @return
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
	 * Writer Bean for bulk transactions to DB
	 * @param dataSource
	 * @return
	 */
	@Bean("writeTransaction")
	public JdbcBatchItemWriter<Transaction> writerTransaction(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Transaction>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO TRANSACTION (ID, ACCOUNT_ID, AMOUNT, CURRENCY_CODE, LOCAL_HOUR, SCENARIO,TYPE,IP_ADDRESS,IP_STATE,IP_POSTAL_CODE,IP_COUNTRY,PROXY,B_LANGUAGE,P_INTRUMENT_TYPE,CARD_TYPE,P_BILLING_ZIP,P_BILLING_STATE,P_BILLING_COUNTRY_CODE,SHIPPING_ZIP,SHIPPING_STATE,SHIPPING_COUNTRY,CVV_VERIICATION_RESULT,DIGITAL_ITEM_COUNT,PHYSICAL_ITEM_COUNT,TRANSACTION_DATETIME) "
						+ "VALUES (:id, :accountId, :amount,:currencyCode,:localHour,:scenario,:type,:ipAddress,:ipState,:ipPostalCode,:ipCountry,:isProxy,:browserLanguage,:paymentInstrumentType,:cardType,:paymentBillingPostalCode,:paymentBillingState,:paymentBillingCountryCode,:shippingPostalCode,:shippingState,:shippingCountry,:cvvVerifyResult,:digitalItemCount,:physicalItemCount,:transactionDateTime)")
				.dataSource(dataSource).build();
	}
	/**
	 * Writer to save RISK transactions
	 * @param dataSource
	 * @return
	 */
	@Bean("wirteRiskTransaction")
	public JdbcBatchItemWriter<Transaction> writerRiskTransaction(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Transaction>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO TRANSACTION_RISK (ID, AMOUNT, IP) VALUES (:id,:amount,:ipAddress)").dataSource(dataSource).build();
	}
	/**
	 * Writer bean for bulk accounts to database
	 * @param dataSource
	 * @return
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
	 * @return
	 */
	@Bean("initialSeedJob")
	public Job initialSeedJob(JobListenerPML listener, @Qualifier("step1") Step step1, @Qualifier("step2") Step step2) {
		return jobBuilderFactory.get("initialSeedJob").incrementer(new RunIdIncrementer()).flow(step1).next(step2).end()
				.build();
	}
	/**
	 * Job for analize the transactions
	 * @param listener to catch the end and send resume to whatsapp
	 * @param stepAnalyze step for transactions analysis
	 * @return
	 */
	@Bean("analyzeTransactionsJob")
	public Job processTransactionJob(JobListenerPML listener, @Qualifier("analyzeTransactionsStep") Step stepAnalyze) {
		return jobBuilderFactory.get("analyzeTransactionsJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(stepAnalyze).end().build();
	}
	/**
	 * Step for bulk accounts
	 * @param writer
	 * @param reader
	 * @return
	 */
	@Bean("step1")
	public Step step1(JdbcBatchItemWriter<Account> writer, FlatFileItemReader<Account> reader) {
		return stepBuilderFactory.get("step1").<Account, Account>chunk(1000).reader(reader).writer(writer).build();
	}
	/**
	 * Step for bulk transactions
	 * @param writer
	 * @param reader
	 * @return
	 */
	@Bean("step2")
	public Step step2(@Qualifier("writeTransaction") JdbcBatchItemWriter<Transaction> writer,
			@Qualifier("fileTransactionReader") FlatFileItemReader<Transaction> reader) {
		return stepBuilderFactory.get("step2").<Transaction, Transaction>chunk(1000).reader(reader).writer(writer)
				.build();
	}
	/**
	 * Step for analyze transactions
	 * @param writer
	 * @param reader
	 * @param te
	 * @param transactionProcessor
	 * @return
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
	 * @return
	 */
	@Bean("analizeTaskExecutor")
	public TaskExecutor taskExecutor() {
		SimpleAsyncTaskExecutor ste = new SimpleAsyncTaskExecutor();
		ste.setThreadNamePrefix("transactionAnalyze-");
		return ste;
	}
}
