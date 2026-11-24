package com.exemple.project.exemple.presentation;

import com.exemple.project.TestContainersConfiguration;
import jakarta.transaction.Transactional;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestContainersConfiguration.class)
class ExempleApiIT {
    @Autowired
    private MockMvcTester mockMvc;

    @Nested
    @DisplayName("request `GET /exemples`")
    class search {
        @Test
        void should_fetch_page() {
            mockMvc
                    .get()
                    .uri("/api/exemples")
                    .contentType(MediaType.APPLICATION_JSON)
                    .assertThat()
                    .hasStatus(HttpStatus.OK)
                    .bodyJson()
                    .extractingPath("$.page.totalElements")
                    .isEqualTo(5);
        }

        @Test
        void should_fetch_page_of_A() {
            mockMvc
                    .get()
                    .uri("/api/exemples")
                    .param("search", "A")
                    .contentType(MediaType.APPLICATION_JSON)
                    .assertThat()
                    .hasStatus(HttpStatus.OK)
                    .bodyJson()
                    .extractingPath("$.page.totalElements")
                    .isEqualTo(2);
        }
    }

    @Test
    void get_by_id() {
        mockMvc
                .get()
                .uri("/api/exemples/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .assertThat()
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo("""
                        {
                          "id": 1,
                          "name": "ABC"
                        }""");
    }

    @Test
    @Transactional
    void create() {
        String jsonExempleCreate = """
                {
                  "name": "test"
                }""";
        String jsonExempleExpected = """
                {
                  "id": {id},
                  "name": "test"
                }""";

        String jsonExempleGetterExpected = """
                {
                  "id": {id},
                  "name": "test"
                }""";

        var result = mockMvc
                .post()
                .uri("/api/exemples")
                .content(jsonExempleCreate)
                .contentType(MediaType.APPLICATION_JSON)
                .assertThat()
                .hasStatus(HttpStatus.CREATED)
                .bodyJson();

        Integer id = result
                .extractingPath("$.id")
                .convertTo(InstanceOfAssertFactories.INTEGER)
                .actual();

        result.isEqualTo(jsonExempleExpected.replace("{id}", String.valueOf(id)));

        mockMvc
                .get()
                .uri("/api/exemples/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .assertThat()
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isEqualTo(jsonExempleGetterExpected.replace("{id}", String.valueOf(id)));
    }

    @Test
    @Transactional
    void update() {
        long id = 1;
        String jsonExempleExpected = """
                {
                  "id": 1,
                  "name": "test"
                }""";

        String jsonExempleUpdate = """
                {
                  "name": "test"
                }""";
        mockMvc
                .put()
                .uri("/api/exemples/{id}", id)
                .content(jsonExempleUpdate)
                .contentType(MediaType.APPLICATION_JSON)
                .assertThat()
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isLenientlyEqualTo(jsonExempleExpected)
                .hasPath("$.id");

        mockMvc
                .get()
                .uri("/api/exemples/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .assertThat()
                .hasStatus(HttpStatus.OK)
                .bodyJson()
                .isLenientlyEqualTo(jsonExempleExpected);
    }


    @Test
    @Transactional
    void delete() {
        long id = 1;

        mockMvc
                .delete()
                .uri("/api/exemples/{id}", id)
                .assertThat()
                .hasStatus(HttpStatus.NO_CONTENT);

        mockMvc
                .get()
                .uri("/api/exemples/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .assertThat()
                .hasStatus(HttpStatus.NOT_FOUND);
    }
}
