package com.stay_fine.service;

import com.stay_fine.enums.Error;
import com.stay_fine.enums.PersonStatus;
import com.stay_fine.exception.NotFoundException;
import com.stay_fine.model.entity.Person;
import com.stay_fine.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Person create(Person person) {

        Person personBuilder = Person.builder()
                .name(person.getName())
                .email(person.getEmail())
                .telephone(person.getTelephone())
                .username(person.getUsername())
                .password(passwordEncoder.encode(person.getPassword()))
                .build();

        return personRepository.save(personBuilder);
    }

    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));
    }

    public Person update(Long id, Person person) {
        Person existsPerson = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        PersonRequestToPersonDB(person, existsPerson);
        existsPerson.setUpdateDate(LocalDateTime.now());
        return personRepository.save(existsPerson);
    }

    public void delete(Long id) {
        Person personExists = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        personExists.setStatus(PersonStatus.EXCLUIDO);
        personExists.setUpdateDate(LocalDateTime.now());
        personRepository.save(personExists);
    }

    private void PersonRequestToPersonDB(Person person, Person existsPerson) {
        if (person.getName() != null && !Objects.equals(person.getName(), existsPerson.getName())) {
            existsPerson.setName(person.getName());
        }

        if (person.getEmail() != null && !Objects.equals(person.getEmail(), existsPerson.getEmail())) {
            existsPerson.setEmail(person.getEmail());
        }

        if (person.getTelephone() != null && !Objects.equals(person.getTelephone(), existsPerson.getTelephone())) {
            existsPerson.setTelephone(person.getTelephone());
        }

        if (person.getUsername() != null && !Objects.equals(person.getUsername(), existsPerson.getUsername())) {
            existsPerson.setUsername(person.getUsername());
        }

        if (person.getPassword() != null && !Objects.equals(person.getPassword(), existsPerson.getPassword())) {
            existsPerson.setPassword(person.getPassword());
        }
    }

}
