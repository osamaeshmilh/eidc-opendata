package ly.gov.eidc.opendata.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.gov.eidc.opendata.domain.Sector} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SectorDTO implements Serializable {

    private Long id;

    private String name;

    @Lob
    private byte[] sectorImage;

    private String sectorImageContentType;
    private String sectorImageUrl;

    private UserDTO user;

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

    public byte[] getSectorImage() {
        return sectorImage;
    }

    public void setSectorImage(byte[] sectorImage) {
        this.sectorImage = sectorImage;
    }

    public String getSectorImageContentType() {
        return sectorImageContentType;
    }

    public void setSectorImageContentType(String sectorImageContentType) {
        this.sectorImageContentType = sectorImageContentType;
    }

    public String getSectorImageUrl() {
        return sectorImageUrl;
    }

    public void setSectorImageUrl(String sectorImageUrl) {
        this.sectorImageUrl = sectorImageUrl;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SectorDTO)) {
            return false;
        }

        SectorDTO sectorDTO = (SectorDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sectorDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SectorDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sectorImage='" + getSectorImage() + "'" +
            ", sectorImageUrl='" + getSectorImageUrl() + "'" +
            ", user=" + getUser() +
            "}";
    }
}
