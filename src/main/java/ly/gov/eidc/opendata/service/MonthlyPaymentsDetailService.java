package ly.gov.eidc.opendata.service;

import java.util.Optional;
import ly.gov.eidc.opendata.domain.MonthlyPaymentsDetail;
import ly.gov.eidc.opendata.repository.MonthlyPaymentsDetailRepository;
import ly.gov.eidc.opendata.service.dto.MonthlyPaymentsDetailDTO;
import ly.gov.eidc.opendata.service.mapper.MonthlyPaymentsDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.gov.eidc.opendata.domain.MonthlyPaymentsDetail}.
 */
@Service
@Transactional
public class MonthlyPaymentsDetailService {

    private final Logger log = LoggerFactory.getLogger(MonthlyPaymentsDetailService.class);

    private final MonthlyPaymentsDetailRepository monthlyPaymentsDetailRepository;

    private final MonthlyPaymentsDetailMapper monthlyPaymentsDetailMapper;

    public MonthlyPaymentsDetailService(
        MonthlyPaymentsDetailRepository monthlyPaymentsDetailRepository,
        MonthlyPaymentsDetailMapper monthlyPaymentsDetailMapper
    ) {
        this.monthlyPaymentsDetailRepository = monthlyPaymentsDetailRepository;
        this.monthlyPaymentsDetailMapper = monthlyPaymentsDetailMapper;
    }

    /**
     * Save a monthlyPaymentsDetail.
     *
     * @param monthlyPaymentsDetailDTO the entity to save.
     * @return the persisted entity.
     */
    public MonthlyPaymentsDetailDTO save(MonthlyPaymentsDetailDTO monthlyPaymentsDetailDTO) {
        log.debug("Request to save MonthlyPaymentsDetail : {}", monthlyPaymentsDetailDTO);
        MonthlyPaymentsDetail monthlyPaymentsDetail = monthlyPaymentsDetailMapper.toEntity(monthlyPaymentsDetailDTO);
        monthlyPaymentsDetail = monthlyPaymentsDetailRepository.save(monthlyPaymentsDetail);
        return monthlyPaymentsDetailMapper.toDto(monthlyPaymentsDetail);
    }

    /**
     * Update a monthlyPaymentsDetail.
     *
     * @param monthlyPaymentsDetailDTO the entity to save.
     * @return the persisted entity.
     */
    public MonthlyPaymentsDetailDTO update(MonthlyPaymentsDetailDTO monthlyPaymentsDetailDTO) {
        log.debug("Request to update MonthlyPaymentsDetail : {}", monthlyPaymentsDetailDTO);
        MonthlyPaymentsDetail monthlyPaymentsDetail = monthlyPaymentsDetailMapper.toEntity(monthlyPaymentsDetailDTO);
        monthlyPaymentsDetail = monthlyPaymentsDetailRepository.save(monthlyPaymentsDetail);
        return monthlyPaymentsDetailMapper.toDto(monthlyPaymentsDetail);
    }

    /**
     * Partially update a monthlyPaymentsDetail.
     *
     * @param monthlyPaymentsDetailDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MonthlyPaymentsDetailDTO> partialUpdate(MonthlyPaymentsDetailDTO monthlyPaymentsDetailDTO) {
        log.debug("Request to partially update MonthlyPaymentsDetail : {}", monthlyPaymentsDetailDTO);

        return monthlyPaymentsDetailRepository
            .findById(monthlyPaymentsDetailDTO.getId())
            .map(existingMonthlyPaymentsDetail -> {
                monthlyPaymentsDetailMapper.partialUpdate(existingMonthlyPaymentsDetail, monthlyPaymentsDetailDTO);

                return existingMonthlyPaymentsDetail;
            })
            .map(monthlyPaymentsDetailRepository::save)
            .map(monthlyPaymentsDetailMapper::toDto);
    }

    /**
     * Get all the monthlyPaymentsDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MonthlyPaymentsDetailDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MonthlyPaymentsDetails");
        return monthlyPaymentsDetailRepository.findAll(pageable).map(monthlyPaymentsDetailMapper::toDto);
    }

    /**
     * Get one monthlyPaymentsDetail by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MonthlyPaymentsDetailDTO> findOne(Long id) {
        log.debug("Request to get MonthlyPaymentsDetail : {}", id);
        return monthlyPaymentsDetailRepository.findById(id).map(monthlyPaymentsDetailMapper::toDto);
    }

    /**
     * Delete the monthlyPaymentsDetail by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MonthlyPaymentsDetail : {}", id);
        monthlyPaymentsDetailRepository.deleteById(id);
    }
}
