package ly.gov.eidc.opendata.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.opendata.domain.enumeration.Chapter;

/**
 * A DTO for the {@link ly.gov.eidc.opendata.domain.Clause} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClauseDTO implements Serializable {

    private Long id;

    private String name;

    private Chapter chapter;

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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClauseDTO)) {
            return false;
        }

        ClauseDTO clauseDTO = (ClauseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clauseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClauseDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", chapter='" + getChapter() + "'" +
            "}";
    }
}
