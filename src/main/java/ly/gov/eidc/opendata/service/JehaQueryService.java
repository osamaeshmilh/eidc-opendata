package ly.gov.eidc.opendata.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.gov.eidc.opendata.domain.*; // for static metamodels
import ly.gov.eidc.opendata.domain.Jeha;
import ly.gov.eidc.opendata.repository.JehaRepository;
import ly.gov.eidc.opendata.service.criteria.JehaCriteria;
import ly.gov.eidc.opendata.service.dto.JehaDTO;
import ly.gov.eidc.opendata.service.mapper.JehaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Jeha} entities in the database.
 * The main input is a {@link JehaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JehaDTO} or a {@link Page} of {@link JehaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JehaQueryService extends QueryService<Jeha> {

    private final Logger log = LoggerFactory.getLogger(JehaQueryService.class);

    private final JehaRepository jehaRepository;

    private final JehaMapper jehaMapper;

    public JehaQueryService(JehaRepository jehaRepository, JehaMapper jehaMapper) {
        this.jehaRepository = jehaRepository;
        this.jehaMapper = jehaMapper;
    }

    /**
     * Return a {@link List} of {@link JehaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JehaDTO> findByCriteria(JehaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Jeha> specification = createSpecification(criteria);
        return jehaMapper.toDto(jehaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JehaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JehaDTO> findByCriteria(JehaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Jeha> specification = createSpecification(criteria);
        return jehaRepository.findAll(specification, page).map(jehaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JehaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Jeha> specification = createSpecification(criteria);
        return jehaRepository.count(specification);
    }

    /**
     * Function to convert {@link JehaCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Jeha> createSpecification(JehaCriteria criteria) {
        Specification<Jeha> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Jeha_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Jeha_.name));
            }
            if (criteria.getWebsite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWebsite(), Jeha_.website));
            }
            if (criteria.getMobileNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobileNo(), Jeha_.mobileNo));
            }
            if (criteria.getFacebookPage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFacebookPage(), Jeha_.facebookPage));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Jeha_.email));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Jeha_.address));
            }
            if (criteria.getPlusCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPlusCode(), Jeha_.plusCode));
            }
            if (criteria.getCenterImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCenterImageUrl(), Jeha_.centerImageUrl));
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(buildSpecification(criteria.getUserId(), root -> root.join(Jeha_.user, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getSectorId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getSectorId(), root -> root.join(Jeha_.sector, JoinType.LEFT).get(Sector_.id))
                    );
            }
        }
        return specification;
    }
}
