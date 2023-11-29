package ly.gov.eidc.opendata.service;

import java.util.Optional;
import ly.gov.eidc.opendata.domain.MonthlyPayment;
import ly.gov.eidc.opendata.repository.MonthlyPaymentRepository;
import ly.gov.eidc.opendata.service.dto.MonthlyPaymentDTO;
import ly.gov.eidc.opendata.service.mapper.MonthlyPaymentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ly.gov.eidc.opendata.domain.MonthlyPayment}.
 */
@Service
@Transactional
public class MonthlyPaymentService {

    private final Logger log = LoggerFactory.getLogger(MonthlyPaymentService.class);

    private final MonthlyPaymentRepository monthlyPaymentRepository;

    private final MonthlyPaymentMapper monthlyPaymentMapper;

    public MonthlyPaymentService(MonthlyPaymentRepository monthlyPaymentRepository, MonthlyPaymentMapper monthlyPaymentMapper) {
        this.monthlyPaymentRepository = monthlyPaymentRepository;
        this.monthlyPaymentMapper = monthlyPaymentMapper;
    }

    /**
     * Save a monthlyPayment.
     *
     * @param monthlyPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    public MonthlyPaymentDTO save(MonthlyPaymentDTO monthlyPaymentDTO) {
        log.debug("Request to save MonthlyPayment : {}", monthlyPaymentDTO);
        MonthlyPayment monthlyPayment = monthlyPaymentMapper.toEntity(monthlyPaymentDTO);
        monthlyPayment = monthlyPaymentRepository.save(monthlyPayment);
        return monthlyPaymentMapper.toDto(monthlyPayment);
    }

    /**
     * Update a monthlyPayment.
     *
     * @param monthlyPaymentDTO the entity to save.
     * @return the persisted entity.
     */
    public MonthlyPaymentDTO update(MonthlyPaymentDTO monthlyPaymentDTO) {
        log.debug("Request to update MonthlyPayment : {}", monthlyPaymentDTO);
        MonthlyPayment monthlyPayment = monthlyPaymentMapper.toEntity(monthlyPaymentDTO);
        monthlyPayment = monthlyPaymentRepository.save(monthlyPayment);
        return monthlyPaymentMapper.toDto(monthlyPayment);
    }

    /**
     * Partially update a monthlyPayment.
     *
     * @param monthlyPaymentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MonthlyPaymentDTO> partialUpdate(MonthlyPaymentDTO monthlyPaymentDTO) {
        log.debug("Request to partially update MonthlyPayment : {}", monthlyPaymentDTO);

        return monthlyPaymentRepository
            .findById(monthlyPaymentDTO.getId())
            .map(existingMonthlyPayment -> {
                monthlyPaymentMapper.partialUpdate(existingMonthlyPayment, monthlyPaymentDTO);

                return existingMonthlyPayment;
            })
            .map(monthlyPaymentRepository::save)
            .map(monthlyPaymentMapper::toDto);
    }

    /**
     * Get all the monthlyPayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MonthlyPaymentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MonthlyPayments");
        return monthlyPaymentRepository.findAll(pageable).map(monthlyPaymentMapper::toDto);
    }

    /**
     * Get all the monthlyPayments with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<MonthlyPaymentDTO> findAllWithEagerRelationships(Pageable pageable) {
        return monthlyPaymentRepository.findAllWithEagerRelationships(pageable).map(monthlyPaymentMapper::toDto);
    }

    /**
     * Get one monthlyPayment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MonthlyPaymentDTO> findOne(Long id) {
        log.debug("Request to get MonthlyPayment : {}", id);
        return monthlyPaymentRepository.findOneWithEagerRelationships(id).map(monthlyPaymentMapper::toDto);
    }

    /**
     * Delete the monthlyPayment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MonthlyPayment : {}", id);
        monthlyPaymentRepository.deleteById(id);
    }
}
