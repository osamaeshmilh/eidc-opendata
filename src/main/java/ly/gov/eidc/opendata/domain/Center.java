package ly.gov.eidc.opendata.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Center.
 */
@Entity
@Table(name = "center")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Center implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "website")
    private String website;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "facebook_page")
    private String facebookPage;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "plus_code")
    private String plusCode;

    @Lob
    @Column(name = "center_image")
    private byte[] centerImage;

    @Column(name = "center_image_content_type")
    private String centerImageContentType;

    @Column(name = "center_image_url")
    private String centerImageUrl;

    @JsonIgnoreProperties(value = { "user", "center" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Sector sector;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Center id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Center name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return this.website;
    }

    public Center website(String website) {
        this.setWebsite(website);
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Center mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFacebookPage() {
        return this.facebookPage;
    }

    public Center facebookPage(String facebookPage) {
        this.setFacebookPage(facebookPage);
        return this;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    public String getEmail() {
        return this.email;
    }

    public Center email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public Center address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlusCode() {
        return this.plusCode;
    }

    public Center plusCode(String plusCode) {
        this.setPlusCode(plusCode);
        return this;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
    }

    public byte[] getCenterImage() {
        return this.centerImage;
    }

    public Center centerImage(byte[] centerImage) {
        this.setCenterImage(centerImage);
        return this;
    }

    public void setCenterImage(byte[] centerImage) {
        this.centerImage = centerImage;
    }

    public String getCenterImageContentType() {
        return this.centerImageContentType;
    }

    public Center centerImageContentType(String centerImageContentType) {
        this.centerImageContentType = centerImageContentType;
        return this;
    }

    public void setCenterImageContentType(String centerImageContentType) {
        this.centerImageContentType = centerImageContentType;
    }

    public String getCenterImageUrl() {
        return this.centerImageUrl;
    }

    public Center centerImageUrl(String centerImageUrl) {
        this.setCenterImageUrl(centerImageUrl);
        return this;
    }

    public void setCenterImageUrl(String centerImageUrl) {
        this.centerImageUrl = centerImageUrl;
    }

    public Sector getSector() {
        return this.sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Center sector(Sector sector) {
        this.setSector(sector);
        return this;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Center user(User user) {
        this.setUser(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Center)) {
            return false;
        }
        return getId() != null && getId().equals(((Center) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Center{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", website='" + getWebsite() + "'" +
            ", mobileNo='" + getMobileNo() + "'" +
            ", facebookPage='" + getFacebookPage() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", plusCode='" + getPlusCode() + "'" +
            ", centerImage='" + getCenterImage() + "'" +
            ", centerImageContentType='" + getCenterImageContentType() + "'" +
            ", centerImageUrl='" + getCenterImageUrl() + "'" +
            "}";
    }
}
