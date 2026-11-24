package com.exemple.project.core.utils;

import com.exemple.project.core.exceptions.IllegalParameterException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Permit to build generic specification
 *
 * @param <T> Class on witch we want to build a specification
 */
public class CriteriaParameter<T> {
    private final Map<String, String> criteria;
    private final Class<T> type;

    public CriteriaParameter(Class<T> type, Map<String, String> criteria) {
        final Field[] fields = type.getDeclaredFields();
        for (Map.Entry<String, String> field : criteria.entrySet()) {
            if (Arrays.stream(fields).noneMatch(f -> f.getName().equals(field.getKey()))) {
                throw new IllegalParameterException(
                        String.format("The field %s doesn't exist in the class %s", field.getKey(), type.getName()));
            }
        }
        this.criteria = criteria;
        this.type = type;
    }

    public Specification<T> toSpecification() {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, String> crit : criteria.entrySet()) {
                if (crit.getValue() != null) {
                    try {
                        if (type.getDeclaredField(crit.getKey()).getGenericType().equals(String.class)) {
                            predicates.add(cb.like(cb.lower(root.get(crit.getKey())),
                                    crit.getValue().toLowerCase() + "%"));
                        } else {
                            predicates.add(cb.equal(root.get(crit.getKey()), criteria.get(crit.getValue())));
                        }
                    } catch (NoSuchFieldException e) { //unreachable because of the check in constructor
                        throw new RuntimeException(e);
                    }
                }
            }
            return cb.and(predicates);
        };
    }
}
