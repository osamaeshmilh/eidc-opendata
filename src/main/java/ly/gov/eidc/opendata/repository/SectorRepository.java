package ly.gov.eidc.opendata.repository;

import ly.gov.eidc.opendata.domain.Sector;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Sector entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SectorRepository extends JpaRepository<Sector, Long>, JpaSpecificationExecutor<Sector> {}
