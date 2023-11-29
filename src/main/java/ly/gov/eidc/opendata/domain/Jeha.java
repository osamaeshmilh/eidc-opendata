package ly.gov.eidc.opendata.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Jeha.
 */
@Entity
@Table(name = "jeha")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Jeha implements Serializable {

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "user", "center" }, allowSetters = true)
    private Sector sector;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Jeha id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Jeha name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return this.website;
    }

    public Jeha website(String website) {
        this.setWebsite(website);
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Jeha mobileNo(String mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFacebookPage() {
        return this.facebookPage;
    }

    public Jeha facebookPage(String facebookPage) {
        this.setFacebookPage(facebookPage);
        return this;
    }

    public void setFacebookPage(String facebookPage) {
        this.facebookPage = facebookPage;
    }

    public String getEmail() {
        return this.email;
    }

    public Jeha email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public Jeha address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlusCode() {
        return this.plusCode;
    }

    public Jeha plusCode(String plusCode) {
        this.setPlusCode(plusCode);
        return this;
    }

    public void setPlusCode(String plusCode) {
        this.plusCode = plusCode;
    }

    public byte[] getCenterImage() {
        return this.centerImage;
    }

    public Jeha centerImage(byte[] centerImage) {
        this.setCenterImage(centerImage);
        return this;
    }

    public void setCenterImage(byte[] centerImage) {
        this.centerImage = centerImage;
    }

    public String getCenterImageContentType() {
        return this.centerImageContentType;
    }

    public Jeha centerImageContentType(String centerImageContentType) {
        this.centerImageContentType = centerImageContentType;
        return this;
    }

    public void setCenterImageContentType(String centerImageContentType) {
        this.centerImageContentType = centerImageContentType;
    }

    public String getCenterImageUrl() {
        return this.centerImageUrl;
    }

    public Jeha centerImageUrl(String centerImageUrl) {
        this.setCenterImageUrl(centerImageUrl);
        return this;
    }

    public void setCenterImageUrl(String centerImageUrl) {
        this.centerImageUrl = centerImageUrl;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Jeha user(User user) {
        this.setUser(user);
        return this;
    }

    public Sector getSector() {
        return this.sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Jeha sector(Sector sector) {
        this.setSector(sector);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Jeha)) {
            return false;
        }
        return getId() != null && getId().equals(((Jeha) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Jeha{" +
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
