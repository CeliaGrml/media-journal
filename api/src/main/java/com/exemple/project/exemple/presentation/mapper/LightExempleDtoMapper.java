package com.exemple.project.exemple.presentation.mapper;

import com.exemple.project.core.utils.mapper.GenericDtoMapper;
import com.exemple.project.exemple.models.Exemple;
import com.exemple.project.exemple.presentation.dto.LightExempleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LightExempleDtoMapper extends GenericDtoMapper<Exemple, LightExempleDto> {
}
