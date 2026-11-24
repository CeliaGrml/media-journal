package com.exemple.project.exemple.service.impl;

import com.exemple.project.core.exceptions.ResourceNotFoundException;
import com.exemple.project.core.utils.CriteriaParameter;
import com.exemple.project.core.utils.PageParameter;
import com.exemple.project.exemple.models.Exemple;
import com.exemple.project.exemple.persistence.ExempleJpaRepository;
import com.exemple.project.exemple.service.ExempleService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ExempleServiceImpl implements ExempleService {

    private final ExempleJpaRepository exempleJpaRepository;

    public ExempleServiceImpl(ExempleJpaRepository exempleJpaRepository) {
        this.exempleJpaRepository = exempleJpaRepository;
    }

    @Override
    public Page<Exemple> search(String search, PageParameter pageParameter) {
        Map<String, String> map = HashMap.newHashMap(2);
        map.put("name", search);
        CriteriaParameter<Exemple> criteriaParameter = new CriteriaParameter<>(Exemple.class, map);
        return exempleJpaRepository.findAll(criteriaParameter.toSpecification(), pageRequest(pageParameter));
    }

    @Override
    public Exemple findById(long id) {
        Optional<Exemple> optionalExemple = exempleJpaRepository.findById(id);
        if (optionalExemple.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Exemple with id %d not found", id));
        }
        return optionalExemple.get();
    }

    @Override
    public Exemple createExemple(Exemple exemple) {
        return exempleJpaRepository.save(exemple);
    }

    @Override
    public Exemple updateExemple(long id, Exemple exemple) {
        findById(id);
        return exempleJpaRepository.save(Exemple.builder().exemple(exemple).id(id).build());
    }

    @Override
    public void deleteExemple(long id) {
        exempleJpaRepository.deleteById(id);
    }
}
