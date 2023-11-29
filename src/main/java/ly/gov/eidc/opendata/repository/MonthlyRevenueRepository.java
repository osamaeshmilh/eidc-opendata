package ly.gov.eidc.opendata.repository;

import java.util.List;
import java.util.Optional;
import ly.gov.eidc.opendata.domain.MonthlyRevenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MonthlyRevenue entity.
 */
@Repository
public interface MonthlyRevenueRepository extends JpaRepository<MonthlyRevenue, Long>, JpaSpecificationExecutor<MonthlyRevenue> {
    default Optional<MonthlyRevenue> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<MonthlyRevenue> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<MonthlyRevenue> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select monthlyRevenue from MonthlyRevenue monthlyRevenue left join fetch monthlyRevenue.clause left join fetch monthlyRevenue.jeha",
        countQuery = "select count(monthlyRevenue) from MonthlyRevenue monthlyRevenue"
    )
    Page<MonthlyRevenue> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select monthlyRevenue from MonthlyRevenue monthlyRevenue left join fetch monthlyRevenue.clause left join fetch monthlyRevenue.jeha"
    )
    List<MonthlyRevenue> findAllWithToOneRelationships();

    @Query(
        "select monthlyRevenue from MonthlyRevenue monthlyRevenue left join fetch monthlyRevenue.clause left join fetch monthlyRevenue.jeha where monthlyRevenue.id =:id"
    )
    Optional<MonthlyRevenue> findOneWithToOneRelationships(@Param("id") Long id);
}
