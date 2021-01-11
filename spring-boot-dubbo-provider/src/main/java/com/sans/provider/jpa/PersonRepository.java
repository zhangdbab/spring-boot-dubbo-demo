package com.sans.provider.jpa;

 import com.sans.provider.entity.Person;
 import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}