package ly.gov.eidc.opendata.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.opendata.domain.enumeration.Chapter;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.gov.eidc.opendata.domain.Clause} entity. This class is used
 * in {@link ly.gov.eidc.opendata.web.rest.ClauseResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /clauses?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClauseCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Chapter
     */
    public static class ChapterFilter extends Filter<Chapter> {

        public ChapterFilter() {}

        public ChapterFilter(ChapterFilter filter) {
            super(filter);
        }

        @Override
        public ChapterFilter copy() {
            return new ChapterFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private ChapterFilter chapter;

    private Boolean distinct;

    public ClauseCriteria() {}

    public ClauseCriteria(ClauseCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.chapter = other.chapter == null ? null : other.chapter.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ClauseCriteria copy() {
        return new ClauseCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public ChapterFilter getChapter() {
        return chapter;
    }

    public ChapterFilter chapter() {
        if (chapter == null) {
            chapter = new ChapterFilter();
        }
        return chapter;
    }

    public void setChapter(ChapterFilter chapter) {
        this.chapter = chapter;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClauseCriteria that = (ClauseCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(chapter, that.chapter) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, chapter, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClauseCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (chapter != null ? "chapter=" + chapter + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
