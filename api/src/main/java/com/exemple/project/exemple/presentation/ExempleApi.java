package com.exemple.project.exemple.presentation;

import com.exemple.project.core.utils.PageParameter;
import com.exemple.project.exemple.models.Exemple;
import com.exemple.project.exemple.presentation.dto.LightExempleDto;
import com.exemple.project.exemple.presentation.dto.ExempleDto;
import com.exemple.project.exemple.presentation.dto.ExempleRequest;
import com.exemple.project.exemple.presentation.mapper.LightExempleDtoMapper;
import com.exemple.project.exemple.presentation.mapper.ExempleDtoMapper;
import com.exemple.project.exemple.presentation.mapper.ExempleRequestMapper;
import com.exemple.project.exemple.service.ExempleService;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ExposesResourceFor(Exemple.class)
@RequestMapping("/api/exemples")
public class ExempleApi {
    private final ExempleService exempleService;
    private final ExempleDtoMapper exempleDtoMapper;
    private final ExempleRequestMapper exempleRequestMapper;
    private final LightExempleDtoMapper lightExempleDtoMapper;

    public ExempleApi(ExempleService exempleService, ExempleDtoMapper exempleDtoMapper, ExempleRequestMapper exempleRequestMapper, LightExempleDtoMapper lightExempleDtoMapper) {
        this.exempleService = exempleService;
        this.exempleDtoMapper = exempleDtoMapper;
        this.exempleRequestMapper = exempleRequestMapper;
        this.lightExempleDtoMapper = lightExempleDtoMapper;
    }

    @GetMapping(produces = "application/json")
    public Page<LightExempleDto> getAll(@ModelAttribute PageParameter pageParameter,
                                       @RequestParam(required = false) String search) {
        return lightExempleDtoMapper.toDtos(exempleService.search(search, pageParameter));
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ExempleDto getOne(@PathVariable long id) {
        return exempleDtoMapper.toDto(exempleService.findById(id));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(produces = "application/json")
    public LightExempleDto createOne(@RequestBody ExempleRequest exemple) {
        return lightExempleDtoMapper.toDto(exempleService.createExemple(exempleRequestMapper.toEntity(exemple)));
    }

    @PutMapping(value = "{id}", produces = "application/json")
    public ExempleDto updateOne(@PathVariable long id, @RequestBody ExempleRequest exemple) {
        return exempleDtoMapper.toDto(exempleService.updateExemple(id, exempleRequestMapper.toEntity(exemple)));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "{id}", produces = "application/json")
    public void deleteOne(@PathVariable long id) {
        exempleService.deleteExemple(id);
    }
}
