<template>
  <div>
    <h2 id="page-heading" data-cy="MonthlyPaymentHeading">
      <span id="monthly-payment-heading">Monthly Payments</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh list</span>
        </button>
        <router-link :to="{ name: 'MonthlyPaymentCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-monthly-payment"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>Create a new Monthly Payment</span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && monthlyPayments && monthlyPayments.length === 0">
      <span>No Monthly Payments found</span>
    </div>
    <div class="table-responsive" v-if="monthlyPayments && monthlyPayments.length > 0">
      <table class="table table-striped" aria-describedby="monthlyPayments">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>Translation missing for global.field.id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('chapter')">
              <span>Chapter</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'chapter'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('month')">
              <span>Month</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'month'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('year')">
              <span>Year</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'year'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('isTwelve')">
              <span>Is Twelve</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'isTwelve'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('annualEstablishedBudget')">
              <span>Annual Established Budget</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'annualEstablishedBudget'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('actualExpensesBeginningToEndLastMonth')">
              <span>Actual Expenses Beginning To End Last Month</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'actualExpensesBeginningToEndLastMonth'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('actualExpensesDuringMonth')">
              <span>Actual Expenses During Month</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'actualExpensesDuringMonth'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalexpensesUntilEndMonth')">
              <span>Totalexpenses Until End Month</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'totalexpensesUntilEndMonth'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('existingConnections')">
              <span>Existing Connections</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'existingConnections'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalActualExpenses')">
              <span>Total Actual Expenses</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalActualExpenses'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('balanceOfClause')">
              <span>Balance Of Clause</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'balanceOfClause'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('comments')">
              <span>Comments</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'comments'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('clause.name')">
              <span>Clause</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'clause.name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('jeha.name')">
              <span>Jeha</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'jeha.name'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="monthlyPayment in monthlyPayments" :key="monthlyPayment.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MonthlyPaymentView', params: { monthlyPaymentId: monthlyPayment.id } }">{{
                monthlyPayment.id
              }}</router-link>
            </td>
            <td>{{ monthlyPayment.chapter }}</td>
            <td>{{ monthlyPayment.month }}</td>
            <td>{{ monthlyPayment.year }}</td>
            <td>{{ monthlyPayment.isTwelve }}</td>
            <td>{{ monthlyPayment.annualEstablishedBudget }}</td>
            <td>{{ monthlyPayment.actualExpensesBeginningToEndLastMonth }}</td>
            <td>{{ monthlyPayment.actualExpensesDuringMonth }}</td>
            <td>{{ monthlyPayment.totalexpensesUntilEndMonth }}</td>
            <td>{{ monthlyPayment.existingConnections }}</td>
            <td>{{ monthlyPayment.totalActualExpenses }}</td>
            <td>{{ monthlyPayment.balanceOfClause }}</td>
            <td>{{ monthlyPayment.comments }}</td>
            <td>
              <div v-if="monthlyPayment.clause">
                <router-link :to="{ name: 'ClauseView', params: { clauseId: monthlyPayment.clause.id } }">{{
                  monthlyPayment.clause.name
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="monthlyPayment.jeha">
                <router-link :to="{ name: 'JehaView', params: { jehaId: monthlyPayment.jeha.id } }">{{
                  monthlyPayment.jeha.name
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'MonthlyPaymentView', params: { monthlyPaymentId: monthlyPayment.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.view</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'MonthlyPaymentEdit', params: { monthlyPaymentId: monthlyPayment.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(monthlyPayment)"
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
        <span id="eidcOpendataApp.monthlyPayment.delete.question" data-cy="monthlyPaymentDeleteDialogHeading"
          >Translation missing for entity.delete.title</span
        >
      </template>
      <div class="modal-body">
        <p id="jhi-delete-monthlyPayment-heading">Are you sure you want to delete Monthly Payment {{ removeId }}?</p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Translation missing for entity.action.cancel</button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-monthlyPayment"
            data-cy="entityConfirmDeleteButton"
            v-on:click="removeMonthlyPayment()"
          >
            Translation missing for entity.action.delete
          </button>
        </div>
      </template>
    </b-modal>
    <div v-show="monthlyPayments && monthlyPayments.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./monthly-payment.component.ts"></script>
