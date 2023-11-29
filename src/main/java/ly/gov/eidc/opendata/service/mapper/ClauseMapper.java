package ly.gov.eidc.opendata.service.mapper;

import ly.gov.eidc.opendata.domain.Clause;
import ly.gov.eidc.opendata.service.dto.ClauseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Clause} and its DTO {@link ClauseDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClauseMapper extends EntityMapper<ClauseDTO, Clause> {}
