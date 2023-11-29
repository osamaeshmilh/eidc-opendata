<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="monthlyPaymentsDetail">
        <h2 class="jh-entity-heading" data-cy="monthlyPaymentsDetailDetailsHeading">
          <span>Monthly Payments Detail</span> {{ monthlyPaymentsDetail.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Beneficiary</span>
          </dt>
          <dd>
            <span>{{ monthlyPaymentsDetail.beneficiary }}</span>
          </dd>
          <dt>
            <span>Payment</span>
          </dt>
          <dd>
            <span>{{ monthlyPaymentsDetail.payment }}</span>
          </dd>
          <dt>
            <span>Date Of Payment</span>
          </dt>
          <dd>
            <span>{{ monthlyPaymentsDetail.dateOfPayment }}</span>
          </dd>
          <dt>
            <span>Description</span>
          </dt>
          <dd>
            <span>{{ monthlyPaymentsDetail.description }}</span>
          </dd>
          <dt>
            <span>Notes</span>
          </dt>
          <dd>
            <span>{{ monthlyPaymentsDetail.notes }}</span>
          </dd>
          <dt>
            <span>Monthly Payment</span>
          </dt>
          <dd>
            <div v-if="monthlyPaymentsDetail.monthlyPayment">
              <router-link :to="{ name: 'MonthlyPaymentView', params: { monthlyPaymentId: monthlyPaymentsDetail.monthlyPayment.id } }">{{
                monthlyPaymentsDetail.monthlyPayment.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.back</span>
        </button>
        <router-link
          v-if="monthlyPaymentsDetail.id"
          :to="{ name: 'MonthlyPaymentsDetailEdit', params: { monthlyPaymentsDetailId: monthlyPaymentsDetail.id } }"
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

<script lang="ts" src="./monthly-payments-detail-details.component.ts"></script>
