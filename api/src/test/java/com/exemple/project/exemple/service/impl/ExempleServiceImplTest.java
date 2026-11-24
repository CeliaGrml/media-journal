package com.exemple.project.exemple.service.impl;

import com.exemple.project.core.exceptions.ResourceNotFoundException;
import com.exemple.project.exemple.persistence.ExempleJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExempleServiceImplTest {
    @Mock
    private ExempleJpaRepository sellerJpaRepository;
    @InjectMocks
    private ExempleServiceImpl sellerService;

    @Test
    void findById_should_throw_exception_when_cart_not_found_by_id() {
        long id = 1L;
        when(sellerJpaRepository.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            sellerService.findById(id);
        })
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageStartingWith(String.format("Exemple with id %d not found", id));
    }
}
