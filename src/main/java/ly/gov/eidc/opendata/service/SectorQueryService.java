package ly.gov.eidc.opendata.service;

import jakarta.persistence.criteria.JoinType;
import java.util.List;
import ly.gov.eidc.opendata.domain.*; // for static metamodels
import ly.gov.eidc.opendata.domain.Sector;
import ly.gov.eidc.opendata.repository.SectorRepository;
import ly.gov.eidc.opendata.service.criteria.SectorCriteria;
import ly.gov.eidc.opendata.service.dto.SectorDTO;
import ly.gov.eidc.opendata.service.mapper.SectorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Sector} entities in the database.
 * The main input is a {@link SectorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SectorDTO} or a {@link Page} of {@link SectorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SectorQueryService extends QueryService<Sector> {

    private final Logger log = LoggerFactory.getLogger(SectorQueryService.class);

    private final SectorRepository sectorRepository;

    private final SectorMapper sectorMapper;

    public SectorQueryService(SectorRepository sectorRepository, SectorMapper sectorMapper) {
        this.sectorRepository = sectorRepository;
        this.sectorMapper = sectorMapper;
    }

    /**
     * Return a {@link List} of {@link SectorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SectorDTO> findByCriteria(SectorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Sector> specification = createSpecification(criteria);
        return sectorMapper.toDto(sectorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SectorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SectorDTO> findByCriteria(SectorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Sector> specification = createSpecification(criteria);
        return sectorRepository.findAll(specification, page).map(sectorMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SectorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Sector> specification = createSpecification(criteria);
        return sectorRepository.count(specification);
    }

    /**
     * Function to convert {@link SectorCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Sector> createSpecification(SectorCriteria criteria) {
        Specification<Sector> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Sector_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Sector_.name));
            }
            if (criteria.getSectorImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSectorImageUrl(), Sector_.sectorImageUrl));
            }
            if (criteria.getUserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getUserId(), root -> root.join(Sector_.user, JoinType.LEFT).get(User_.id))
                    );
            }
            if (criteria.getCenterId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getCenterId(), root -> root.join(Sector_.center, JoinType.LEFT).get(Center_.id))
                    );
            }
        }
        return specification;
    }
}
