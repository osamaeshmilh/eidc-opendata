package ly.gov.eidc.opendata.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.gov.eidc.opendata.domain.*; // for static metamodels
import ly.gov.eidc.opendata.domain.MonthlyPaymentsDetail;
import ly.gov.eidc.opendata.repository.MonthlyPaymentsDetailRepository;
import ly.gov.eidc.opendata.service.criteria.MonthlyPaymentsDetailCriteria;
import ly.gov.eidc.opendata.service.dto.MonthlyPaymentsDetailDTO;
import ly.gov.eidc.opendata.service.mapper.MonthlyPaymentsDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link MonthlyPaymentsDetail} entities in the database.
 * The main input is a {@link MonthlyPaymentsDetailCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MonthlyPaymentsDetailDTO} or a {@link Page} of {@link MonthlyPaymentsDetailDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MonthlyPaymentsDetailQueryService extends QueryService<MonthlyPaymentsDetail> {

    private final Logger log = LoggerFactory.getLogger(MonthlyPaymentsDetailQueryService.class);

    private final MonthlyPaymentsDetailRepository monthlyPaymentsDetailRepository;

    private final MonthlyPaymentsDetailMapper monthlyPaymentsDetailMapper;

    public MonthlyPaymentsDetailQueryService(
        MonthlyPaymentsDetailRepository monthlyPaymentsDetailRepository,
        MonthlyPaymentsDetailMapper monthlyPaymentsDetailMapper
    ) {
        this.monthlyPaymentsDetailRepository = monthlyPaymentsDetailRepository;
        this.monthlyPaymentsDetailMapper = monthlyPaymentsDetailMapper;
    }

    /**
     * Return a {@link List} of {@link MonthlyPaymentsDetailDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MonthlyPaymentsDetailDTO> findByCriteria(MonthlyPaymentsDetailCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MonthlyPaymentsDetail> specification = createSpecification(criteria);
        return monthlyPaymentsDetailMapper.toDto(monthlyPaymentsDetailRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MonthlyPaymentsDetailDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MonthlyPaymentsDetailDTO> findByCriteria(MonthlyPaymentsDetailCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MonthlyPaymentsDetail> specification = createSpecification(criteria);
        return monthlyPaymentsDetailRepository.findAll(specification, page).map(monthlyPaymentsDetailMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MonthlyPaymentsDetailCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MonthlyPaymentsDetail> specification = createSpecification(criteria);
        return monthlyPaymentsDetailRepository.count(specification);
    }

    /**
     * Function to convert {@link MonthlyPaymentsDetailCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MonthlyPaymentsDetail> createSpecification(MonthlyPaymentsDetailCriteria criteria) {
        Specification<MonthlyPaymentsDetail> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MonthlyPaymentsDetail_.id));
            }
            if (criteria.getBeneficiary() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBeneficiary(), MonthlyPaymentsDetail_.beneficiary));
            }
            if (criteria.getPayment() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPayment(), MonthlyPaymentsDetail_.payment));
            }
            if (criteria.getDateOfPayment() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getDateOfPayment(), MonthlyPaymentsDetail_.dateOfPayment));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MonthlyPaymentsDetail_.description));
            }
            if (criteria.getNotes() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNotes(), MonthlyPaymentsDetail_.notes));
            }
            if (criteria.getMonthlyPaymentId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getMonthlyPaymentId(),
                            root -> root.join(MonthlyPaymentsDetail_.monthlyPayment, JoinType.LEFT).get(MonthlyPayment_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
