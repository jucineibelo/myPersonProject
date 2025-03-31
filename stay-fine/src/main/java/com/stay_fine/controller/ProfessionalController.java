package com.stay_fine.controller;

import com.stay_fine.model.entity.Professional;
import com.stay_fine.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {

    @Autowired
    private ProfessionalService service;

    @PostMapping
    public ResponseEntity<Professional> post(@RequestBody Professional professional) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createProfessional(professional));
    }

    @GetMapping("{id}")
    public ResponseEntity<Professional> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Professional>> getAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll(pageable));
    }

    @PutMapping("{id}")
    public ResponseEntity<Professional> update(@PathVariable Long id, @RequestBody Professional professional) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, professional));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
