package ly.gov.eidc.opendata.service;

import java.util.Optional;
import ly.gov.eidc.opendata.domain.Clause;
import ly.gov.eidc.opendata.repository.ClauseRepository;
import ly.gov.eidc.opendata.service.dto.ClauseDTO;
import ly.gov.eidc.opendata.service.mapper.ClauseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.gov.eidc.opendata.domain.Clause}.
 */
@Service
@Transactional
public class ClauseService {

    private final Logger log = LoggerFactory.getLogger(ClauseService.class);

    private final ClauseRepository clauseRepository;

    private final ClauseMapper clauseMapper;

    public ClauseService(ClauseRepository clauseRepository, ClauseMapper clauseMapper) {
        this.clauseRepository = clauseRepository;
        this.clauseMapper = clauseMapper;
    }

    /**
     * Save a clause.
     *
     * @param clauseDTO the entity to save.
     * @return the persisted entity.
     */
    public ClauseDTO save(ClauseDTO clauseDTO) {
        log.debug("Request to save Clause : {}", clauseDTO);
        Clause clause = clauseMapper.toEntity(clauseDTO);
        clause = clauseRepository.save(clause);
        return clauseMapper.toDto(clause);
    }

    /**
     * Update a clause.
     *
     * @param clauseDTO the entity to save.
     * @return the persisted entity.
     */
    public ClauseDTO update(ClauseDTO clauseDTO) {
        log.debug("Request to update Clause : {}", clauseDTO);
        Clause clause = clauseMapper.toEntity(clauseDTO);
        clause = clauseRepository.save(clause);
        return clauseMapper.toDto(clause);
    }

    /**
     * Partially update a clause.
     *
     * @param clauseDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ClauseDTO> partialUpdate(ClauseDTO clauseDTO) {
        log.debug("Request to partially update Clause : {}", clauseDTO);

        return clauseRepository
            .findById(clauseDTO.getId())
            .map(existingClause -> {
                clauseMapper.partialUpdate(existingClause, clauseDTO);

                return existingClause;
            })
            .map(clauseRepository::save)
            .map(clauseMapper::toDto);
    }

    /**
     * Get all the clauses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ClauseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Clauses");
        return clauseRepository.findAll(pageable).map(clauseMapper::toDto);
    }

    /**
     * Get one clause by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ClauseDTO> findOne(Long id) {
        log.debug("Request to get Clause : {}", id);
        return clauseRepository.findById(id).map(clauseMapper::toDto);
    }

    /**
     * Delete the clause by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Clause : {}", id);
        clauseRepository.deleteById(id);
    }
}
