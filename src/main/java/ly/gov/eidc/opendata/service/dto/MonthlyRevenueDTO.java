package ly.gov.eidc.opendata.service.dto;

import java.io.Serializable;
import java.util.Objects;
import ly.gov.eidc.opendata.domain.enumeration.Chapter;

/**
 * A DTO for the {@link ly.gov.eidc.opendata.domain.MonthlyRevenue} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MonthlyRevenueDTO implements Serializable {

    private Long id;

    private Chapter chapter;

    private Integer month;

    private Integer year;

    private Float revenueCollected;

    private Float annualEstablishedBudget;

    private Float actualRevenueBeginningToEndLastMonth;

    private Float actualRevenueDuringMonth;

    private Float totalRevenueUntilEndMonth;

    private String existingConnections;

    private Float totalActualRevenue;

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

    public Float getRevenueCollected() {
        return revenueCollected;
    }

    public void setRevenueCollected(Float revenueCollected) {
        this.revenueCollected = revenueCollected;
    }

    public Float getAnnualEstablishedBudget() {
        return annualEstablishedBudget;
    }

    public void setAnnualEstablishedBudget(Float annualEstablishedBudget) {
        this.annualEstablishedBudget = annualEstablishedBudget;
    }

    public Float getActualRevenueBeginningToEndLastMonth() {
        return actualRevenueBeginningToEndLastMonth;
    }

    public void setActualRevenueBeginningToEndLastMonth(Float actualRevenueBeginningToEndLastMonth) {
        this.actualRevenueBeginningToEndLastMonth = actualRevenueBeginningToEndLastMonth;
    }

    public Float getActualRevenueDuringMonth() {
        return actualRevenueDuringMonth;
    }

    public void setActualRevenueDuringMonth(Float actualRevenueDuringMonth) {
        this.actualRevenueDuringMonth = actualRevenueDuringMonth;
    }

    public Float getTotalRevenueUntilEndMonth() {
        return totalRevenueUntilEndMonth;
    }

    public void setTotalRevenueUntilEndMonth(Float totalRevenueUntilEndMonth) {
        this.totalRevenueUntilEndMonth = totalRevenueUntilEndMonth;
    }

    public String getExistingConnections() {
        return existingConnections;
    }

    public void setExistingConnections(String existingConnections) {
        this.existingConnections = existingConnections;
    }

    public Float getTotalActualRevenue() {
        return totalActualRevenue;
    }

    public void setTotalActualRevenue(Float totalActualRevenue) {
        this.totalActualRevenue = totalActualRevenue;
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
        if (!(o instanceof MonthlyRevenueDTO)) {
            return false;
        }

        MonthlyRevenueDTO monthlyRevenueDTO = (MonthlyRevenueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, monthlyRevenueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MonthlyRevenueDTO{" +
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
            ", clause=" + getClause() +
            ", jeha=" + getJeha() +
            "}";
    }
}
