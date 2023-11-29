package ly.gov.eidc.opendata.repository;

import java.util.List;
import java.util.Optional;
import ly.gov.eidc.opendata.domain.Jeha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Jeha entity.
 */
@Repository
public interface JehaRepository extends JpaRepository<Jeha, Long>, JpaSpecificationExecutor<Jeha> {
    default Optional<Jeha> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Jeha> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Jeha> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(value = "select jeha from Jeha jeha left join fetch jeha.sector", countQuery = "select count(jeha) from Jeha jeha")
    Page<Jeha> findAllWithToOneRelationships(Pageable pageable);

    @Query("select jeha from Jeha jeha left join fetch jeha.sector")
    List<Jeha> findAllWithToOneRelationships();

    @Query("select jeha from Jeha jeha left join fetch jeha.sector where jeha.id =:id")
    Optional<Jeha> findOneWithToOneRelationships(@Param("id") Long id);
}
