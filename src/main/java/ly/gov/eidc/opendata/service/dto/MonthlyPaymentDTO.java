package ly.gov.eidc.opendata.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.opendata.domain.enumeration.Chapter;

/**
 * A DTO for the {@link ly.gov.eidc.opendata.domain.MonthlyPayment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyPaymentDTO implements Serializable {

    private Long id;

    private Chapter chapter;

    private Integer month;

    private Integer year;

    private Boolean isTwelve;

    private Float annualEstablishedBudget;

    private Float actualExpensesBeginningToEndLastMonth;

    private Float actualExpensesDuringMonth;

    private Float totalexpensesUntilEndMonth;

    private String existingConnections;

    private Float totalActualExpenses;

    private Float balanceOfClause;

    private String comments;

    private ClauseDTO clause;

    private JehaDTO jeha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getIsTwelve() {
        return isTwelve;
    }

    public void setIsTwelve(Boolean isTwelve) {
        this.isTwelve = isTwelve;
    }

    public Float getAnnualEstablishedBudget() {
        return annualEstablishedBudget;
    }

    public void setAnnualEstablishedBudget(Float annualEstablishedBudget) {
        this.annualEstablishedBudget = annualEstablishedBudget;
    }

    public Float getActualExpensesBeginningToEndLastMonth() {
        return actualExpensesBeginningToEndLastMonth;
    }

    public void setActualExpensesBeginningToEndLastMonth(Float actualExpensesBeginningToEndLastMonth) {
        this.actualExpensesBeginningToEndLastMonth = actualExpensesBeginningToEndLastMonth;
    }

    public Float getActualExpensesDuringMonth() {
        return actualExpensesDuringMonth;
    }

    public void setActualExpensesDuringMonth(Float actualExpensesDuringMonth) {
        this.actualExpensesDuringMonth = actualExpensesDuringMonth;
    }

    public Float getTotalexpensesUntilEndMonth() {
        return totalexpensesUntilEndMonth;
    }

    public void setTotalexpensesUntilEndMonth(Float totalexpensesUntilEndMonth) {
        this.totalexpensesUntilEndMonth = totalexpensesUntilEndMonth;
    }

    public String getExistingConnections() {
        return existingConnections;
    }

    public void setExistingConnections(String existingConnections) {
        this.existingConnections = existingConnections;
    }

    public Float getTotalActualExpenses() {
        return totalActualExpenses;
    }

    public void setTotalActualExpenses(Float totalActualExpenses) {
        this.totalActualExpenses = totalActualExpenses;
    }

    public Float getBalanceOfClause() {
        return balanceOfClause;
    }

    public void setBalanceOfClause(Float balanceOfClause) {
        this.balanceOfClause = balanceOfClause;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ClauseDTO getClause() {
        return clause;
    }

    public void setClause(ClauseDTO clause) {
        this.clause = clause;
    }

    public JehaDTO getJeha() {
        return jeha;
    }

    public void setJeha(JehaDTO jeha) {
        this.jeha = jeha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyPaymentDTO)) {
            return false;
        }

        MonthlyPaymentDTO monthlyPaymentDTO = (MonthlyPaymentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, monthlyPaymentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyPaymentDTO{" +
            "id=" + getId() +
            ", chapter='" + getChapter() + "'" +
            ", month=" + getMonth() +
            ", year=" + getYear() +
            ", isTwelve='" + getIsTwelve() + "'" +
            ", annualEstablishedBudget=" + getAnnualEstablishedBudget() +
            ", actualExpensesBeginningToEndLastMonth=" + getActualExpensesBeginningToEndLastMonth() +
            ", actualExpensesDuringMonth=" + getActualExpensesDuringMonth() +
            ", totalexpensesUntilEndMonth=" + getTotalexpensesUntilEndMonth() +
            ", existingConnections='" + getExistingConnections() + "'" +
            ", totalActualExpenses=" + getTotalActualExpenses() +
            ", balanceOfClause=" + getBalanceOfClause() +
            ", comments='" + getComments() + "'" +
            ", clause=" + getClause() +
            ", jeha=" + getJeha() +
            "}";
    }
}
