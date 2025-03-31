package com.stay_fine.service;

import com.stay_fine.enums.Error;
import com.stay_fine.enums.ProfessionalStatus;
import com.stay_fine.exception.NotFoundException;
import com.stay_fine.model.entity.Professional;
import com.stay_fine.repository.ProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository repository;


    public Professional createProfessional(Professional professional) {

        Professional professionalBuilder = Professional.builder()
                .name(professional.getName())
                .registrationDate(professional.getRegistrationDate())
                .status(professional.getStatus())
                .build();

        return repository.save(professionalBuilder);
    }

    public Professional getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));
    }

    public Page<Professional> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Professional update(Long id, Professional professional) {
        Professional existsProfessional = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        if (professional.getName() != null && !professional.getName().equals(existsProfessional.getName())) {
            existsProfessional.setName(professional.getName());
        }

        existsProfessional.setUpdateDate(LocalDateTime.now());
        return repository.save(existsProfessional);
    }

    public void delete(Long id) {
        Professional existsProfessional = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        existsProfessional.setUpdateDate(LocalDateTime.now());
        existsProfessional.setStatus(ProfessionalStatus.EXCLUIDO);
        repository.save(existsProfessional);
    }


}
