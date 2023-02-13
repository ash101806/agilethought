package com.agilethought.schedulerpml.procesor;

import org.springframework.batch.item.ItemProcessor;

import com.agilethought.schedulerpml.dao.Account;

public class PersonaItemProcessor implements ItemProcessor<Account, Account> {
	@Override
	public Account process(Account item) throws Exception {
		
		
		return item;
	}
}
