package edu.northeastern.cs5200.orm.jpa.entities;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
