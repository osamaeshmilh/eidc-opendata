package ly.gov.eidc.opendata.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.gov.eidc.opendata.domain.*; // for static metamodels
import ly.gov.eidc.opendata.domain.MonthlyPayment;
import ly.gov.eidc.opendata.repository.MonthlyPaymentRepository;
import ly.gov.eidc.opendata.service.criteria.MonthlyPaymentCriteria;
import ly.gov.eidc.opendata.service.dto.MonthlyPaymentDTO;
import ly.gov.eidc.opendata.service.mapper.MonthlyPaymentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link MonthlyPayment} entities in the database.
 * The main input is a {@link MonthlyPaymentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MonthlyPaymentDTO} or a {@link Page} of {@link MonthlyPaymentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MonthlyPaymentQueryService extends QueryService<MonthlyPayment> {

    private final Logger log = LoggerFactory.getLogger(MonthlyPaymentQueryService.class);

    private final MonthlyPaymentRepository monthlyPaymentRepository;

    private final MonthlyPaymentMapper monthlyPaymentMapper;

    public MonthlyPaymentQueryService(MonthlyPaymentRepository monthlyPaymentRepository, MonthlyPaymentMapper monthlyPaymentMapper) {
        this.monthlyPaymentRepository = monthlyPaymentRepository;
        this.monthlyPaymentMapper = monthlyPaymentMapper;
    }

    /**
     * Return a {@link List} of {@link MonthlyPaymentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MonthlyPaymentDTO> findByCriteria(MonthlyPaymentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MonthlyPayment> specification = createSpecification(criteria);
        return monthlyPaymentMapper.toDto(monthlyPaymentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MonthlyPaymentDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MonthlyPaymentDTO> findByCriteria(MonthlyPaymentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MonthlyPayment> specification = createSpecification(criteria);
        return monthlyPaymentRepository.findAll(specification, page).map(monthlyPaymentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MonthlyPaymentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MonthlyPayment> specification = createSpecification(criteria);
        return monthlyPaymentRepository.count(specification);
    }

    /**
     * Function to convert {@link MonthlyPaymentCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MonthlyPayment> createSpecification(MonthlyPaymentCriteria criteria) {
        Specification<MonthlyPayment> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MonthlyPayment_.id));
            }
            if (criteria.getChapter() != null) {
                specification = specification.and(buildSpecification(criteria.getChapter(), MonthlyPayment_.chapter));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), MonthlyPayment_.month));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), MonthlyPayment_.year));
            }
            if (criteria.getIsTwelve() != null) {
                specification = specification.and(buildSpecification(criteria.getIsTwelve(), MonthlyPayment_.isTwelve));
            }
            if (criteria.getAnnualEstablishedBudget() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getAnnualEstablishedBudget(), MonthlyPayment_.annualEstablishedBudget)
                    );
            }
            if (criteria.getActualExpensesBeginningToEndLastMonth() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(
                            criteria.getActualExpensesBeginningToEndLastMonth(),
                            MonthlyPayment_.actualExpensesBeginningToEndLastMonth
                        )
                    );
            }
            if (criteria.getActualExpensesDuringMonth() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getActualExpensesDuringMonth(), MonthlyPayment_.actualExpensesDuringMonth)
                    );
            }
            if (criteria.getTotalexpensesUntilEndMonth() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getTotalexpensesUntilEndMonth(), MonthlyPayment_.totalexpensesUntilEndMonth)
                    );
            }
            if (criteria.getExistingConnections() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getExistingConnections(), MonthlyPayment_.existingConnections));
            }
            if (criteria.getTotalActualExpenses() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalActualExpenses(), MonthlyPayment_.totalActualExpenses));
            }
            if (criteria.getBalanceOfClause() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBalanceOfClause(), MonthlyPayment_.balanceOfClause));
            }
            if (criteria.getComments() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComments(), MonthlyPayment_.comments));
            }
            if (criteria.getClauseId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getClauseId(), root -> root.join(MonthlyPayment_.clause, JoinType.LEFT).get(Clause_.id))
                    );
            }
            if (criteria.getJehaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getJehaId(), root -> root.join(MonthlyPayment_.jeha, JoinType.LEFT).get(Jeha_.id))
                    );
            }
            if (criteria.getMonthlyPaymentsDetailId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getMonthlyPaymentsDetailId(),
                            root -> root.join(MonthlyPayment_.monthlyPaymentsDetails, JoinType.LEFT).get(MonthlyPaymentsDetail_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
