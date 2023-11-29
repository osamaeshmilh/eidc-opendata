<template>
  <div>
    <h2 id="page-heading" data-cy="MonthlyRevenueHeading">
      <span id="monthly-revenue-heading">Monthly Revenues</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh list</span>
        </button>
        <router-link :to="{ name: 'MonthlyRevenueCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-monthly-revenue"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>Create a new Monthly Revenue</span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && monthlyRevenues && monthlyRevenues.length === 0">
      <span>No Monthly Revenues found</span>
    </div>
    <div class="table-responsive" v-if="monthlyRevenues && monthlyRevenues.length > 0">
      <table class="table table-striped" aria-describedby="monthlyRevenues">
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
            <th scope="row" v-on:click="changeOrder('revenueCollected')">
              <span>Revenue Collected</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'revenueCollected'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('annualEstablishedBudget')">
              <span>Annual Established Budget</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'annualEstablishedBudget'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('actualRevenueBeginningToEndLastMonth')">
              <span>Actual Revenue Beginning To End Last Month</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'actualRevenueBeginningToEndLastMonth'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('actualRevenueDuringMonth')">
              <span>Actual Revenue During Month</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'actualRevenueDuringMonth'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalRevenueUntilEndMonth')">
              <span>Total Revenue Until End Month</span>
              <jhi-sort-indicator
                :current-order="propOrder"
                :reverse="reverse"
                :field-name="'totalRevenueUntilEndMonth'"
              ></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('existingConnections')">
              <span>Existing Connections</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'existingConnections'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('totalActualRevenue')">
              <span>Total Actual Revenue</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'totalActualRevenue'"></jhi-sort-indicator>
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
          <tr v-for="monthlyRevenue in monthlyRevenues" :key="monthlyRevenue.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'MonthlyRevenueView', params: { monthlyRevenueId: monthlyRevenue.id } }">{{
                monthlyRevenue.id
              }}</router-link>
            </td>
            <td>{{ monthlyRevenue.chapter }}</td>
            <td>{{ monthlyRevenue.month }}</td>
            <td>{{ monthlyRevenue.year }}</td>
            <td>{{ monthlyRevenue.revenueCollected }}</td>
            <td>{{ monthlyRevenue.annualEstablishedBudget }}</td>
            <td>{{ monthlyRevenue.actualRevenueBeginningToEndLastMonth }}</td>
            <td>{{ monthlyRevenue.actualRevenueDuringMonth }}</td>
            <td>{{ monthlyRevenue.totalRevenueUntilEndMonth }}</td>
            <td>{{ monthlyRevenue.existingConnections }}</td>
            <td>{{ monthlyRevenue.totalActualRevenue }}</td>
            <td>{{ monthlyRevenue.balanceOfClause }}</td>
            <td>{{ monthlyRevenue.comments }}</td>
            <td>
              <div v-if="monthlyRevenue.clause">
                <router-link :to="{ name: 'ClauseView', params: { clauseId: monthlyRevenue.clause.id } }">{{
                  monthlyRevenue.clause.name
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="monthlyRevenue.jeha">
                <router-link :to="{ name: 'JehaView', params: { jehaId: monthlyRevenue.jeha.id } }">{{
                  monthlyRevenue.jeha.name
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'MonthlyRevenueView', params: { monthlyRevenueId: monthlyRevenue.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.view</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'MonthlyRevenueEdit', params: { monthlyRevenueId: monthlyRevenue.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(monthlyRevenue)"
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
        <span id="eidcOpendataApp.monthlyRevenue.delete.question" data-cy="monthlyRevenueDeleteDialogHeading"
          >Translation missing for entity.delete.title</span
        >
      </template>
      <div class="modal-body">
        <p id="jhi-delete-monthlyRevenue-heading">Are you sure you want to delete Monthly Revenue {{ removeId }}?</p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Translation missing for entity.action.cancel</button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-monthlyRevenue"
            data-cy="entityConfirmDeleteButton"
            v-on:click="removeMonthlyRevenue()"
          >
            Translation missing for entity.action.delete
          </button>
        </div>
      </template>
    </b-modal>
    <div v-show="monthlyRevenues && monthlyRevenues.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./monthly-revenue.component.ts"></script>
