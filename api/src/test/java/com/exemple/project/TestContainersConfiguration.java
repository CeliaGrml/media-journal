package com.exemple.project;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainersConfiguration {

    private final Map<String, String> labels = Map.of("com.docker.compose.project", "BetterIT");

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer() {
        var container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
        container.withReuse(false);
        container.setLabels(labels);
        return container;
    }
}
