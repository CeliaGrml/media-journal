package com.exemple.project.exemple.presentation.mapper;

import com.exemple.project.core.utils.mapper.GenericRequestMapper;
import com.exemple.project.exemple.models.Exemple;
import com.exemple.project.exemple.presentation.dto.ExempleRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExempleRequestMapper extends GenericRequestMapper<Exemple, ExempleRequest> {
}
