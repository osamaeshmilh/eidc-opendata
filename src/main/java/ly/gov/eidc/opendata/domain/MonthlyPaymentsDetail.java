package ly.gov.eidc.opendata.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A MonthlyPaymentsDetail.
 */
@Entity
@Table(name = "monthly_payments_detail")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyPaymentsDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "beneficiary")
    private String beneficiary;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "date_of_payment")
    private LocalDate dateOfPayment;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "clause", "jeha", "monthlyPaymentsDetails" }, allowSetters = true)
    private MonthlyPayment monthlyPayment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MonthlyPaymentsDetail id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeneficiary() {
        return this.beneficiary;
    }

    public MonthlyPaymentsDetail beneficiary(String beneficiary) {
        this.setBeneficiary(beneficiary);
        return this;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Integer getPayment() {
        return this.payment;
    }

    public MonthlyPaymentsDetail payment(Integer payment) {
        this.setPayment(payment);
        return this;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public LocalDate getDateOfPayment() {
        return this.dateOfPayment;
    }

    public MonthlyPaymentsDetail dateOfPayment(LocalDate dateOfPayment) {
        this.setDateOfPayment(dateOfPayment);
        return this;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public String getDescription() {
        return this.description;
    }

    public MonthlyPaymentsDetail description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return this.notes;
    }

    public MonthlyPaymentsDetail notes(String notes) {
        this.setNotes(notes);
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public MonthlyPayment getMonthlyPayment() {
        return this.monthlyPayment;
    }

    public void setMonthlyPayment(MonthlyPayment monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public MonthlyPaymentsDetail monthlyPayment(MonthlyPayment monthlyPayment) {
        this.setMonthlyPayment(monthlyPayment);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyPaymentsDetail)) {
            return false;
        }
        return getId() != null && getId().equals(((MonthlyPaymentsDetail) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyPaymentsDetail{" +
            "id=" + getId() +
            ", beneficiary='" + getBeneficiary() + "'" +
            ", payment=" + getPayment() +
            ", dateOfPayment='" + getDateOfPayment() + "'" +
            ", description='" + getDescription() + "'" +
            ", notes='" + getNotes() + "'" +
            "}";
    }
}
