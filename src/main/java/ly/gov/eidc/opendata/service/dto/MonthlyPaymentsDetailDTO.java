package ly.gov.eidc.opendata.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link ly.gov.eidc.opendata.domain.MonthlyPaymentsDetail} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyPaymentsDetailDTO implements Serializable {

    private Long id;

    private String beneficiary;

    private Integer payment;

    private LocalDate dateOfPayment;

    private String description;

    private String notes;

    private MonthlyPaymentDTO monthlyPayment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public LocalDate getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDate dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public MonthlyPaymentDTO getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(MonthlyPaymentDTO monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyPaymentsDetailDTO)) {
            return false;
        }

        MonthlyPaymentsDetailDTO monthlyPaymentsDetailDTO = (MonthlyPaymentsDetailDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, monthlyPaymentsDetailDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyPaymentsDetailDTO{" +
            "id=" + getId() +
            ", beneficiary='" + getBeneficiary() + "'" +
            ", payment=" + getPayment() +
            ", dateOfPayment='" + getDateOfPayment() + "'" +
            ", description='" + getDescription() + "'" +
            ", notes='" + getNotes() + "'" +
            ", monthlyPayment=" + getMonthlyPayment() +
            "}";
    }
}
