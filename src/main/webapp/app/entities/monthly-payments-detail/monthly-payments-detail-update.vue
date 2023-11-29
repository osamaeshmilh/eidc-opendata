<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="eidcOpendataApp.monthlyPaymentsDetail.home.createOrEditLabel" data-cy="MonthlyPaymentsDetailCreateUpdateHeading">
          Create or edit a Monthly Payments Detail
        </h2>
        <div>
          <div class="form-group" v-if="monthlyPaymentsDetail.id">
            <label for="id">Translation missing for global.field.id</label>
            <input type="text" class="form-control" id="id" name="id" v-model="monthlyPaymentsDetail.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payments-detail-beneficiary">Beneficiary</label>
            <input
              type="text"
              class="form-control"
              name="beneficiary"
              id="monthly-payments-detail-beneficiary"
              data-cy="beneficiary"
              :class="{ valid: !v$.beneficiary.$invalid, invalid: v$.beneficiary.$invalid }"
              v-model="v$.beneficiary.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payments-detail-payment">Payment</label>
            <input
              type="number"
              class="form-control"
              name="payment"
              id="monthly-payments-detail-payment"
              data-cy="payment"
              :class="{ valid: !v$.payment.$invalid, invalid: v$.payment.$invalid }"
              v-model.number="v$.payment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payments-detail-dateOfPayment">Date Of Payment</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="monthly-payments-detail-dateOfPayment"
                  v-model="v$.dateOfPayment.$model"
                  name="dateOfPayment"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="monthly-payments-detail-dateOfPayment"
                data-cy="dateOfPayment"
                type="text"
                class="form-control"
                name="dateOfPayment"
                :class="{ valid: !v$.dateOfPayment.$invalid, invalid: v$.dateOfPayment.$invalid }"
                v-model="v$.dateOfPayment.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payments-detail-description">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="monthly-payments-detail-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payments-detail-notes">Notes</label>
            <input
              type="text"
              class="form-control"
              name="notes"
              id="monthly-payments-detail-notes"
              data-cy="notes"
              :class="{ valid: !v$.notes.$invalid, invalid: v$.notes.$invalid }"
              v-model="v$.notes.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payments-detail-monthlyPayment">Monthly Payment</label>
            <select
              class="form-control"
              id="monthly-payments-detail-monthlyPayment"
              data-cy="monthlyPayment"
              name="monthlyPayment"
              v-model="monthlyPaymentsDetail.monthlyPayment"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  monthlyPaymentsDetail.monthlyPayment && monthlyPaymentOption.id === monthlyPaymentsDetail.monthlyPayment.id
                    ? monthlyPaymentsDetail.monthlyPayment
                    : monthlyPaymentOption
                "
                v-for="monthlyPaymentOption in monthlyPayments"
                :key="monthlyPaymentOption.id"
              >
                {{ monthlyPaymentOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./monthly-payments-detail-update.component.ts"></script>
