package ly.gov.eidc.opendata.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import ly.gov.eidc.opendata.domain.enumeration.Chapter;

/**
 * A MonthlyRevenue.
 */
@Entity
@Table(name = "monthly_revenue")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyRevenue implements Serializable {

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

    @Column(name = "revenue_collected")
    private Float revenueCollected;

    @Column(name = "annual_established_budget")
    private Float annualEstablishedBudget;

    @Column(name = "actual_revenue_beginning_to_end_last_month")
    private Float actualRevenueBeginningToEndLastMonth;

    @Column(name = "actual_revenue_during_month")
    private Float actualRevenueDuringMonth;

    @Column(name = "total_revenue_until_end_month")
    private Float totalRevenueUntilEndMonth;

    @Column(name = "existing_connections")
    private String existingConnections;

    @Column(name = "total_actual_revenue")
    private Float totalActualRevenue;

    @Column(name = "balance_of_clause")
    private Float balanceOfClause;

    @Column(name = "comments")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    private Clause clause;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "user", "sector" }, allowSetters = true)
    private Jeha jeha;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public MonthlyRevenue id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chapter getChapter() {
        return this.chapter;
    }

    public MonthlyRevenue chapter(Chapter chapter) {
        this.setChapter(chapter);
        return this;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Integer getMonth() {
        return this.month;
    }

    public MonthlyRevenue month(Integer month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return this.year;
    }

    public MonthlyRevenue year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getRevenueCollected() {
        return this.revenueCollected;
    }

    public MonthlyRevenue revenueCollected(Float revenueCollected) {
        this.setRevenueCollected(revenueCollected);
        return this;
    }

    public void setRevenueCollected(Float revenueCollected) {
        this.revenueCollected = revenueCollected;
    }

    public Float getAnnualEstablishedBudget() {
        return this.annualEstablishedBudget;
    }

    public MonthlyRevenue annualEstablishedBudget(Float annualEstablishedBudget) {
        this.setAnnualEstablishedBudget(annualEstablishedBudget);
        return this;
    }

    public void setAnnualEstablishedBudget(Float annualEstablishedBudget) {
        this.annualEstablishedBudget = annualEstablishedBudget;
    }

    public Float getActualRevenueBeginningToEndLastMonth() {
        return this.actualRevenueBeginningToEndLastMonth;
    }

    public MonthlyRevenue actualRevenueBeginningToEndLastMonth(Float actualRevenueBeginningToEndLastMonth) {
        this.setActualRevenueBeginningToEndLastMonth(actualRevenueBeginningToEndLastMonth);
        return this;
    }

    public void setActualRevenueBeginningToEndLastMonth(Float actualRevenueBeginningToEndLastMonth) {
        this.actualRevenueBeginningToEndLastMonth = actualRevenueBeginningToEndLastMonth;
    }

    public Float getActualRevenueDuringMonth() {
        return this.actualRevenueDuringMonth;
    }

    public MonthlyRevenue actualRevenueDuringMonth(Float actualRevenueDuringMonth) {
        this.setActualRevenueDuringMonth(actualRevenueDuringMonth);
        return this;
    }

    public void setActualRevenueDuringMonth(Float actualRevenueDuringMonth) {
        this.actualRevenueDuringMonth = actualRevenueDuringMonth;
    }

    public Float getTotalRevenueUntilEndMonth() {
        return this.totalRevenueUntilEndMonth;
    }

    public MonthlyRevenue totalRevenueUntilEndMonth(Float totalRevenueUntilEndMonth) {
        this.setTotalRevenueUntilEndMonth(totalRevenueUntilEndMonth);
        return this;
    }

    public void setTotalRevenueUntilEndMonth(Float totalRevenueUntilEndMonth) {
        this.totalRevenueUntilEndMonth = totalRevenueUntilEndMonth;
    }

    public String getExistingConnections() {
        return this.existingConnections;
    }

    public MonthlyRevenue existingConnections(String existingConnections) {
        this.setExistingConnections(existingConnections);
        return this;
    }

    public void setExistingConnections(String existingConnections) {
        this.existingConnections = existingConnections;
    }

    public Float getTotalActualRevenue() {
        return this.totalActualRevenue;
    }

    public MonthlyRevenue totalActualRevenue(Float totalActualRevenue) {
        this.setTotalActualRevenue(totalActualRevenue);
        return this;
    }

    public void setTotalActualRevenue(Float totalActualRevenue) {
        this.totalActualRevenue = totalActualRevenue;
    }

    public Float getBalanceOfClause() {
        return this.balanceOfClause;
    }

    public MonthlyRevenue balanceOfClause(Float balanceOfClause) {
        this.setBalanceOfClause(balanceOfClause);
        return this;
    }

    public void setBalanceOfClause(Float balanceOfClause) {
        this.balanceOfClause = balanceOfClause;
    }

    public String getComments() {
        return this.comments;
    }

    public MonthlyRevenue comments(String comments) {
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

    public MonthlyRevenue clause(Clause clause) {
        this.setClause(clause);
        return this;
    }

    public Jeha getJeha() {
        return this.jeha;
    }

    public void setJeha(Jeha jeha) {
        this.jeha = jeha;
    }

    public MonthlyRevenue jeha(Jeha jeha) {
        this.setJeha(jeha);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MonthlyRevenue)) {
            return false;
        }
        return getId() != null && getId().equals(((MonthlyRevenue) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyRevenue{" +
            "id=" + getId() +
            ", chapter='" + getChapter() + "'" +
            ", month=" + getMonth() +
            ", year=" + getYear() +
            ", revenueCollected=" + getRevenueCollected() +
            ", annualEstablishedBudget=" + getAnnualEstablishedBudget() +
            ", actualRevenueBeginningToEndLastMonth=" + getActualRevenueBeginningToEndLastMonth() +
            ", actualRevenueDuringMonth=" + getActualRevenueDuringMonth() +
            ", totalRevenueUntilEndMonth=" + getTotalRevenueUntilEndMonth() +
            ", existingConnections='" + getExistingConnections() + "'" +
            ", totalActualRevenue=" + getTotalActualRevenue() +
            ", balanceOfClause=" + getBalanceOfClause() +
            ", comments='" + getComments() + "'" +
            "}";
    }
}
