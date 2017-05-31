package com.tomkasp.common.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tomkasp.common.domain.Diet;
import com.tomkasp.common.repository.DietRepository;
import com.tomkasp.common.web.rest.util.HeaderUtil;
import com.tomkasp.common.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Diet.
 */
@RestController
@RequestMapping("/api")
public class DietResource {

    private final Logger log = LoggerFactory.getLogger(DietResource.class);

    @Inject
    private DietRepository dietRepository;

    /**
     * POST  /diets : Create a new diet.
     *
     * @param diet the diet to create
     * @return the ResponseEntity with status 201 (Created) and with body the new diet, or with status 400 (Bad Request) if the diet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/diets")
    @Timed
    public ResponseEntity<Diet> createDiet(@Valid @RequestBody Diet diet) throws URISyntaxException {
        log.debug("REST request to save Diet : {}", diet);
        if (diet.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("diet", "idexists", "A new diet cannot already have an ID")).body(null);
        }
        Diet result = dietRepository.save(diet);
        return ResponseEntity.created(new URI("/api/diets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("diet", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /diets : Updates an existing diet.
     *
     * @param diet the diet to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated diet,
     * or with status 400 (Bad Request) if the diet is not valid,
     * or with status 500 (Internal Server Error) if the diet couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/diets")
    @Timed
    public ResponseEntity<Diet> updateDiet(@Valid @RequestBody Diet diet) throws URISyntaxException {
        log.debug("REST request to update Diet : {}", diet);
        if (diet.getId() == null) {
            return createDiet(diet);
        }
        Diet result = dietRepository.save(diet);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("diet", diet.getId().toString()))
            .body(result);
    }

    /**
     * GET  /diets : get all the diets.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of diets in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/diets")
    @Timed
    public ResponseEntity<List<Diet>> getAllDiets(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Diets");
        Page<Diet> page = dietRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/diets");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /diets/:id : get the "id" diet.
     *
     * @param id the id of the diet to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the diet, or with status 404 (Not Found)
     */
    @GetMapping("/diets/{id}")
    @Timed
    public ResponseEntity<Diet> getDiet(@PathVariable Long id) {
        log.debug("REST request to get Diet : {}", id);
        Diet diet = dietRepository.findOne(id);
        return Optional.ofNullable(diet)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /diets/:id : delete the "id" diet.
     *
     * @param id the id of the diet to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/diets/{id}")
    @Timed
    public ResponseEntity<Void> deleteDiet(@PathVariable Long id) {
        log.debug("REST request to delete Diet : {}", id);
        dietRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("diet", id.toString())).build();
    }

}
