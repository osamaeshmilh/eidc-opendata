package ly.gov.eidc.opendata.service.mapper;

import ly.gov.eidc.opendata.domain.Jeha;
import ly.gov.eidc.opendata.domain.Sector;
import ly.gov.eidc.opendata.domain.User;
import ly.gov.eidc.opendata.service.dto.JehaDTO;
import ly.gov.eidc.opendata.service.dto.SectorDTO;
import ly.gov.eidc.opendata.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Jeha} and its DTO {@link JehaDTO}.
 */
@Mapper(componentModel = "spring")
public interface JehaMapper extends EntityMapper<JehaDTO, Jeha> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "sector", source = "sector", qualifiedByName = "sectorName")
    JehaDTO toDto(Jeha s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("sectorName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    SectorDTO toDtoSectorName(Sector sector);
}
