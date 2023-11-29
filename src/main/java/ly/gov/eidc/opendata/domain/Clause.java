package ly.gov.eidc.opendata.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import ly.gov.eidc.opendata.domain.enumeration.Chapter;

/**
 * A Clause.
 */
@Entity
@Table(name = "clause")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Clause implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "chapter")
    private Chapter chapter;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Clause id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Clause name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Chapter getChapter() {
        return this.chapter;
    }

    public Clause chapter(Chapter chapter) {
        this.setChapter(chapter);
        return this;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Clause)) {
            return false;
        }
        return getId() != null && getId().equals(((Clause) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Clause{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", chapter='" + getChapter() + "'" +
            "}";
    }
}
