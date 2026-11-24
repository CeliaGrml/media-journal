package com.exemple.project.core.utils;

import com.exemple.project.core.exceptions.IllegalParameterException;
import com.exemple.project.exemple.models.Exemple;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CriteriaParameterTest {

    @Test
    void build_with_wrong_field() {
        Map<String, String> map = HashMap.newHashMap(2);
        map.put("wrongField", "test");

        assertThatThrownBy(() -> new CriteriaParameter<>(Exemple.class, map))
                .isInstanceOf(IllegalParameterException.class);
    }
}
