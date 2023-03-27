package com.agilethought.schedulerpml.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.agilethought.schedulerpml.dao.Transaction;

public class CustomItemWriterAshley implements ItemWriter<Transaction> {

	@Override
	public void write(List<? extends Transaction> items) throws Exception {
		System.out.println(items.size());

	}

}
