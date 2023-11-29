package ly.gov.eidc.opendata.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.opendata.domain.enumeration.Reference;

/**
 * A DTO for the {@link ly.gov.eidc.opendata.domain.Attachment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttachmentDTO implements Serializable {

    private Long id;

    private String name;

    @Lob
    private byte[] file;

    private String fileContentType;
    private String fileUrl;

    private Long referenceId;

    private Reference reference;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttachmentDTO)) {
            return false;
        }

        AttachmentDTO attachmentDTO = (AttachmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, attachmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttachmentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", file='" + getFile() + "'" +
            ", fileUrl='" + getFileUrl() + "'" +
            ", referenceId=" + getReferenceId() +
            ", reference='" + getReference() + "'" +
            "}";
    }
}
