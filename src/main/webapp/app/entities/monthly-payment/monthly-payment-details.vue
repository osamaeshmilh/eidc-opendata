<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="monthlyPayment">
        <h2 class="jh-entity-heading" data-cy="monthlyPaymentDetailsHeading"><span>Monthly Payment</span> {{ monthlyPayment.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Chapter</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.chapter }}</span>
          </dd>
          <dt>
            <span>Month</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.month }}</span>
          </dd>
          <dt>
            <span>Year</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.year }}</span>
          </dd>
          <dt>
            <span>Is Twelve</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.isTwelve }}</span>
          </dd>
          <dt>
            <span>Annual Established Budget</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.annualEstablishedBudget }}</span>
          </dd>
          <dt>
            <span>Actual Expenses Beginning To End Last Month</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.actualExpensesBeginningToEndLastMonth }}</span>
          </dd>
          <dt>
            <span>Actual Expenses During Month</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.actualExpensesDuringMonth }}</span>
          </dd>
          <dt>
            <span>Totalexpenses Until End Month</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.totalexpensesUntilEndMonth }}</span>
          </dd>
          <dt>
            <span>Existing Connections</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.existingConnections }}</span>
          </dd>
          <dt>
            <span>Total Actual Expenses</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.totalActualExpenses }}</span>
          </dd>
          <dt>
            <span>Balance Of Clause</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.balanceOfClause }}</span>
          </dd>
          <dt>
            <span>Comments</span>
          </dt>
          <dd>
            <span>{{ monthlyPayment.comments }}</span>
          </dd>
          <dt>
            <span>Clause</span>
          </dt>
          <dd>
            <div v-if="monthlyPayment.clause">
              <router-link :to="{ name: 'ClauseView', params: { clauseId: monthlyPayment.clause.id } }">{{
                monthlyPayment.clause.name
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span>Jeha</span>
          </dt>
          <dd>
            <div v-if="monthlyPayment.jeha">
              <router-link :to="{ name: 'JehaView', params: { jehaId: monthlyPayment.jeha.id } }">{{
                monthlyPayment.jeha.name
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.back</span>
        </button>
        <router-link
          v-if="monthlyPayment.id"
          :to="{ name: 'MonthlyPaymentEdit', params: { monthlyPaymentId: monthlyPayment.id } }"
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

<script lang="ts" src="./monthly-payment-details.component.ts"></script>
