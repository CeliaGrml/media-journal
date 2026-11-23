package com.exemple.project.exemple.persistence;

import com.exemple.project.exemple.models.Exemple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExempleJpaRepository extends JpaRepository<Exemple, Long>, JpaSpecificationExecutor<Exemple> {
}
