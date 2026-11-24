package com.exemple.project.exemple.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Exemple {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exemple_id_seq")
    private Long id;

    @NotNull
    private String name;

    public Exemple() {
    }

    public Exemple(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder exemple(Exemple exemple) {
            this.id = exemple.getId();
            this.name = exemple.getName();
            return this;
        }

        public Exemple build() {
            return new Exemple(
                    id,
                    name);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Exemple exemple)) return false;
        return Objects.equals(getId(), exemple.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Exemple{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
