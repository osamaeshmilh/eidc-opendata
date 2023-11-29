<template>
  <div>
    <h2 id="page-heading" data-cy="MonthlyPaymentsDetailHeading">
      <span id="monthly-payments-detail-heading">Monthly Payments Details</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh list</span>
        </button>
        <router-link :to="{ name: 'MonthlyPaymentsDetailCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-monthly-payments-detail"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>Create a new Monthly Payments Detail</span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && monthlyPaymentsDetails && monthlyPaymentsDetails.length === 0">
      <span>No Monthly Payments Details found</span>
    </div>
    <div class="table-responsive" v-if="monthlyPaymentsDetails && monthlyPaymentsDetails.length > 0">
      <table class="table table-striped" aria-describedby="monthlyPaymentsDetails">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>Translation missing for global.field.id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('beneficiary')">
              <span>Beneficiary</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'beneficiary'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('payment')">
              <span>Payment</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'payment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('dateOfPayment')">
              <span>Date Of Payment</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'dateOfPayment'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span>Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('notes')">
              <span>Notes</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'notes'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('monthlyPayment.id')">
              <span>Monthly Payment</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'monthlyPayment.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="monthlyPaymentsDetail in monthlyPaymentsDetails" :key="monthlyPaymentsDetail.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MonthlyPaymentsDetailView', params: { monthlyPaymentsDetailId: monthlyPaymentsDetail.id } }">{{
                monthlyPaymentsDetail.id
              }}</router-link>
            </td>
            <td>{{ monthlyPaymentsDetail.beneficiary }}</td>
            <td>{{ monthlyPaymentsDetail.payment }}</td>
            <td>{{ monthlyPaymentsDetail.dateOfPayment }}</td>
            <td>{{ monthlyPaymentsDetail.description }}</td>
            <td>{{ monthlyPaymentsDetail.notes }}</td>
            <td>
              <div v-if="monthlyPaymentsDetail.monthlyPayment">
                <router-link :to="{ name: 'MonthlyPaymentView', params: { monthlyPaymentId: monthlyPaymentsDetail.monthlyPayment.id } }">{{
                  monthlyPaymentsDetail.monthlyPayment.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'MonthlyPaymentsDetailView', params: { monthlyPaymentsDetailId: monthlyPaymentsDetail.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.view</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'MonthlyPaymentsDetailEdit', params: { monthlyPaymentsDetailId: monthlyPaymentsDetail.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(monthlyPaymentsDetail)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline">Translation missing for entity.action.delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span id="eidcOpendataApp.monthlyPaymentsDetail.delete.question" data-cy="monthlyPaymentsDetailDeleteDialogHeading"
          >Translation missing for entity.delete.title</span
        >
      </template>
      <div class="modal-body">
        <p id="jhi-delete-monthlyPaymentsDetail-heading">Are you sure you want to delete Monthly Payments Detail {{ removeId }}?</p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Translation missing for entity.action.cancel</button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-monthlyPaymentsDetail"
            data-cy="entityConfirmDeleteButton"
            v-on:click="removeMonthlyPaymentsDetail()"
          >
            Translation missing for entity.action.delete
          </button>
        </div>
      </template>
    </b-modal>
    <div v-show="monthlyPaymentsDetails && monthlyPaymentsDetails.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./monthly-payments-detail.component.ts"></script>
