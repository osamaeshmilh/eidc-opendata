package ly.gov.eidc.opendata.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.opendata.repository.JehaRepository;
import ly.gov.eidc.opendata.service.JehaQueryService;
import ly.gov.eidc.opendata.service.JehaService;
import ly.gov.eidc.opendata.service.criteria.JehaCriteria;
import ly.gov.eidc.opendata.service.dto.JehaDTO;
import ly.gov.eidc.opendata.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.gov.eidc.opendata.domain.Jeha}.
 */
@RestController
@RequestMapping("/api/jehas")
public class JehaResource {

    private final Logger log = LoggerFactory.getLogger(JehaResource.class);

    private static final String ENTITY_NAME = "jeha";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JehaService jehaService;

    private final JehaRepository jehaRepository;

    private final JehaQueryService jehaQueryService;

    public JehaResource(JehaService jehaService, JehaRepository jehaRepository, JehaQueryService jehaQueryService) {
        this.jehaService = jehaService;
        this.jehaRepository = jehaRepository;
        this.jehaQueryService = jehaQueryService;
    }

    /**
     * {@code POST  /jehas} : Create a new jeha.
     *
     * @param jehaDTO the jehaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jehaDTO, or with status {@code 400 (Bad Request)} if the jeha has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<JehaDTO> createJeha(@RequestBody JehaDTO jehaDTO) throws URISyntaxException {
        log.debug("REST request to save Jeha : {}", jehaDTO);
        if (jehaDTO.getId() != null) {
            throw new BadRequestAlertException("A new jeha cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JehaDTO result = jehaService.save(jehaDTO);
        return ResponseEntity
            .created(new URI("/api/jehas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jehas/:id} : Updates an existing jeha.
     *
     * @param id the id of the jehaDTO to save.
     * @param jehaDTO the jehaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jehaDTO,
     * or with status {@code 400 (Bad Request)} if the jehaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jehaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JehaDTO> updateJeha(@PathVariable(value = "id", required = false) final Long id, @RequestBody JehaDTO jehaDTO)
        throws URISyntaxException {
        log.debug("REST request to update Jeha : {}, {}", id, jehaDTO);
        if (jehaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jehaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jehaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JehaDTO result = jehaService.update(jehaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jehaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /jehas/:id} : Partial updates given fields of an existing jeha, field will ignore if it is null
     *
     * @param id the id of the jehaDTO to save.
     * @param jehaDTO the jehaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jehaDTO,
     * or with status {@code 400 (Bad Request)} if the jehaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the jehaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the jehaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JehaDTO> partialUpdateJeha(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JehaDTO jehaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Jeha partially : {}, {}", id, jehaDTO);
        if (jehaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jehaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jehaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JehaDTO> result = jehaService.partialUpdate(jehaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jehaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /jehas} : get all the jehas.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jehas in body.
     */
    @GetMapping("")
    public ResponseEntity<List<JehaDTO>> getAllJehas(
        JehaCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Jehas by criteria: {}", criteria);

        Page<JehaDTO> page = jehaQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jehas/count} : count all the jehas.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countJehas(JehaCriteria criteria) {
        log.debug("REST request to count Jehas by criteria: {}", criteria);
        return ResponseEntity.ok().body(jehaQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /jehas/:id} : get the "id" jeha.
     *
     * @param id the id of the jehaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jehaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JehaDTO> getJeha(@PathVariable Long id) {
        log.debug("REST request to get Jeha : {}", id);
        Optional<JehaDTO> jehaDTO = jehaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jehaDTO);
    }

    /**
     * {@code DELETE  /jehas/:id} : delete the "id" jeha.
     *
     * @param id the id of the jehaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJeha(@PathVariable Long id) {
        log.debug("REST request to delete Jeha : {}", id);
        jehaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
