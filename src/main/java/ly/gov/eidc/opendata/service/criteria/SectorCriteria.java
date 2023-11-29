package ly.gov.eidc.opendata.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.gov.eidc.opendata.domain.Sector} entity. This class is used
 * in {@link ly.gov.eidc.opendata.web.rest.SectorResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /sectors?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SectorCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter sectorImageUrl;

    private LongFilter userId;

    private LongFilter centerId;

    private Boolean distinct;

    public SectorCriteria() {}

    public SectorCriteria(SectorCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.sectorImageUrl = other.sectorImageUrl == null ? null : other.sectorImageUrl.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.centerId = other.centerId == null ? null : other.centerId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public SectorCriteria copy() {
        return new SectorCriteria(this);
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

    public StringFilter getSectorImageUrl() {
        return sectorImageUrl;
    }

    public StringFilter sectorImageUrl() {
        if (sectorImageUrl == null) {
            sectorImageUrl = new StringFilter();
        }
        return sectorImageUrl;
    }

    public void setSectorImageUrl(StringFilter sectorImageUrl) {
        this.sectorImageUrl = sectorImageUrl;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public LongFilter userId() {
        if (userId == null) {
            userId = new LongFilter();
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public LongFilter getCenterId() {
        return centerId;
    }

    public LongFilter centerId() {
        if (centerId == null) {
            centerId = new LongFilter();
        }
        return centerId;
    }

    public void setCenterId(LongFilter centerId) {
        this.centerId = centerId;
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
        final SectorCriteria that = (SectorCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(sectorImageUrl, that.sectorImageUrl) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(centerId, that.centerId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sectorImageUrl, userId, centerId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SectorCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (sectorImageUrl != null ? "sectorImageUrl=" + sectorImageUrl + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (centerId != null ? "centerId=" + centerId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
