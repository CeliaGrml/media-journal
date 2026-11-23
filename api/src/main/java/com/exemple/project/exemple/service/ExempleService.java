package com.exemple.project.exemple.service;

import com.exemple.project.core.utils.PageParameter;
import com.exemple.project.core.utils.PageService;
import com.exemple.project.exemple.models.Exemple;
import org.springframework.data.domain.Page;

public interface ExempleService extends PageService {
    /**
     * Find a exemple by its id.
     *
     * @param id The id of the exemple
     * @return The exemple that was found with the given id
     */
    Exemple findById(long id);

    /**
     * Get a paginated search in Exemple.
     *
     * @param search        Filter to search by the exemple's name
     * @param pageParameter The page parameter for the listing
     * @return A page of Exemple
     */
    Page<Exemple> search(String search, PageParameter pageParameter);

    /**
     * Create a exemple.
     *
     * @param exemple The exemple to create
     * @return The exemple created
     */
    Exemple createExemple(Exemple exemple);

    /**
     * Update a exemple
     *
     * @param id     The id of the exemple we want to update
     * @param exemple The new fields content
     * @return The exemple updated
     */
    Exemple updateExemple(long id, Exemple exemple);

    /**
     * Delete a exemple
     *
     * @param id The id of the exemple we want to remove
     */
    void deleteExemple(long id);
}
