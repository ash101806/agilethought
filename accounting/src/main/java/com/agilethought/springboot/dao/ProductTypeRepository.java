package com.agilethought.springboot.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
/**
 * Repository to manager the entity {@link ProductType}
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public interface ProductTypeRepository extends CrudRepository<ProductType, String> {
	public Optional<ProductType> findByCode(String code);
}
