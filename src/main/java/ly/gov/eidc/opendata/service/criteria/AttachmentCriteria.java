package ly.gov.eidc.opendata.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.opendata.domain.enumeration.Reference;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.gov.eidc.opendata.domain.Attachment} entity. This class is used
 * in {@link ly.gov.eidc.opendata.web.rest.AttachmentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /attachments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttachmentCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Reference
     */
    public static class ReferenceFilter extends Filter<Reference> {

        public ReferenceFilter() {}

        public ReferenceFilter(ReferenceFilter filter) {
            super(filter);
        }

        @Override
        public ReferenceFilter copy() {
            return new ReferenceFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter fileUrl;

    private LongFilter referenceId;

    private ReferenceFilter reference;

    private Boolean distinct;

    public AttachmentCriteria() {}

    public AttachmentCriteria(AttachmentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.fileUrl = other.fileUrl == null ? null : other.fileUrl.copy();
        this.referenceId = other.referenceId == null ? null : other.referenceId.copy();
        this.reference = other.reference == null ? null : other.reference.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AttachmentCriteria copy() {
        return new AttachmentCriteria(this);
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

    public StringFilter getFileUrl() {
        return fileUrl;
    }

    public StringFilter fileUrl() {
        if (fileUrl == null) {
            fileUrl = new StringFilter();
        }
        return fileUrl;
    }

    public void setFileUrl(StringFilter fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LongFilter getReferenceId() {
        return referenceId;
    }

    public LongFilter referenceId() {
        if (referenceId == null) {
            referenceId = new LongFilter();
        }
        return referenceId;
    }

    public void setReferenceId(LongFilter referenceId) {
        this.referenceId = referenceId;
    }

    public ReferenceFilter getReference() {
        return reference;
    }

    public ReferenceFilter reference() {
        if (reference == null) {
            reference = new ReferenceFilter();
        }
        return reference;
    }

    public void setReference(ReferenceFilter reference) {
        this.reference = reference;
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
        final AttachmentCriteria that = (AttachmentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(fileUrl, that.fileUrl) &&
            Objects.equals(referenceId, that.referenceId) &&
            Objects.equals(reference, that.reference) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fileUrl, referenceId, reference, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttachmentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (fileUrl != null ? "fileUrl=" + fileUrl + ", " : "") +
            (referenceId != null ? "referenceId=" + referenceId + ", " : "") +
            (reference != null ? "reference=" + reference + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
