package com.exemple.project.core.utils.mapper;

import org.springframework.data.domain.Page;

public interface GenericDtoMapper<E, D> {
    D toDto(E entity);

    default Page<D> toDtos(Page<E> entities) {
        return entities.map(this::toDto);
    }
}
