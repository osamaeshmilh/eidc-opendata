package ly.gov.eidc.opendata.service;

import java.util.Optional;
import ly.gov.eidc.opendata.domain.MonthlyRevenue;
import ly.gov.eidc.opendata.repository.MonthlyRevenueRepository;
import ly.gov.eidc.opendata.service.dto.MonthlyRevenueDTO;
import ly.gov.eidc.opendata.service.mapper.MonthlyRevenueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.gov.eidc.opendata.domain.MonthlyRevenue}.
 */
@Service
@Transactional
public class MonthlyRevenueService {

    private final Logger log = LoggerFactory.getLogger(MonthlyRevenueService.class);

    private final MonthlyRevenueRepository monthlyRevenueRepository;

    private final MonthlyRevenueMapper monthlyRevenueMapper;

    public MonthlyRevenueService(MonthlyRevenueRepository monthlyRevenueRepository, MonthlyRevenueMapper monthlyRevenueMapper) {
        this.monthlyRevenueRepository = monthlyRevenueRepository;
        this.monthlyRevenueMapper = monthlyRevenueMapper;
    }

    /**
     * Save a monthlyRevenue.
     *
     * @param monthlyRevenueDTO the entity to save.
     * @return the persisted entity.
     */
    public MonthlyRevenueDTO save(MonthlyRevenueDTO monthlyRevenueDTO) {
        log.debug("Request to save MonthlyRevenue : {}", monthlyRevenueDTO);
        MonthlyRevenue monthlyRevenue = monthlyRevenueMapper.toEntity(monthlyRevenueDTO);
        monthlyRevenue = monthlyRevenueRepository.save(monthlyRevenue);
        return monthlyRevenueMapper.toDto(monthlyRevenue);
    }

    /**
     * Update a monthlyRevenue.
     *
     * @param monthlyRevenueDTO the entity to save.
     * @return the persisted entity.
     */
    public MonthlyRevenueDTO update(MonthlyRevenueDTO monthlyRevenueDTO) {
        log.debug("Request to update MonthlyRevenue : {}", monthlyRevenueDTO);
        MonthlyRevenue monthlyRevenue = monthlyRevenueMapper.toEntity(monthlyRevenueDTO);
        monthlyRevenue = monthlyRevenueRepository.save(monthlyRevenue);
        return monthlyRevenueMapper.toDto(monthlyRevenue);
    }

    /**
     * Partially update a monthlyRevenue.
     *
     * @param monthlyRevenueDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MonthlyRevenueDTO> partialUpdate(MonthlyRevenueDTO monthlyRevenueDTO) {
        log.debug("Request to partially update MonthlyRevenue : {}", monthlyRevenueDTO);

        return monthlyRevenueRepository
            .findById(monthlyRevenueDTO.getId())
            .map(existingMonthlyRevenue -> {
                monthlyRevenueMapper.partialUpdate(existingMonthlyRevenue, monthlyRevenueDTO);

                return existingMonthlyRevenue;
            })
            .map(monthlyRevenueRepository::save)
            .map(monthlyRevenueMapper::toDto);
    }

    /**
     * Get all the monthlyRevenues.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MonthlyRevenueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MonthlyRevenues");
        return monthlyRevenueRepository.findAll(pageable).map(monthlyRevenueMapper::toDto);
    }

    /**
     * Get all the monthlyRevenues with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<MonthlyRevenueDTO> findAllWithEagerRelationships(Pageable pageable) {
        return monthlyRevenueRepository.findAllWithEagerRelationships(pageable).map(monthlyRevenueMapper::toDto);
    }

    /**
     * Get one monthlyRevenue by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MonthlyRevenueDTO> findOne(Long id) {
        log.debug("Request to get MonthlyRevenue : {}", id);
        return monthlyRevenueRepository.findOneWithEagerRelationships(id).map(monthlyRevenueMapper::toDto);
    }

    /**
     * Delete the monthlyRevenue by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MonthlyRevenue : {}", id);
        monthlyRevenueRepository.deleteById(id);
    }
}
