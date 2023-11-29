<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="monthlyRevenue">
        <h2 class="jh-entity-heading" data-cy="monthlyRevenueDetailsHeading"><span>Monthly Revenue</span> {{ monthlyRevenue.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Chapter</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.chapter }}</span>
          </dd>
          <dt>
            <span>Month</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.month }}</span>
          </dd>
          <dt>
            <span>Year</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.year }}</span>
          </dd>
          <dt>
            <span>Revenue Collected</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.revenueCollected }}</span>
          </dd>
          <dt>
            <span>Annual Established Budget</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.annualEstablishedBudget }}</span>
          </dd>
          <dt>
            <span>Actual Revenue Beginning To End Last Month</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.actualRevenueBeginningToEndLastMonth }}</span>
          </dd>
          <dt>
            <span>Actual Revenue During Month</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.actualRevenueDuringMonth }}</span>
          </dd>
          <dt>
            <span>Total Revenue Until End Month</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.totalRevenueUntilEndMonth }}</span>
          </dd>
          <dt>
            <span>Existing Connections</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.existingConnections }}</span>
          </dd>
          <dt>
            <span>Total Actual Revenue</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.totalActualRevenue }}</span>
          </dd>
          <dt>
            <span>Balance Of Clause</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.balanceOfClause }}</span>
          </dd>
          <dt>
            <span>Comments</span>
          </dt>
          <dd>
            <span>{{ monthlyRevenue.comments }}</span>
          </dd>
          <dt>
            <span>Clause</span>
          </dt>
          <dd>
            <div v-if="monthlyRevenue.clause">
              <router-link :to="{ name: 'ClauseView', params: { clauseId: monthlyRevenue.clause.id } }">{{
                monthlyRevenue.clause.name
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>Jeha</span>
          </dt>
          <dd>
            <div v-if="monthlyRevenue.jeha">
              <router-link :to="{ name: 'JehaView', params: { jehaId: monthlyRevenue.jeha.id } }">{{
                monthlyRevenue.jeha.name
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.back</span>
        </button>
        <router-link
          v-if="monthlyRevenue.id"
          :to="{ name: 'MonthlyRevenueEdit', params: { monthlyRevenueId: monthlyRevenue.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./monthly-revenue-details.component.ts"></script>
