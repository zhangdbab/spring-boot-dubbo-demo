package com.sans.provider.jpa;

 import com.sans.provider.entity.Person;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {
 @Query(value = "SELECT * FROM PERSON WHERE ID = ?1", nativeQuery = true)
 Person findByID(String ID);
}