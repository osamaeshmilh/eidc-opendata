package ly.gov.eidc.opendata.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.gov.eidc.opendata.domain.Center} entity. This class is used
 * in {@link ly.gov.eidc.opendata.web.rest.CenterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /centers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CenterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter website;

    private StringFilter mobileNo;

    private StringFilter facebookPage;

    private StringFilter email;

    private StringFilter address;

    private StringFilter plusCode;

    private StringFilter centerImageUrl;

    private LongFilter sectorId;

    private LongFilter userId;

    private Boolean distinct;

    public CenterCriteria() {}

    public CenterCriteria(CenterCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.website = other.website == null ? null : other.website.copy();
        this.mobileNo = other.mobileNo == null ? null : other.mobileNo.copy();
        this.facebookPage = other.facebookPage == null ? null : other.facebookPage.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.plusCode = other.plusCode == null ? null : other.plusCode.copy();
        this.centerImageUrl = other.centerImageUrl == null ? null : other.centerImageUrl.copy();
        this.sectorId = other.sectorId == null ? null : other.sectorId.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CenterCriteria copy() {
        return new CenterCriteria(this);
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

    public StringFilter getWebsite() {
        return website;
    }

    public StringFilter website() {
        if (website == null) {
            website = new StringFilter();
        }
        return website;
    }

    public void setWebsite(StringFilter website) {
        this.website = website;
    }

    public StringFilter getMobileNo() {
        return mobileNo;
    }

    public StringFilter mobileNo() {
        if (mobileNo == null) {
            mobileNo = new StringFilter();
        }
        return mobileNo;
    }

    public void setMobileNo(StringFilter mobileNo) {
        this.mobileNo = mobileNo;
    }

    public StringFilter getFacebookPage() {
        return facebookPage;
    }

    public StringFilter facebookPage() {
        if (facebookPage == null) {
            facebookPage = new StringFilter();
        }
        return facebookPage;
    }

    public void setFacebookPage(StringFilter facebookPage) {
        this.facebookPage = facebookPage;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getAddress() {
        return address;
    }

    public StringFilter address() {
        if (address == null) {
            address = new StringFilter();
        }
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getPlusCode() {
        return plusCode;
    }

    public StringFilter plusCode() {
        if (plusCode == null) {
            plusCode = new StringFilter();
        }
        return plusCode;
    }

    public void setPlusCode(StringFilter plusCode) {
        this.plusCode = plusCode;
    }

    public StringFilter getCenterImageUrl() {
        return centerImageUrl;
    }

    public StringFilter centerImageUrl() {
        if (centerImageUrl == null) {
            centerImageUrl = new StringFilter();
        }
        return centerImageUrl;
    }

    public void setCenterImageUrl(StringFilter centerImageUrl) {
        this.centerImageUrl = centerImageUrl;
    }

    public LongFilter getSectorId() {
        return sectorId;
    }

    public LongFilter sectorId() {
        if (sectorId == null) {
            sectorId = new LongFilter();
        }
        return sectorId;
    }

    public void setSectorId(LongFilter sectorId) {
        this.sectorId = sectorId;
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
        final CenterCriteria that = (CenterCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(website, that.website) &&
            Objects.equals(mobileNo, that.mobileNo) &&
            Objects.equals(facebookPage, that.facebookPage) &&
            Objects.equals(email, that.email) &&
            Objects.equals(address, that.address) &&
            Objects.equals(plusCode, that.plusCode) &&
            Objects.equals(centerImageUrl, that.centerImageUrl) &&
            Objects.equals(sectorId, that.sectorId) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            website,
            mobileNo,
            facebookPage,
            email,
            address,
            plusCode,
            centerImageUrl,
            sectorId,
            userId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CenterCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (website != null ? "website=" + website + ", " : "") +
            (mobileNo != null ? "mobileNo=" + mobileNo + ", " : "") +
            (facebookPage != null ? "facebookPage=" + facebookPage + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (address != null ? "address=" + address + ", " : "") +
            (plusCode != null ? "plusCode=" + plusCode + ", " : "") +
            (centerImageUrl != null ? "centerImageUrl=" + centerImageUrl + ", " : "") +
            (sectorId != null ? "sectorId=" + sectorId + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
