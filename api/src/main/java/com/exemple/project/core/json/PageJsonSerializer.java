package com.exemple.project.core.json;

import org.springframework.data.domain.Page;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

public class PageJsonSerializer extends ValueSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> page, JsonGenerator gen, SerializationContext context) {
        gen.writeStartObject();

        gen.writeObjectPropertyStart("page");
        gen.writeNumberProperty("totalElements", page.getTotalElements());
        gen.writeNumberProperty("totalPages", page.getTotalPages());
        gen.writeNumberProperty("number", page.getNumber());
        gen.writeNumberProperty("size", page.getSize());
        gen.writeBooleanProperty("first", page.isFirst());
        gen.writeBooleanProperty("last", page.isLast());
        gen.writeEndObject();

        context.defaultSerializeProperty("content", page.getContent(), gen);

        gen.writeEndObject();
    }
}
