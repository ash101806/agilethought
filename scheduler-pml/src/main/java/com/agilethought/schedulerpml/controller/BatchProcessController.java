package com.agilethought.schedulerpml.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.schedulerpml.dto.BaseResponseDTO;

@RestController
@RequestMapping("/api/batch")
public class BatchProcessController {
	@Autowired
	@Qualifier("myJobLauncher")
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("analyzeTransactionsJob")
	private Job jobAnalyze;
	@PostMapping("analyze")
	public ResponseEntity<BaseResponseDTO> startProcess() throws Exception{
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();
		jobLauncher.run(jobAnalyze, jobParameters);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
