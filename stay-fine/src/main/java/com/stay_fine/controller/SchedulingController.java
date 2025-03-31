package com.stay_fine.controller;

import com.stay_fine.model.entity.Scheduling;
import com.stay_fine.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @Autowired
    SchedulingService schedulingService;

    @PostMapping
    ResponseEntity<Scheduling> create(@RequestBody Scheduling scheduling) {
         return ResponseEntity.status(HttpStatus.CREATED).body(schedulingService.create(scheduling));
    }

    @GetMapping
    ResponseEntity<List<Scheduling>> getAll() {
        List<Scheduling> schedulingList = schedulingService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(schedulingList);
    }

    @GetMapping("/{id}")
    ResponseEntity<Scheduling> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(schedulingService.findById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<Scheduling> update(@PathVariable Long id, @RequestBody Scheduling request){
        return ResponseEntity.status(HttpStatus.OK).body(schedulingService.update(id, request));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        schedulingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
