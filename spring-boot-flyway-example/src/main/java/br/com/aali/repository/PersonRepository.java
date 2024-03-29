package br.com.aali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aali.model.entity.Person;

/**
 * The Person Repository Interface.
 * 
 * @author Ryan Padilha <ryan.padilha@gmail.com>
 * @since 0.1
 *
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
