package ly.gov.eidc.opendata.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.opendata.domain.enumeration.Chapter;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.gov.eidc.opendata.domain.MonthlyRevenue} entity. This class is used
 * in {@link ly.gov.eidc.opendata.web.rest.MonthlyRevenueResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /monthly-revenues?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyRevenueCriteria implements Serializable, Criteria {

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

    private ChapterFilter chapter;

    private IntegerFilter month;

    private IntegerFilter year;

    private FloatFilter revenueCollected;

    private FloatFilter annualEstablishedBudget;

    private FloatFilter actualRevenueBeginningToEndLastMonth;

    private FloatFilter actualRevenueDuringMonth;

    private FloatFilter totalRevenueUntilEndMonth;

    private StringFilter existingConnections;

    private FloatFilter totalActualRevenue;

    private FloatFilter balanceOfClause;

    private StringFilter comments;

    private LongFilter clauseId;

    private LongFilter jehaId;

    private Boolean distinct;

    public MonthlyRevenueCriteria() {}

    public MonthlyRevenueCriteria(MonthlyRevenueCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.chapter = other.chapter == null ? null : other.chapter.copy();
        this.month = other.month == null ? null : other.month.copy();
        this.year = other.year == null ? null : other.year.copy();
        this.revenueCollected = other.revenueCollected == null ? null : other.revenueCollected.copy();
        this.annualEstablishedBudget = other.annualEstablishedBudget == null ? null : other.annualEstablishedBudget.copy();
        this.actualRevenueBeginningToEndLastMonth =
            other.actualRevenueBeginningToEndLastMonth == null ? null : other.actualRevenueBeginningToEndLastMonth.copy();
        this.actualRevenueDuringMonth = other.actualRevenueDuringMonth == null ? null : other.actualRevenueDuringMonth.copy();
        this.totalRevenueUntilEndMonth = other.totalRevenueUntilEndMonth == null ? null : other.totalRevenueUntilEndMonth.copy();
        this.existingConnections = other.existingConnections == null ? null : other.existingConnections.copy();
        this.totalActualRevenue = other.totalActualRevenue == null ? null : other.totalActualRevenue.copy();
        this.balanceOfClause = other.balanceOfClause == null ? null : other.balanceOfClause.copy();
        this.comments = other.comments == null ? null : other.comments.copy();
        this.clauseId = other.clauseId == null ? null : other.clauseId.copy();
        this.jehaId = other.jehaId == null ? null : other.jehaId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public MonthlyRevenueCriteria copy() {
        return new MonthlyRevenueCriteria(this);
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

    public IntegerFilter getMonth() {
        return month;
    }

    public IntegerFilter month() {
        if (month == null) {
            month = new IntegerFilter();
        }
        return month;
    }

    public void setMonth(IntegerFilter month) {
        this.month = month;
    }

    public IntegerFilter getYear() {
        return year;
    }

    public IntegerFilter year() {
        if (year == null) {
            year = new IntegerFilter();
        }
        return year;
    }

    public void setYear(IntegerFilter year) {
        this.year = year;
    }

    public FloatFilter getRevenueCollected() {
        return revenueCollected;
    }

    public FloatFilter revenueCollected() {
        if (revenueCollected == null) {
            revenueCollected = new FloatFilter();
        }
        return revenueCollected;
    }

    public void setRevenueCollected(FloatFilter revenueCollected) {
        this.revenueCollected = revenueCollected;
    }

    public FloatFilter getAnnualEstablishedBudget() {
        return annualEstablishedBudget;
    }

    public FloatFilter annualEstablishedBudget() {
        if (annualEstablishedBudget == null) {
            annualEstablishedBudget = new FloatFilter();
        }
        return annualEstablishedBudget;
    }

    public void setAnnualEstablishedBudget(FloatFilter annualEstablishedBudget) {
        this.annualEstablishedBudget = annualEstablishedBudget;
    }

    public FloatFilter getActualRevenueBeginningToEndLastMonth() {
        return actualRevenueBeginningToEndLastMonth;
    }

    public FloatFilter actualRevenueBeginningToEndLastMonth() {
        if (actualRevenueBeginningToEndLastMonth == null) {
            actualRevenueBeginningToEndLastMonth = new FloatFilter();
        }
        return actualRevenueBeginningToEndLastMonth;
    }

    public void setActualRevenueBeginningToEndLastMonth(FloatFilter actualRevenueBeginningToEndLastMonth) {
        this.actualRevenueBeginningToEndLastMonth = actualRevenueBeginningToEndLastMonth;
    }

    public FloatFilter getActualRevenueDuringMonth() {
        return actualRevenueDuringMonth;
    }

    public FloatFilter actualRevenueDuringMonth() {
        if (actualRevenueDuringMonth == null) {
            actualRevenueDuringMonth = new FloatFilter();
        }
        return actualRevenueDuringMonth;
    }

    public void setActualRevenueDuringMonth(FloatFilter actualRevenueDuringMonth) {
        this.actualRevenueDuringMonth = actualRevenueDuringMonth;
    }

    public FloatFilter getTotalRevenueUntilEndMonth() {
        return totalRevenueUntilEndMonth;
    }

    public FloatFilter totalRevenueUntilEndMonth() {
        if (totalRevenueUntilEndMonth == null) {
            totalRevenueUntilEndMonth = new FloatFilter();
        }
        return totalRevenueUntilEndMonth;
    }

    public void setTotalRevenueUntilEndMonth(FloatFilter totalRevenueUntilEndMonth) {
        this.totalRevenueUntilEndMonth = totalRevenueUntilEndMonth;
    }

    public StringFilter getExistingConnections() {
        return existingConnections;
    }

    public StringFilter existingConnections() {
        if (existingConnections == null) {
            existingConnections = new StringFilter();
        }
        return existingConnections;
    }

    public void setExistingConnections(StringFilter existingConnections) {
        this.existingConnections = existingConnections;
    }

    public FloatFilter getTotalActualRevenue() {
        return totalActualRevenue;
    }

    public FloatFilter totalActualRevenue() {
        if (totalActualRevenue == null) {
            totalActualRevenue = new FloatFilter();
        }
        return totalActualRevenue;
    }

    public void setTotalActualRevenue(FloatFilter totalActualRevenue) {
        this.totalActualRevenue = totalActualRevenue;
    }

    public FloatFilter getBalanceOfClause() {
        return balanceOfClause;
    }

    public FloatFilter balanceOfClause() {
        if (balanceOfClause == null) {
            balanceOfClause = new FloatFilter();
        }
        return balanceOfClause;
    }

    public void setBalanceOfClause(FloatFilter balanceOfClause) {
        this.balanceOfClause = balanceOfClause;
    }

    public StringFilter getComments() {
        return comments;
    }

    public StringFilter comments() {
        if (comments == null) {
            comments = new StringFilter();
        }
        return comments;
    }

    public void setComments(StringFilter comments) {
        this.comments = comments;
    }

    public LongFilter getClauseId() {
        return clauseId;
    }

    public LongFilter clauseId() {
        if (clauseId == null) {
            clauseId = new LongFilter();
        }
        return clauseId;
    }

    public void setClauseId(LongFilter clauseId) {
        this.clauseId = clauseId;
    }

    public LongFilter getJehaId() {
        return jehaId;
    }

    public LongFilter jehaId() {
        if (jehaId == null) {
            jehaId = new LongFilter();
        }
        return jehaId;
    }

    public void setJehaId(LongFilter jehaId) {
        this.jehaId = jehaId;
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
        final MonthlyRevenueCriteria that = (MonthlyRevenueCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(chapter, that.chapter) &&
            Objects.equals(month, that.month) &&
            Objects.equals(year, that.year) &&
            Objects.equals(revenueCollected, that.revenueCollected) &&
            Objects.equals(annualEstablishedBudget, that.annualEstablishedBudget) &&
            Objects.equals(actualRevenueBeginningToEndLastMonth, that.actualRevenueBeginningToEndLastMonth) &&
            Objects.equals(actualRevenueDuringMonth, that.actualRevenueDuringMonth) &&
            Objects.equals(totalRevenueUntilEndMonth, that.totalRevenueUntilEndMonth) &&
            Objects.equals(existingConnections, that.existingConnections) &&
            Objects.equals(totalActualRevenue, that.totalActualRevenue) &&
            Objects.equals(balanceOfClause, that.balanceOfClause) &&
            Objects.equals(comments, that.comments) &&
            Objects.equals(clauseId, that.clauseId) &&
            Objects.equals(jehaId, that.jehaId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            chapter,
            month,
            year,
            revenueCollected,
            annualEstablishedBudget,
            actualRevenueBeginningToEndLastMonth,
            actualRevenueDuringMonth,
            totalRevenueUntilEndMonth,
            existingConnections,
            totalActualRevenue,
            balanceOfClause,
            comments,
            clauseId,
            jehaId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyRevenueCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (chapter != null ? "chapter=" + chapter + ", " : "") +
            (month != null ? "month=" + month + ", " : "") +
            (year != null ? "year=" + year + ", " : "") +
            (revenueCollected != null ? "revenueCollected=" + revenueCollected + ", " : "") +
            (annualEstablishedBudget != null ? "annualEstablishedBudget=" + annualEstablishedBudget + ", " : "") +
            (actualRevenueBeginningToEndLastMonth != null ? "actualRevenueBeginningToEndLastMonth=" + actualRevenueBeginningToEndLastMonth + ", " : "") +
            (actualRevenueDuringMonth != null ? "actualRevenueDuringMonth=" + actualRevenueDuringMonth + ", " : "") +
            (totalRevenueUntilEndMonth != null ? "totalRevenueUntilEndMonth=" + totalRevenueUntilEndMonth + ", " : "") +
            (existingConnections != null ? "existingConnections=" + existingConnections + ", " : "") +
            (totalActualRevenue != null ? "totalActualRevenue=" + totalActualRevenue + ", " : "") +
            (balanceOfClause != null ? "balanceOfClause=" + balanceOfClause + ", " : "") +
            (comments != null ? "comments=" + comments + ", " : "") +
            (clauseId != null ? "clauseId=" + clauseId + ", " : "") +
            (jehaId != null ? "jehaId=" + jehaId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
