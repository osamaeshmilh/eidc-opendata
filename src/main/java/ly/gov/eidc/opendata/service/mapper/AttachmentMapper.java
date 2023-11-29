package ly.gov.eidc.opendata.service.mapper;

import ly.gov.eidc.opendata.domain.Attachment;
import ly.gov.eidc.opendata.service.dto.AttachmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Attachment} and its DTO {@link AttachmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {}
