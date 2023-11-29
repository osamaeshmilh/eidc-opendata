package ly.gov.eidc.opendata.repository;

import java.util.List;
import java.util.Optional;
import ly.gov.eidc.opendata.domain.MonthlyPayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MonthlyPayment entity.
 */
@Repository
public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment, Long>, JpaSpecificationExecutor<MonthlyPayment> {
    default Optional<MonthlyPayment> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<MonthlyPayment> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<MonthlyPayment> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select monthlyPayment from MonthlyPayment monthlyPayment left join fetch monthlyPayment.clause left join fetch monthlyPayment.jeha",
        countQuery = "select count(monthlyPayment) from MonthlyPayment monthlyPayment"
    )
    Page<MonthlyPayment> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select monthlyPayment from MonthlyPayment monthlyPayment left join fetch monthlyPayment.clause left join fetch monthlyPayment.jeha"
    )
    List<MonthlyPayment> findAllWithToOneRelationships();

    @Query(
        "select monthlyPayment from MonthlyPayment monthlyPayment left join fetch monthlyPayment.clause left join fetch monthlyPayment.jeha where monthlyPayment.id =:id"
    )
    Optional<MonthlyPayment> findOneWithToOneRelationships(@Param("id") Long id);
}
