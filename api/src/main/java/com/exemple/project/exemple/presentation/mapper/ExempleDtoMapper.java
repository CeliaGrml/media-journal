package com.exemple.project.exemple.presentation.mapper;

import com.exemple.project.core.utils.mapper.GenericDtoMapper;
import com.exemple.project.exemple.models.Exemple;
import com.exemple.project.exemple.presentation.dto.ExempleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExempleDtoMapper extends GenericDtoMapper<Exemple, ExempleDto> {
}
