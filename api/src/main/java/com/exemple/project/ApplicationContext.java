package com.exemple.project;

import com.exemple.project.core.json.PageJsonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import tools.jackson.databind.module.SimpleModule;

@Configuration
public class ApplicationContext {

    @Bean
    @SuppressWarnings("unchecked")
    public SimpleModule pageSerializerModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer((Class<Page<?>>) (Class<?>) Page.class, new PageJsonSerializer());
        return module;
    }
}
