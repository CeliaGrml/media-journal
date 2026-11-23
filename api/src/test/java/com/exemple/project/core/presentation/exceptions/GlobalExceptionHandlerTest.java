package com.exemple.project.core.presentation.exceptions;

import com.exemple.project.core.exceptions.ConflictException;
import com.exemple.project.core.exceptions.IllegalParameterException;
import com.exemple.project.core.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {
    private MockMvc mockMvc;

    // Faux contrôleur qui lance les exceptions à la demande
    @RestController
    static class FakeController {
        @GetMapping("/not-found")
        public void throwNotFound() {
            throw new ResourceNotFoundException("Ressource introuvable");
        }

        @GetMapping("/conflict")
        public void throwConflict() {
            throw new ConflictException("Conflit détecté");
        }

        @GetMapping("/illegal")
        public void throwIllegal() {
            throw new IllegalParameterException("Paramètre invalide");
        }
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new FakeController())
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void handleNotFound_shouldReturn404() throws Exception {
        mockMvc.perform(get("/not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Ressource introuvable"));
    }

    @Test
    void handleConflict_shouldReturn409() throws Exception {
        mockMvc.perform(get("/conflict"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value("Conflit détecté"));
    }

    @Test
    void handleIllegalParameter_shouldReturn422() throws Exception {
        mockMvc.perform(get("/illegal"))
                .andExpect(status().isUnprocessableContent())
                .andExpect(jsonPath("$.status").value(422))
                .andExpect(jsonPath("$.message").value("Paramètre invalide"));
    }
}