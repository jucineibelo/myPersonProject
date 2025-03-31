package com.stay_fine.service;

import com.stay_fine.enums.Error;
import com.stay_fine.enums.ProductStatus;
import com.stay_fine.exception.BadRequestException;
import com.stay_fine.exception.NotFoundException;
import com.stay_fine.model.entity.*;
import com.stay_fine.repository.PaymentRepository;
import com.stay_fine.repository.PersonRepository;
import com.stay_fine.repository.ProfessionalRepository;
import com.stay_fine.repository.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulingService {

    @Autowired
    SchedulingRepository schedulingRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ProfessionalRepository professionalRepository;

    @Autowired
    ProductService productService;

    public Scheduling create(Scheduling scheduling) {

        List<Product> productList = scheduling.getProducts().stream()
                .map(p -> productService.findById(p.getId()))
                .toList();

        validProducts(productList);

        Scheduling schedulingSaved = Scheduling.builder()
                .person(validPerson(scheduling.getPerson().getId()))
                .payment(validPayment(scheduling.getPayment().getId()))
                .schedulingDate(scheduling.getSchedulingDate())
                .products(productList)
                .professional(validProfessional(scheduling.getProfessional().getId()))
                .build();

        return schedulingRepository.save(schedulingSaved);
    }

    private void validProducts(List<Product> products) {
        List<Long> inactiveProductIds = products.stream()
                .filter(p -> p.getStatus() == ProductStatus.EXCLUIDO || p.getStatus() == ProductStatus.INATIVO)
                .map(Product::getId)
                .toList();

        if (!inactiveProductIds.isEmpty()) {
            throw new BadRequestException(Error.BAD_REQUEST_EXCEPTION);
        }
    }

    public List<Scheduling> findAll() {
        return schedulingRepository.findAll();
    }

    public Scheduling findById(Long id) {
        return schedulingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));
    }

    public Scheduling update(Long id, Scheduling request) {
        Scheduling existsScheduling = schedulingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        validaDataHora(request.getSchedulingDate());
        existsScheduling.setSchedulingDate(request.getSchedulingDate());
        existsScheduling.setPerson(validPerson(request.getPerson().getId()));
        existsScheduling.setPayment(validPayment(request.getPayment().getId()));
        existsScheduling.setProducts(request.getProducts());
        existsScheduling.setProfessional(validProfessional(request.getProfessional().getId()));

        return schedulingRepository.save(existsScheduling);
    }

    public void delete(Long id) {
        Scheduling scheduling = schedulingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        validaDataHora(scheduling.getSchedulingDate());
        schedulingRepository.delete(scheduling);
    }

    private void validaDataHora(LocalDateTime dataHoraAgendada) { //se passar de 24 horas não pode cancelar excluir ou atualizar
        LocalDateTime now = LocalDateTime.now();

        if (!dataHoraAgendada.isAfter(now.plusHours(18))) {
            throw new RuntimeException("Você não pode alterar a data deste agendamento!");
        }
    }

    private Person validPerson(Long id) {
        Person existsPerson = personRepository.findByIdActivePerson(id);

        if (existsPerson == null) {
            throw new NotFoundException(Error.NOT_FOUND_EXCEPTION, id);
        }
        return existsPerson;
    }

    private Payment validPayment(Long id) {
        Payment existPayment = paymentRepository.findByIdActivePayment(id);

        if (existPayment == null) {
            throw new NotFoundException(Error.NOT_FOUND_EXCEPTION, id);
        }
        return existPayment;
    }

    private Professional validProfessional(Long id) {
        Professional existPayment = professionalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        return existPayment;
    }
}


