package ly.gov.eidc.opendata.service.mapper;

import ly.gov.eidc.opendata.domain.MonthlyPayment;
import ly.gov.eidc.opendata.domain.MonthlyPaymentsDetail;
import ly.gov.eidc.opendata.service.dto.MonthlyPaymentDTO;
import ly.gov.eidc.opendata.service.dto.MonthlyPaymentsDetailDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link MonthlyPaymentsDetail} and its DTO {@link MonthlyPaymentsDetailDTO}.
 */
@Mapper(componentModel = "spring")
public interface MonthlyPaymentsDetailMapper extends EntityMapper<MonthlyPaymentsDetailDTO, MonthlyPaymentsDetail> {
    @Mapping(target = "monthlyPayment", source = "monthlyPayment", qualifiedByName = "monthlyPaymentId")
    MonthlyPaymentsDetailDTO toDto(MonthlyPaymentsDetail s);

    @Named("monthlyPaymentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MonthlyPaymentDTO toDtoMonthlyPaymentId(MonthlyPayment monthlyPayment);
}
