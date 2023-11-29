package ly.gov.eidc.opendata.service.mapper;

import ly.gov.eidc.opendata.domain.Center;
import ly.gov.eidc.opendata.domain.Sector;
import ly.gov.eidc.opendata.domain.User;
import ly.gov.eidc.opendata.service.dto.CenterDTO;
import ly.gov.eidc.opendata.service.dto.SectorDTO;
import ly.gov.eidc.opendata.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Center} and its DTO {@link CenterDTO}.
 */
@Mapper(componentModel = "spring")
public interface CenterMapper extends EntityMapper<CenterDTO, Center> {
    @Mapping(target = "sector", source = "sector", qualifiedByName = "sectorId")
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    CenterDTO toDto(Center s);

    @Named("sectorId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SectorDTO toDtoSectorId(Sector sector);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
