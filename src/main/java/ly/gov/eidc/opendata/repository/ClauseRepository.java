package ly.gov.eidc.opendata.repository;

import ly.gov.eidc.opendata.domain.Clause;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Clause entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClauseRepository extends JpaRepository<Clause, Long>, JpaSpecificationExecutor<Clause> {}
