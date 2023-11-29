package ly.gov.eidc.opendata.service.mapper;

import ly.gov.eidc.opendata.domain.Clause;
import ly.gov.eidc.opendata.domain.Jeha;
import ly.gov.eidc.opendata.domain.MonthlyPayment;
import ly.gov.eidc.opendata.service.dto.ClauseDTO;
import ly.gov.eidc.opendata.service.dto.JehaDTO;
import ly.gov.eidc.opendata.service.dto.MonthlyPaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MonthlyPayment} and its DTO {@link MonthlyPaymentDTO}.
 */
@Mapper(componentModel = "spring")
public interface MonthlyPaymentMapper extends EntityMapper<MonthlyPaymentDTO, MonthlyPayment> {
    @Mapping(target = "clause", source = "clause", qualifiedByName = "clauseName")
    @Mapping(target = "jeha", source = "jeha", qualifiedByName = "jehaName")
    MonthlyPaymentDTO toDto(MonthlyPayment s);

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
