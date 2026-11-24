package com.exemple.project.core.utils.mapper;

public interface GenericRequestMapper<E, D> {
    E toEntity(D dto);
}
