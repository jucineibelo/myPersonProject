package com.stay_fine.controller;

import com.stay_fine.controller.request.PersonRequest;
import com.stay_fine.controller.response.PersonResponse;
import com.stay_fine.mapper.PersonMapper;
import com.stay_fine.model.entity.Person;
import com.stay_fine.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonResponse> post(@RequestBody PersonRequest request) {
        Person savedPerson = personService.create(PersonMapper.personRequestToEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonMapper.personEntityToResponse(savedPerson));
    }

    @GetMapping
    public ResponseEntity<Page<PersonResponse>> getAllPerson(Pageable pageable) {
        Page<Person> personPage = personService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(personPage.map(PersonMapper::personEntityToResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getById(@PathVariable Long id) {
        Person getById = personService.findById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonMapper.personEntityToResponse(getById));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @RequestBody PersonRequest request){
        Person updatedPerson = personService.update(id, PersonMapper.personRequestToEntity(request));
        return ResponseEntity.status(HttpStatus.OK).body(PersonMapper.personEntityToResponse(updatedPerson));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
