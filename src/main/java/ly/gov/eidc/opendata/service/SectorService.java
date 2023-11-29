package ly.gov.eidc.opendata.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import ly.gov.eidc.opendata.domain.Sector;
import ly.gov.eidc.opendata.repository.SectorRepository;
import ly.gov.eidc.opendata.service.dto.SectorDTO;
import ly.gov.eidc.opendata.service.mapper.SectorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.gov.eidc.opendata.domain.Sector}.
 */
@Service
@Transactional
public class SectorService {

    private final Logger log = LoggerFactory.getLogger(SectorService.class);

    private final SectorRepository sectorRepository;

    private final SectorMapper sectorMapper;

    public SectorService(SectorRepository sectorRepository, SectorMapper sectorMapper) {
        this.sectorRepository = sectorRepository;
        this.sectorMapper = sectorMapper;
    }

    /**
     * Save a sector.
     *
     * @param sectorDTO the entity to save.
     * @return the persisted entity.
     */
    public SectorDTO save(SectorDTO sectorDTO) {
        log.debug("Request to save Sector : {}", sectorDTO);
        Sector sector = sectorMapper.toEntity(sectorDTO);
        sector = sectorRepository.save(sector);
        return sectorMapper.toDto(sector);
    }

    /**
     * Update a sector.
     *
     * @param sectorDTO the entity to save.
     * @return the persisted entity.
     */
    public SectorDTO update(SectorDTO sectorDTO) {
        log.debug("Request to update Sector : {}", sectorDTO);
        Sector sector = sectorMapper.toEntity(sectorDTO);
        sector = sectorRepository.save(sector);
        return sectorMapper.toDto(sector);
    }

    /**
     * Partially update a sector.
     *
     * @param sectorDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SectorDTO> partialUpdate(SectorDTO sectorDTO) {
        log.debug("Request to partially update Sector : {}", sectorDTO);

        return sectorRepository
            .findById(sectorDTO.getId())
            .map(existingSector -> {
                sectorMapper.partialUpdate(existingSector, sectorDTO);

                return existingSector;
            })
            .map(sectorRepository::save)
            .map(sectorMapper::toDto);
    }

    /**
     * Get all the sectors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SectorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Sectors");
        return sectorRepository.findAll(pageable).map(sectorMapper::toDto);
    }

    /**
     *  Get all the sectors where Center is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SectorDTO> findAllWhereCenterIsNull() {
        log.debug("Request to get all sectors where Center is null");
        return StreamSupport
            .stream(sectorRepository.findAll().spliterator(), false)
            .filter(sector -> sector.getCenter() == null)
            .map(sectorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one sector by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SectorDTO> findOne(Long id) {
        log.debug("Request to get Sector : {}", id);
        return sectorRepository.findById(id).map(sectorMapper::toDto);
    }

    /**
     * Delete the sector by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Sector : {}", id);
        sectorRepository.deleteById(id);
    }
}
