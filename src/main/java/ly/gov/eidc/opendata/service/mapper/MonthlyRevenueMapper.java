package ly.gov.eidc.opendata.service.mapper;

import ly.gov.eidc.opendata.domain.Clause;
import ly.gov.eidc.opendata.domain.Jeha;
import ly.gov.eidc.opendata.domain.MonthlyRevenue;
import ly.gov.eidc.opendata.service.dto.ClauseDTO;
import ly.gov.eidc.opendata.service.dto.JehaDTO;
import ly.gov.eidc.opendata.service.dto.MonthlyRevenueDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MonthlyRevenue} and its DTO {@link MonthlyRevenueDTO}.
 */
@Mapper(componentModel = "spring")
public interface MonthlyRevenueMapper extends EntityMapper<MonthlyRevenueDTO, MonthlyRevenue> {
    @Mapping(target = "clause", source = "clause", qualifiedByName = "clauseName")
    @Mapping(target = "jeha", source = "jeha", qualifiedByName = "jehaName")
    MonthlyRevenueDTO toDto(MonthlyRevenue s);

    @Named("clauseName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    ClauseDTO toDtoClauseName(Clause clause);

    @Named("jehaName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    JehaDTO toDtoJehaName(Jeha jeha);
}
