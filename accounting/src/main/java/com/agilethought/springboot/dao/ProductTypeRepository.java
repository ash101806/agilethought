package com.agilethought.springboot.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {
	public Optional<ProductType> findByCode(String code);
}
