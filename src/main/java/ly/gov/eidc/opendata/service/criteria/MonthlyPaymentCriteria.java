package ly.gov.eidc.opendata.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.opendata.domain.enumeration.Chapter;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.gov.eidc.opendata.domain.MonthlyPayment} entity. This class is used
 * in {@link ly.gov.eidc.opendata.web.rest.MonthlyPaymentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /monthly-payments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyPaymentCriteria implements Serializable, Criteria {

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

    private BooleanFilter isTwelve;

    private FloatFilter annualEstablishedBudget;

    private FloatFilter actualExpensesBeginningToEndLastMonth;

    private FloatFilter actualExpensesDuringMonth;

    private FloatFilter totalexpensesUntilEndMonth;

    private StringFilter existingConnections;

    private FloatFilter totalActualExpenses;

    private FloatFilter balanceOfClause;

    private StringFilter comments;

    private LongFilter clauseId;

    private LongFilter jehaId;

    private LongFilter monthlyPaymentsDetailId;

    private Boolean distinct;

    public MonthlyPaymentCriteria() {}

    public MonthlyPaymentCriteria(MonthlyPaymentCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.chapter = other.chapter == null ? null : other.chapter.copy();
        this.month = other.month == null ? null : other.month.copy();
        this.year = other.year == null ? null : other.year.copy();
        this.isTwelve = other.isTwelve == null ? null : other.isTwelve.copy();
        this.annualEstablishedBudget = other.annualEstablishedBudget == null ? null : other.annualEstablishedBudget.copy();
        this.actualExpensesBeginningToEndLastMonth =
            other.actualExpensesBeginningToEndLastMonth == null ? null : other.actualExpensesBeginningToEndLastMonth.copy();
        this.actualExpensesDuringMonth = other.actualExpensesDuringMonth == null ? null : other.actualExpensesDuringMonth.copy();
        this.totalexpensesUntilEndMonth = other.totalexpensesUntilEndMonth == null ? null : other.totalexpensesUntilEndMonth.copy();
        this.existingConnections = other.existingConnections == null ? null : other.existingConnections.copy();
        this.totalActualExpenses = other.totalActualExpenses == null ? null : other.totalActualExpenses.copy();
        this.balanceOfClause = other.balanceOfClause == null ? null : other.balanceOfClause.copy();
        this.comments = other.comments == null ? null : other.comments.copy();
        this.clauseId = other.clauseId == null ? null : other.clauseId.copy();
        this.jehaId = other.jehaId == null ? null : other.jehaId.copy();
        this.monthlyPaymentsDetailId = other.monthlyPaymentsDetailId == null ? null : other.monthlyPaymentsDetailId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public MonthlyPaymentCriteria copy() {
        return new MonthlyPaymentCriteria(this);
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

    public BooleanFilter getIsTwelve() {
        return isTwelve;
    }

    public BooleanFilter isTwelve() {
        if (isTwelve == null) {
            isTwelve = new BooleanFilter();
        }
        return isTwelve;
    }

    public void setIsTwelve(BooleanFilter isTwelve) {
        this.isTwelve = isTwelve;
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

    public FloatFilter getActualExpensesBeginningToEndLastMonth() {
        return actualExpensesBeginningToEndLastMonth;
    }

    public FloatFilter actualExpensesBeginningToEndLastMonth() {
        if (actualExpensesBeginningToEndLastMonth == null) {
            actualExpensesBeginningToEndLastMonth = new FloatFilter();
        }
        return actualExpensesBeginningToEndLastMonth;
    }

    public void setActualExpensesBeginningToEndLastMonth(FloatFilter actualExpensesBeginningToEndLastMonth) {
        this.actualExpensesBeginningToEndLastMonth = actualExpensesBeginningToEndLastMonth;
    }

    public FloatFilter getActualExpensesDuringMonth() {
        return actualExpensesDuringMonth;
    }

    public FloatFilter actualExpensesDuringMonth() {
        if (actualExpensesDuringMonth == null) {
            actualExpensesDuringMonth = new FloatFilter();
        }
        return actualExpensesDuringMonth;
    }

    public void setActualExpensesDuringMonth(FloatFilter actualExpensesDuringMonth) {
        this.actualExpensesDuringMonth = actualExpensesDuringMonth;
    }

    public FloatFilter getTotalexpensesUntilEndMonth() {
        return totalexpensesUntilEndMonth;
    }

    public FloatFilter totalexpensesUntilEndMonth() {
        if (totalexpensesUntilEndMonth == null) {
            totalexpensesUntilEndMonth = new FloatFilter();
        }
        return totalexpensesUntilEndMonth;
    }

    public void setTotalexpensesUntilEndMonth(FloatFilter totalexpensesUntilEndMonth) {
        this.totalexpensesUntilEndMonth = totalexpensesUntilEndMonth;
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

    public FloatFilter getTotalActualExpenses() {
        return totalActualExpenses;
    }

    public FloatFilter totalActualExpenses() {
        if (totalActualExpenses == null) {
            totalActualExpenses = new FloatFilter();
        }
        return totalActualExpenses;
    }

    public void setTotalActualExpenses(FloatFilter totalActualExpenses) {
        this.totalActualExpenses = totalActualExpenses;
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

    public LongFilter getMonthlyPaymentsDetailId() {
        return monthlyPaymentsDetailId;
    }

    public LongFilter monthlyPaymentsDetailId() {
        if (monthlyPaymentsDetailId == null) {
            monthlyPaymentsDetailId = new LongFilter();
        }
        return monthlyPaymentsDetailId;
    }

    public void setMonthlyPaymentsDetailId(LongFilter monthlyPaymentsDetailId) {
        this.monthlyPaymentsDetailId = monthlyPaymentsDetailId;
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
        final MonthlyPaymentCriteria that = (MonthlyPaymentCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(chapter, that.chapter) &&
            Objects.equals(month, that.month) &&
            Objects.equals(year, that.year) &&
            Objects.equals(isTwelve, that.isTwelve) &&
            Objects.equals(annualEstablishedBudget, that.annualEstablishedBudget) &&
            Objects.equals(actualExpensesBeginningToEndLastMonth, that.actualExpensesBeginningToEndLastMonth) &&
            Objects.equals(actualExpensesDuringMonth, that.actualExpensesDuringMonth) &&
            Objects.equals(totalexpensesUntilEndMonth, that.totalexpensesUntilEndMonth) &&
            Objects.equals(existingConnections, that.existingConnections) &&
            Objects.equals(totalActualExpenses, that.totalActualExpenses) &&
            Objects.equals(balanceOfClause, that.balanceOfClause) &&
            Objects.equals(comments, that.comments) &&
            Objects.equals(clauseId, that.clauseId) &&
            Objects.equals(jehaId, that.jehaId) &&
            Objects.equals(monthlyPaymentsDetailId, that.monthlyPaymentsDetailId) &&
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
            isTwelve,
            annualEstablishedBudget,
            actualExpensesBeginningToEndLastMonth,
            actualExpensesDuringMonth,
            totalexpensesUntilEndMonth,
            existingConnections,
            totalActualExpenses,
            balanceOfClause,
            comments,
            clauseId,
            jehaId,
            monthlyPaymentsDetailId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyPaymentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (chapter != null ? "chapter=" + chapter + ", " : "") +
            (month != null ? "month=" + month + ", " : "") +
            (year != null ? "year=" + year + ", " : "") +
            (isTwelve != null ? "isTwelve=" + isTwelve + ", " : "") +
            (annualEstablishedBudget != null ? "annualEstablishedBudget=" + annualEstablishedBudget + ", " : "") +
            (actualExpensesBeginningToEndLastMonth != null ? "actualExpensesBeginningToEndLastMonth=" + actualExpensesBeginningToEndLastMonth + ", " : "") +
            (actualExpensesDuringMonth != null ? "actualExpensesDuringMonth=" + actualExpensesDuringMonth + ", " : "") +
            (totalexpensesUntilEndMonth != null ? "totalexpensesUntilEndMonth=" + totalexpensesUntilEndMonth + ", " : "") +
            (existingConnections != null ? "existingConnections=" + existingConnections + ", " : "") +
            (totalActualExpenses != null ? "totalActualExpenses=" + totalActualExpenses + ", " : "") +
            (balanceOfClause != null ? "balanceOfClause=" + balanceOfClause + ", " : "") +
            (comments != null ? "comments=" + comments + ", " : "") +
            (clauseId != null ? "clauseId=" + clauseId + ", " : "") +
            (jehaId != null ? "jehaId=" + jehaId + ", " : "") +
            (monthlyPaymentsDetailId != null ? "monthlyPaymentsDetailId=" + monthlyPaymentsDetailId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
