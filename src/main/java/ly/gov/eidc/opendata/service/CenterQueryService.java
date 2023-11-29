package ly.gov.eidc.opendata.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.gov.eidc.opendata.domain.*; // for static metamodels
import ly.gov.eidc.opendata.domain.Center;
import ly.gov.eidc.opendata.repository.CenterRepository;
import ly.gov.eidc.opendata.service.criteria.CenterCriteria;
import ly.gov.eidc.opendata.service.dto.CenterDTO;
import ly.gov.eidc.opendata.service.mapper.CenterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Center} entities in the database.
 * The main input is a {@link CenterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CenterDTO} or a {@link Page} of {@link CenterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CenterQueryService extends QueryService<Center> {

    private final Logger log = LoggerFactory.getLogger(CenterQueryService.class);

    private final CenterRepository centerRepository;

    private final CenterMapper centerMapper;

    public CenterQueryService(CenterRepository centerRepository, CenterMapper centerMapper) {
        this.centerRepository = centerRepository;
        this.centerMapper = centerMapper;
    }

    /**
     * Return a {@link List} of {@link CenterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CenterDTO> findByCriteria(CenterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Center> specification = createSpecification(criteria);
        return centerMapper.toDto(centerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CenterDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CenterDTO> findByCriteria(CenterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Center> specification = createSpecification(criteria);
        return centerRepository.findAll(specification, page).map(centerMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CenterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Center> specification = createSpecification(criteria);
        return centerRepository.count(specification);
    }

    /**
     * Function to convert {@link CenterCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Center> createSpecification(CenterCriteria criteria) {
        Specification<Center> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Center_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Center_.name));
            }
            if (criteria.getWebsite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWebsite(), Center_.website));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), Center_.mobileNo));
            }
            if (criteria.getFacebookPage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFacebookPage(), Center_.facebookPage));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Center_.email));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Center_.address));
            }
            if (criteria.getPlusCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlusCode(), Center_.plusCode));
            }
            if (criteria.getCenterImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCenterImageUrl(), Center_.centerImageUrl));
            }
            if (criteria.getSectorId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getSectorId(), root -> root.join(Center_.sector, JoinType.LEFT).get(Sector_.id))
                    );
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUserId(), root -> root.join(Center_.user, JoinType.LEFT).get(User_.id))
                    );
            }
        }
        return specification;
    }
}
