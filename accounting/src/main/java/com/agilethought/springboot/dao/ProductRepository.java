package com.agilethought.springboot.dao;

import org.springframework.data.repository.CrudRepository;
/**
 * Repository to manager the entity {@link Product}
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

}
