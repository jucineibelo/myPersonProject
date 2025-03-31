package com.stay_fine.repository;

import com.stay_fine.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("Select p from Person p where p.id = :id and p.status = 'ATIVO'")
    Person findByIdActivePerson(Long id);
}
