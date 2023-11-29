package ly.gov.eidc.opendata.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.core.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link ly.gov.eidc.opendata.domain.MonthlyPaymentsDetail} entity. This class is used
 * in {@link ly.gov.eidc.opendata.web.rest.MonthlyPaymentsDetailResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /monthly-payments-details?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyPaymentsDetailCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter beneficiary;

    private IntegerFilter payment;

    private LocalDateFilter dateOfPayment;

    private StringFilter description;

    private StringFilter notes;

    private LongFilter monthlyPaymentId;

    private Boolean distinct;

    public MonthlyPaymentsDetailCriteria() {}

    public MonthlyPaymentsDetailCriteria(MonthlyPaymentsDetailCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.beneficiary = other.beneficiary == null ? null : other.beneficiary.copy();
        this.payment = other.payment == null ? null : other.payment.copy();
        this.dateOfPayment = other.dateOfPayment == null ? null : other.dateOfPayment.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.notes = other.notes == null ? null : other.notes.copy();
        this.monthlyPaymentId = other.monthlyPaymentId == null ? null : other.monthlyPaymentId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public MonthlyPaymentsDetailCriteria copy() {
        return new MonthlyPaymentsDetailCriteria(this);
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

    public StringFilter getBeneficiary() {
        return beneficiary;
    }

    public StringFilter beneficiary() {
        if (beneficiary == null) {
            beneficiary = new StringFilter();
        }
        return beneficiary;
    }

    public void setBeneficiary(StringFilter beneficiary) {
        this.beneficiary = beneficiary;
    }

    public IntegerFilter getPayment() {
        return payment;
    }

    public IntegerFilter payment() {
        if (payment == null) {
            payment = new IntegerFilter();
        }
        return payment;
    }

    public void setPayment(IntegerFilter payment) {
        this.payment = payment;
    }

    public LocalDateFilter getDateOfPayment() {
        return dateOfPayment;
    }

    public LocalDateFilter dateOfPayment() {
        if (dateOfPayment == null) {
            dateOfPayment = new LocalDateFilter();
        }
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDateFilter dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getNotes() {
        return notes;
    }

    public StringFilter notes() {
        if (notes == null) {
            notes = new StringFilter();
        }
        return notes;
    }

    public void setNotes(StringFilter notes) {
        this.notes = notes;
    }

    public LongFilter getMonthlyPaymentId() {
        return monthlyPaymentId;
    }

    public LongFilter monthlyPaymentId() {
        if (monthlyPaymentId == null) {
            monthlyPaymentId = new LongFilter();
        }
        return monthlyPaymentId;
    }

    public void setMonthlyPaymentId(LongFilter monthlyPaymentId) {
        this.monthlyPaymentId = monthlyPaymentId;
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
        final MonthlyPaymentsDetailCriteria that = (MonthlyPaymentsDetailCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(beneficiary, that.beneficiary) &&
            Objects.equals(payment, that.payment) &&
            Objects.equals(dateOfPayment, that.dateOfPayment) &&
            Objects.equals(description, that.description) &&
            Objects.equals(notes, that.notes) &&
            Objects.equals(monthlyPaymentId, that.monthlyPaymentId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beneficiary, payment, dateOfPayment, description, notes, monthlyPaymentId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyPaymentsDetailCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (beneficiary != null ? "beneficiary=" + beneficiary + ", " : "") +
            (payment != null ? "payment=" + payment + ", " : "") +
            (dateOfPayment != null ? "dateOfPayment=" + dateOfPayment + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (notes != null ? "notes=" + notes + ", " : "") +
            (monthlyPaymentId != null ? "monthlyPaymentId=" + monthlyPaymentId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
