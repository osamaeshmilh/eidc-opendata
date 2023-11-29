package ly.gov.eidc.opendata.service.dto;

import jakarta.persistence.Lob;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link ly.gov.eidc.opendata.domain.Center} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CenterDTO implements Serializable {

    private Long id;

    private String name;

    private String website;

    private String mobileNo;

    private String facebookPage;

    private String email;

    private String address;

    private String plusCode;

    @Lob
    private byte[] centerImage;

    private String centerImageContentType;
    private String centerImageUrl;

    private SectorDTO sector;

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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFacebookPage() {
        return facebookPage;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlusCode() {
        return plusCode;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
    }

    public byte[] getCenterImage() {
        return centerImage;
    }

    public void setCenterImage(byte[] centerImage) {
        this.centerImage = centerImage;
    }

    public String getCenterImageContentType() {
        return centerImageContentType;
    }

    public void setCenterImageContentType(String centerImageContentType) {
        this.centerImageContentType = centerImageContentType;
    }

    public String getCenterImageUrl() {
        return centerImageUrl;
    }

    public void setCenterImageUrl(String centerImageUrl) {
        this.centerImageUrl = centerImageUrl;
    }

    public SectorDTO getSector() {
        return sector;
    }

    public void setSector(SectorDTO sector) {
        this.sector = sector;
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
        if (!(o instanceof CenterDTO)) {
            return false;
        }

        CenterDTO centerDTO = (CenterDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, centerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CenterDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", website='" + getWebsite() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", facebookPage='" + getFacebookPage() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", plusCode='" + getPlusCode() + "'" +
            ", centerImage='" + getCenterImage() + "'" +
            ", centerImageUrl='" + getCenterImageUrl() + "'" +
            ", sector=" + getSector() +
            ", user=" + getUser() +
            "}";
    }
}
