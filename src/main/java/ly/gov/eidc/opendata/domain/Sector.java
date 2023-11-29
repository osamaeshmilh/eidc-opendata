package ly.gov.eidc.opendata.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Sector.
 */
@Entity
@Table(name = "sector")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Sector implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "sector_image")
    private byte[] sectorImage;

    @Column(name = "sector_image_content_type")
    private String sectorImageContentType;

    @Column(name = "sector_image_url")
    private String sectorImageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    @JsonIgnoreProperties(value = { "sector", "user" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "sector")
    private Center center;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Sector id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Sector name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getSectorImage() {
        return this.sectorImage;
    }

    public Sector sectorImage(byte[] sectorImage) {
        this.setSectorImage(sectorImage);
        return this;
    }

    public void setSectorImage(byte[] sectorImage) {
        this.sectorImage = sectorImage;
    }

    public String getSectorImageContentType() {
        return this.sectorImageContentType;
    }

    public Sector sectorImageContentType(String sectorImageContentType) {
        this.sectorImageContentType = sectorImageContentType;
        return this;
    }

    public void setSectorImageContentType(String sectorImageContentType) {
        this.sectorImageContentType = sectorImageContentType;
    }

    public String getSectorImageUrl() {
        return this.sectorImageUrl;
    }

    public Sector sectorImageUrl(String sectorImageUrl) {
        this.setSectorImageUrl(sectorImageUrl);
        return this;
    }

    public void setSectorImageUrl(String sectorImageUrl) {
        this.sectorImageUrl = sectorImageUrl;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sector user(User user) {
        this.setUser(user);
        return this;
    }

    public Center getCenter() {
        return this.center;
    }

    public void setCenter(Center center) {
        if (this.center != null) {
            this.center.setSector(null);
        }
        if (center != null) {
            center.setSector(this);
        }
        this.center = center;
    }

    public Sector center(Center center) {
        this.setCenter(center);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sector)) {
            return false;
        }
        return getId() != null && getId().equals(((Sector) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sector{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sectorImage='" + getSectorImage() + "'" +
            ", sectorImageContentType='" + getSectorImageContentType() + "'" +
            ", sectorImageUrl='" + getSectorImageUrl() + "'" +
            "}";
    }
}
