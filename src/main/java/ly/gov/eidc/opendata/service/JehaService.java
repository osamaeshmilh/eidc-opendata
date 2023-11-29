package ly.gov.eidc.opendata.service;

import java.util.Optional;
import ly.gov.eidc.opendata.domain.Jeha;
import ly.gov.eidc.opendata.repository.JehaRepository;
import ly.gov.eidc.opendata.service.dto.JehaDTO;
import ly.gov.eidc.opendata.service.mapper.JehaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.gov.eidc.opendata.domain.Jeha}.
 */
@Service
@Transactional
public class JehaService {

    private final Logger log = LoggerFactory.getLogger(JehaService.class);

    private final JehaRepository jehaRepository;

    private final JehaMapper jehaMapper;

    public JehaService(JehaRepository jehaRepository, JehaMapper jehaMapper) {
        this.jehaRepository = jehaRepository;
        this.jehaMapper = jehaMapper;
    }

    /**
     * Save a jeha.
     *
     * @param jehaDTO the entity to save.
     * @return the persisted entity.
     */
    public JehaDTO save(JehaDTO jehaDTO) {
        log.debug("Request to save Jeha : {}", jehaDTO);
        Jeha jeha = jehaMapper.toEntity(jehaDTO);
        jeha = jehaRepository.save(jeha);
        return jehaMapper.toDto(jeha);
    }

    /**
     * Update a jeha.
     *
     * @param jehaDTO the entity to save.
     * @return the persisted entity.
     */
    public JehaDTO update(JehaDTO jehaDTO) {
        log.debug("Request to update Jeha : {}", jehaDTO);
        Jeha jeha = jehaMapper.toEntity(jehaDTO);
        jeha = jehaRepository.save(jeha);
        return jehaMapper.toDto(jeha);
    }

    /**
     * Partially update a jeha.
     *
     * @param jehaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<JehaDTO> partialUpdate(JehaDTO jehaDTO) {
        log.debug("Request to partially update Jeha : {}", jehaDTO);

        return jehaRepository
            .findById(jehaDTO.getId())
            .map(existingJeha -> {
                jehaMapper.partialUpdate(existingJeha, jehaDTO);

                return existingJeha;
            })
            .map(jehaRepository::save)
            .map(jehaMapper::toDto);
    }

    /**
     * Get all the jehas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<JehaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Jehas");
        return jehaRepository.findAll(pageable).map(jehaMapper::toDto);
    }

    /**
     * Get all the jehas with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<JehaDTO> findAllWithEagerRelationships(Pageable pageable) {
        return jehaRepository.findAllWithEagerRelationships(pageable).map(jehaMapper::toDto);
    }

    /**
     * Get one jeha by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JehaDTO> findOne(Long id) {
        log.debug("Request to get Jeha : {}", id);
        return jehaRepository.findOneWithEagerRelationships(id).map(jehaMapper::toDto);
    }

    /**
     * Delete the jeha by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Jeha : {}", id);
        jehaRepository.deleteById(id);
    }
}
