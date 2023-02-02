package com.agilethought.springboot.dao;

import org.springframework.data.repository.CrudRepository;
/**
 * Repository to manager the entity {@link Person}
 * @author Manuel Ashley Sanchez Zapien <mailto: manuel.zapien>
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

}
