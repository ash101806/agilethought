package com.agilethought.schedulerpml.listener;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.agilethought.schedulerpml.dto.RequestWhatsAppComponentsDTO;
import com.agilethought.schedulerpml.dto.RequestWhatsAppDTO;
import com.agilethought.schedulerpml.dto.RequestWhatsAppLanguageDTO;
import com.agilethought.schedulerpml.dto.RequestWhatsAppParameterDTO;
import com.agilethought.schedulerpml.dto.RequestWhatsAppTemplateDTO;

/**
 * Class for a componen that implements a JOB listener, created mainly to notify
 * by WhatsApp the execution
 * 
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@Component
public class JobListenerPML extends JobExecutionListenerSupport {
	private static final Logger LOG = LoggerFactory.getLogger(JobListenerPML.class);
	@Autowired
	private EntityManager em;
	@Autowired
	private WebClient webClient;
	@Value("${agilethought.meta-api.base}")
	private String urlBaseApiMeta;
	@Value("${agilethought.whatsapp.path}")
	private String pathWhatsApp;
	@Value("${agilethought.whatsapp.token}")
	private String token;
	@Value("${agilethought.whatsapp.number}")
	private String number;
	@Value("${agilethought.whatsapp.to-number}")
	private String toNumber;
	@Value("${agilethought.whatsapp.template.enabled}")
	private Boolean enabledTemplate;

	@Override
	@Transactional
	public void beforeJob(JobExecution exec) {
		em.createNativeQuery("DELETE FROM TRANSACTION_RISK").executeUpdate();
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			try {
				BigInteger totalTransactions = (BigInteger) em.createNativeQuery("SELECT COUNT(*) FROM TRANSACTION")
						.getSingleResult();
				BigInteger totalRiskTransactions = (BigInteger) em
						.createNativeQuery("SELECT COUNT(*) FROM TRANSACTION_RISK").getSingleResult();
				BigInteger totalSuspiciousIp = (BigInteger) em
						.createNativeQuery("SELECT COUNT(DISTINCT IP) OPERACIONES FROM TRANSACTION_RISK")
						.getSingleResult();
				BigDecimal totalAmountRiskTran = (BigDecimal) em
						.createNativeQuery("SELECT SUM(AMOUNT) OPERACIONES FROM TRANSACTION_RISK ").getSingleResult();

				LOG.info("FINALIZÓ EL JOB!! Verifica los resultados");
				List<RequestWhatsAppParameterDTO> parameters = new ArrayList<>();
				parameters.add(new RequestWhatsAppParameterDTO(totalTransactions.toString()));
				parameters.add(new RequestWhatsAppParameterDTO(totalRiskTransactions.toString()));
				parameters.add(new RequestWhatsAppParameterDTO(totalSuspiciousIp.toString()));
				parameters.add(new RequestWhatsAppParameterDTO(
						totalAmountRiskTran.setScale(2, RoundingMode.HALF_UP).toString()));
				RequestWhatsAppComponentsDTO component = new RequestWhatsAppComponentsDTO();
				component.setType("BODY");
				component.setParameters(parameters);
				List<RequestWhatsAppComponentsDTO> components = new ArrayList<>();
				components.add(component);
				RequestWhatsAppLanguageDTO language = new RequestWhatsAppLanguageDTO();
				language.setCode("en");
				RequestWhatsAppTemplateDTO template = new RequestWhatsAppTemplateDTO();
				template.setName("analyzecompleteresume");
				template.setLanguage(language);
				template.setComponents(components);
				RequestWhatsAppDTO request = new RequestWhatsAppDTO();
				request.setTemplate(template);
				request.setProduct("whatsapp");
				request.setTo(toNumber);
				request.setType("template");

				String requestDirect = MessageFormat.format(
						"'{' \"messaging_product\": \"whatsapp\", \"recipient_type\": \"individual\", \"to\": \"{0}\", \"type\": \"text\", \"text\": '{' \"preview_url\": false,  \"body\": \"The analysis of {1} transactions has been completed. ✅ *{2}* Suspicious transactions *{3}* Suspicious Address *${4}* Total amount of risk transactions\" '}' '}'",
						toNumber, totalTransactions.toString(), totalRiskTransactions.toString(),
						totalSuspiciousIp.toString(), totalAmountRiskTran.setScale(2, RoundingMode.HALF_UP).toString());

				String response = webClient.post()
						.uri(UriComponentsBuilder.fromUriString(urlBaseApiMeta).path(pathWhatsApp).build(number))
						.header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
						.bodyValue(enabledTemplate ? request : requestDirect).retrieve().bodyToMono(String.class)
						.block();
				LOG.info("Whatsapp: " + response);
			} catch (Exception e) {
				LOG.error("Can't send whatsapp ", e);
			}

		}
	}
}
