package com.agilethought.schedulerpml.controller;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.schedulerpml.app.OperationsResultsApp;
import com.agilethought.schedulerpml.dao.Transaction;
import com.agilethought.schedulerpml.dto.BaseResponseDTO;
import com.agilethought.schedulerpml.dto.RiskOperationsIPDTO;
/**
 * Controller class for endpoint to batch and get results of PML
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 *
 */
@RestController
@RequestMapping("/api")
public class BatchProcessController {
	@Autowired
	@Qualifier("myJobLauncher")
	private JobLauncher jobLauncher;
	@Autowired
	private JobExplorer jobExplorer;
	@Autowired
	@Qualifier("analyzeTransactionsJob")
	private Job jobAnalyze;
	@Autowired
	private OperationsResultsApp comunApp;
/**
 * Endpoint to start JOB of analysis of transactions to generate risk data
 * @return
 * @throws Exception
 */
	@PostMapping("/batch/analyze")
	public ResponseEntity<BaseResponseDTO> startProcess() throws Exception {

		Set<JobExecution> lista = jobExplorer.findRunningJobExecutions("analyzeTransactionsJob");
		lista.addAll(jobExplorer.findRunningJobExecutions("initialSeedJob"));
		if (lista.size() > 0) {
			String runningIds = lista.stream()
					.map(je -> je.getJobParameters().getString("JobID") == null ? String.valueOf(je.getJobId())
							: je.getJobParameters().getString("JobID"))
					.collect(Collectors.joining(", "));
			return new ResponseEntity<>(new BaseResponseDTO(MessageFormat.format("Already processing with id(s) : {0}", runningIds)), HttpStatus.PROCESSING);
		}
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();
		JobExecution je = jobLauncher.run(jobAnalyze, jobParameters);
		return new ResponseEntity<>(new BaseResponseDTO(MessageFormat.format("Started with id: {0}", je.getJobParameters().getString("JobID"))),
				HttpStatus.ACCEPTED);
	}
	/**
	 * Endpoint to get data of risk transaction gruped by ip address
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/transactions/risk-results-ip")
	public ResponseEntity<List<RiskOperationsIPDTO>> risk() throws Exception {
		
		return new ResponseEntity<>(comunApp.getRiskOperationByIp(),HttpStatus.OK);
	}
	/**
	 * Endpoint to get all transactions by countryCode in order to report it in other Applications
	 * @param coutryCode
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/transactions/{countryCode}")
	public ResponseEntity<List<Transaction>> transactionCountryCode(@PathVariable("countryCode") String coutryCode) throws Exception {
		
		return new ResponseEntity<>(comunApp.getTransactionsByCountry(coutryCode),HttpStatus.OK);
	}
}
