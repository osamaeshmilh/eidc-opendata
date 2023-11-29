package ly.gov.eidc.opendata.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.gov.eidc.opendata.domain.*; // for static metamodels
import ly.gov.eidc.opendata.domain.MonthlyRevenue;
import ly.gov.eidc.opendata.repository.MonthlyRevenueRepository;
import ly.gov.eidc.opendata.service.criteria.MonthlyRevenueCriteria;
import ly.gov.eidc.opendata.service.dto.MonthlyRevenueDTO;
import ly.gov.eidc.opendata.service.mapper.MonthlyRevenueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link MonthlyRevenue} entities in the database.
 * The main input is a {@link MonthlyRevenueCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MonthlyRevenueDTO} or a {@link Page} of {@link MonthlyRevenueDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MonthlyRevenueQueryService extends QueryService<MonthlyRevenue> {

    private final Logger log = LoggerFactory.getLogger(MonthlyRevenueQueryService.class);

    private final MonthlyRevenueRepository monthlyRevenueRepository;

    private final MonthlyRevenueMapper monthlyRevenueMapper;

    public MonthlyRevenueQueryService(MonthlyRevenueRepository monthlyRevenueRepository, MonthlyRevenueMapper monthlyRevenueMapper) {
        this.monthlyRevenueRepository = monthlyRevenueRepository;
        this.monthlyRevenueMapper = monthlyRevenueMapper;
    }

    /**
     * Return a {@link List} of {@link MonthlyRevenueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MonthlyRevenueDTO> findByCriteria(MonthlyRevenueCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MonthlyRevenue> specification = createSpecification(criteria);
        return monthlyRevenueMapper.toDto(monthlyRevenueRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MonthlyRevenueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MonthlyRevenueDTO> findByCriteria(MonthlyRevenueCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MonthlyRevenue> specification = createSpecification(criteria);
        return monthlyRevenueRepository.findAll(specification, page).map(monthlyRevenueMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MonthlyRevenueCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MonthlyRevenue> specification = createSpecification(criteria);
        return monthlyRevenueRepository.count(specification);
    }

    /**
     * Function to convert {@link MonthlyRevenueCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MonthlyRevenue> createSpecification(MonthlyRevenueCriteria criteria) {
        Specification<MonthlyRevenue> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MonthlyRevenue_.id));
            }
            if (criteria.getChapter() != null) {
                specification = specification.and(buildSpecification(criteria.getChapter(), MonthlyRevenue_.chapter));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), MonthlyRevenue_.month));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), MonthlyRevenue_.year));
            }
            if (criteria.getRevenueCollected() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getRevenueCollected(), MonthlyRevenue_.revenueCollected));
            }
            if (criteria.getAnnualEstablishedBudget() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getAnnualEstablishedBudget(), MonthlyRevenue_.annualEstablishedBudget)
                    );
            }
            if (criteria.getActualRevenueBeginningToEndLastMonth() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(
                            criteria.getActualRevenueBeginningToEndLastMonth(),
                            MonthlyRevenue_.actualRevenueBeginningToEndLastMonth
                        )
                    );
            }
            if (criteria.getActualRevenueDuringMonth() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getActualRevenueDuringMonth(), MonthlyRevenue_.actualRevenueDuringMonth)
                    );
            }
            if (criteria.getTotalRevenueUntilEndMonth() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getTotalRevenueUntilEndMonth(), MonthlyRevenue_.totalRevenueUntilEndMonth)
                    );
            }
            if (criteria.getExistingConnections() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getExistingConnections(), MonthlyRevenue_.existingConnections));
            }
            if (criteria.getTotalActualRevenue() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getTotalActualRevenue(), MonthlyRevenue_.totalActualRevenue));
            }
            if (criteria.getBalanceOfClause() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBalanceOfClause(), MonthlyRevenue_.balanceOfClause));
            }
            if (criteria.getComments() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComments(), MonthlyRevenue_.comments));
            }
            if (criteria.getClauseId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getClauseId(), root -> root.join(MonthlyRevenue_.clause, JoinType.LEFT).get(Clause_.id))
                    );
            }
            if (criteria.getJehaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getJehaId(), root -> root.join(MonthlyRevenue_.jeha, JoinType.LEFT).get(Jeha_.id))
                    );
            }
        }
        return specification;
    }
}
