package ly.gov.eidc.opendata.repository;

import ly.gov.eidc.opendata.domain.Center;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Center entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CenterRepository extends JpaRepository<Center, Long>, JpaSpecificationExecutor<Center> {}
