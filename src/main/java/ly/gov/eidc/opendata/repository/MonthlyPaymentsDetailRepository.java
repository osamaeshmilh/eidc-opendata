package ly.gov.eidc.opendata.repository;

import ly.gov.eidc.opendata.domain.MonthlyPaymentsDetail;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MonthlyPaymentsDetail entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonthlyPaymentsDetailRepository
    extends JpaRepository<MonthlyPaymentsDetail, Long>, JpaSpecificationExecutor<MonthlyPaymentsDetail> {}
