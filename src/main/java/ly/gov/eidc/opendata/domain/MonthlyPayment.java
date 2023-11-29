package ly.gov.eidc.opendata.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import ly.gov.eidc.opendata.domain.enumeration.Chapter;

/**
 * A MonthlyPayment.
 */
@Entity
@Table(name = "monthly_payment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "chapter")
    private Chapter chapter;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "is_twelve")
    private Boolean isTwelve;

    @Column(name = "annual_established_budget")
    private Float annualEstablishedBudget;

    @Column(name = "actual_expenses_beginning_to_end_last_month")
    private Float actualExpensesBeginningToEndLastMonth;

    @Column(name = "actual_expenses_during_month")
    private Float actualExpensesDuringMonth;

    @Column(name = "totalexpenses_until_end_month")
    private Float totalexpensesUntilEndMonth;

    @Column(name = "existing_connections")
    private String existingConnections;

    @Column(name = "total_actual_expenses")
    private Float totalActualExpenses;

    @Column(name = "balance_of_clause")
    private Float balanceOfClause;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    private Clause clause;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "user", "sector" }, allowSetters = true)
    private Jeha jeha;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monthlyPayment")
    @JsonIgnoreProperties(value = { "monthlyPayment" }, allowSetters = true)
    private Set<MonthlyPaymentsDetail> monthlyPaymentsDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MonthlyPayment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chapter getChapter() {
        return this.chapter;
    }

    public MonthlyPayment chapter(Chapter chapter) {
        this.setChapter(chapter);
        return this;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getMonth() {
        return this.month;
    }

    public MonthlyPayment month(Integer month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return this.year;
    }

    public MonthlyPayment year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Boolean getIsTwelve() {
        return this.isTwelve;
    }

    public MonthlyPayment isTwelve(Boolean isTwelve) {
        this.setIsTwelve(isTwelve);
        return this;
    }

    public void setIsTwelve(Boolean isTwelve) {
        this.isTwelve = isTwelve;
    }

    public Float getAnnualEstablishedBudget() {
        return this.annualEstablishedBudget;
    }

    public MonthlyPayment annualEstablishedBudget(Float annualEstablishedBudget) {
        this.setAnnualEstablishedBudget(annualEstablishedBudget);
        return this;
    }

    public void setAnnualEstablishedBudget(Float annualEstablishedBudget) {
        this.annualEstablishedBudget = annualEstablishedBudget;
    }

    public Float getActualExpensesBeginningToEndLastMonth() {
        return this.actualExpensesBeginningToEndLastMonth;
    }

    public MonthlyPayment actualExpensesBeginningToEndLastMonth(Float actualExpensesBeginningToEndLastMonth) {
        this.setActualExpensesBeginningToEndLastMonth(actualExpensesBeginningToEndLastMonth);
        return this;
    }

    public void setActualExpensesBeginningToEndLastMonth(Float actualExpensesBeginningToEndLastMonth) {
        this.actualExpensesBeginningToEndLastMonth = actualExpensesBeginningToEndLastMonth;
    }

    public Float getActualExpensesDuringMonth() {
        return this.actualExpensesDuringMonth;
    }

    public MonthlyPayment actualExpensesDuringMonth(Float actualExpensesDuringMonth) {
        this.setActualExpensesDuringMonth(actualExpensesDuringMonth);
        return this;
    }

    public void setActualExpensesDuringMonth(Float actualExpensesDuringMonth) {
        this.actualExpensesDuringMonth = actualExpensesDuringMonth;
    }

    public Float getTotalexpensesUntilEndMonth() {
        return this.totalexpensesUntilEndMonth;
    }

    public MonthlyPayment totalexpensesUntilEndMonth(Float totalexpensesUntilEndMonth) {
        this.setTotalexpensesUntilEndMonth(totalexpensesUntilEndMonth);
        return this;
    }

    public void setTotalexpensesUntilEndMonth(Float totalexpensesUntilEndMonth) {
        this.totalexpensesUntilEndMonth = totalexpensesUntilEndMonth;
    }

    public String getExistingConnections() {
        return this.existingConnections;
    }

    public MonthlyPayment existingConnections(String existingConnections) {
        this.setExistingConnections(existingConnections);
        return this;
    }

    public void setExistingConnections(String existingConnections) {
        this.existingConnections = existingConnections;
    }

    public Float getTotalActualExpenses() {
        return this.totalActualExpenses;
    }

    public MonthlyPayment totalActualExpenses(Float totalActualExpenses) {
        this.setTotalActualExpenses(totalActualExpenses);
        return this;
    }

    public void setTotalActualExpenses(Float totalActualExpenses) {
        this.totalActualExpenses = totalActualExpenses;
    }

    public Float getBalanceOfClause() {
        return this.balanceOfClause;
    }

    public MonthlyPayment balanceOfClause(Float balanceOfClause) {
        this.setBalanceOfClause(balanceOfClause);
        return this;
    }

    public void setBalanceOfClause(Float balanceOfClause) {
        this.balanceOfClause = balanceOfClause;
    }

    public String getComments() {
        return this.comments;
    }

    public MonthlyPayment comments(String comments) {
        this.setComments(comments);
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Clause getClause() {
        return this.clause;
    }

    public void setClause(Clause clause) {
        this.clause = clause;
    }

    public MonthlyPayment clause(Clause clause) {
        this.setClause(clause);
        return this;
    }

    public Jeha getJeha() {
        return this.jeha;
    }

    public void setJeha(Jeha jeha) {
        this.jeha = jeha;
    }

    public MonthlyPayment jeha(Jeha jeha) {
        this.setJeha(jeha);
        return this;
    }

    public Set<MonthlyPaymentsDetail> getMonthlyPaymentsDetails() {
        return this.monthlyPaymentsDetails;
    }

    public void setMonthlyPaymentsDetails(Set<MonthlyPaymentsDetail> monthlyPaymentsDetails) {
        if (this.monthlyPaymentsDetails != null) {
            this.monthlyPaymentsDetails.forEach(i -> i.setMonthlyPayment(null));
        }
        if (monthlyPaymentsDetails != null) {
            monthlyPaymentsDetails.forEach(i -> i.setMonthlyPayment(this));
        }
        this.monthlyPaymentsDetails = monthlyPaymentsDetails;
    }

    public MonthlyPayment monthlyPaymentsDetails(Set<MonthlyPaymentsDetail> monthlyPaymentsDetails) {
        this.setMonthlyPaymentsDetails(monthlyPaymentsDetails);
        return this;
    }

    public MonthlyPayment addMonthlyPaymentsDetail(MonthlyPaymentsDetail monthlyPaymentsDetail) {
        this.monthlyPaymentsDetails.add(monthlyPaymentsDetail);
        monthlyPaymentsDetail.setMonthlyPayment(this);
        return this;
    }

    public MonthlyPayment removeMonthlyPaymentsDetail(MonthlyPaymentsDetail monthlyPaymentsDetail) {
        this.monthlyPaymentsDetails.remove(monthlyPaymentsDetail);
        monthlyPaymentsDetail.setMonthlyPayment(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyPayment)) {
            return false;
        }
        return getId() != null && getId().equals(((MonthlyPayment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyPayment{" +
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
            "}";
    }
}
