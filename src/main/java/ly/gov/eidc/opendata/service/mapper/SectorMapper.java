package ly.gov.eidc.opendata.service.mapper;

import ly.gov.eidc.opendata.domain.Sector;
import ly.gov.eidc.opendata.domain.User;
import ly.gov.eidc.opendata.service.dto.SectorDTO;
import ly.gov.eidc.opendata.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Sector} and its DTO {@link SectorDTO}.
 */
@Mapper(componentModel = "spring")
public interface SectorMapper extends EntityMapper<SectorDTO, Sector> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    SectorDTO toDto(Sector s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
