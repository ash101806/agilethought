package com.agilethought.schedulerpml.app;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.schedulerpml.dao.Transaction;
import com.agilethought.schedulerpml.dao.TransactionrepositoryDAO;
import com.agilethought.schedulerpml.dto.RiskOperationsIPDTO;

/**
 * Class for business Layer implementation to get analysis results
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
@Service
public class OperationsResultsAppImpl implements OperationsResultsApp {
	@Autowired
	private EntityManager em;
	@Autowired
	private TransactionrepositoryDAO repoTran;
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RiskOperationsIPDTO> getRiskOperationByIp() throws Exception {
		List<Object[]> results = em.createNativeQuery("SELECT IP, SUM(AMOUNT) MONTO, COUNT(ID) OPERACIONES FROM TRANSACTION_RISK GROUP BY IP")
				.getResultList();
		return  results.stream().map(r-> new RiskOperationsIPDTO((String)r[0],((BigInteger)r[2]).longValue(), (BigDecimal)r[1])).collect(Collectors.toList());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Transaction> getTransactionsByCountry(String countryCode) throws Exception {
		return repoTran.getTransactionCountry(countryCode.toUpperCase());
	}
}
