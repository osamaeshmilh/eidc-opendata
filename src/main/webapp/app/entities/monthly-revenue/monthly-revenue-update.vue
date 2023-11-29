<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="eidcOpendataApp.monthlyRevenue.home.createOrEditLabel" data-cy="MonthlyRevenueCreateUpdateHeading">
          Create or edit a Monthly Revenue
        </h2>
        <div>
          <div class="form-group" v-if="monthlyRevenue.id">
            <label for="id">Translation missing for global.field.id</label>
            <input type="text" class="form-control" id="id" name="id" v-model="monthlyRevenue.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-chapter">Chapter</label>
            <select
              class="form-control"
              name="chapter"
              :class="{ valid: !v$.chapter.$invalid, invalid: v$.chapter.$invalid }"
              v-model="v$.chapter.$model"
              id="monthly-revenue-chapter"
              data-cy="chapter"
            >
              <option v-for="chapter in chapterValues" :key="chapter" v-bind:value="chapter">{{ chapter }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-month">Month</label>
            <input
              type="number"
              class="form-control"
              name="month"
              id="monthly-revenue-month"
              data-cy="month"
              :class="{ valid: !v$.month.$invalid, invalid: v$.month.$invalid }"
              v-model.number="v$.month.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-year">Year</label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="monthly-revenue-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-revenueCollected">Revenue Collected</label>
            <input
              type="number"
              class="form-control"
              name="revenueCollected"
              id="monthly-revenue-revenueCollected"
              data-cy="revenueCollected"
              :class="{ valid: !v$.revenueCollected.$invalid, invalid: v$.revenueCollected.$invalid }"
              v-model.number="v$.revenueCollected.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-annualEstablishedBudget">Annual Established Budget</label>
            <input
              type="number"
              class="form-control"
              name="annualEstablishedBudget"
              id="monthly-revenue-annualEstablishedBudget"
              data-cy="annualEstablishedBudget"
              :class="{ valid: !v$.annualEstablishedBudget.$invalid, invalid: v$.annualEstablishedBudget.$invalid }"
              v-model.number="v$.annualEstablishedBudget.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-actualRevenueBeginningToEndLastMonth"
              >Actual Revenue Beginning To End Last Month</label
            >
            <input
              type="number"
              class="form-control"
              name="actualRevenueBeginningToEndLastMonth"
              id="monthly-revenue-actualRevenueBeginningToEndLastMonth"
              data-cy="actualRevenueBeginningToEndLastMonth"
              :class="{
                valid: !v$.actualRevenueBeginningToEndLastMonth.$invalid,
                invalid: v$.actualRevenueBeginningToEndLastMonth.$invalid,
              }"
              v-model.number="v$.actualRevenueBeginningToEndLastMonth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-actualRevenueDuringMonth">Actual Revenue During Month</label>
            <input
              type="number"
              class="form-control"
              name="actualRevenueDuringMonth"
              id="monthly-revenue-actualRevenueDuringMonth"
              data-cy="actualRevenueDuringMonth"
              :class="{ valid: !v$.actualRevenueDuringMonth.$invalid, invalid: v$.actualRevenueDuringMonth.$invalid }"
              v-model.number="v$.actualRevenueDuringMonth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-totalRevenueUntilEndMonth">Total Revenue Until End Month</label>
            <input
              type="number"
              class="form-control"
              name="totalRevenueUntilEndMonth"
              id="monthly-revenue-totalRevenueUntilEndMonth"
              data-cy="totalRevenueUntilEndMonth"
              :class="{ valid: !v$.totalRevenueUntilEndMonth.$invalid, invalid: v$.totalRevenueUntilEndMonth.$invalid }"
              v-model.number="v$.totalRevenueUntilEndMonth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-existingConnections">Existing Connections</label>
            <input
              type="text"
              class="form-control"
              name="existingConnections"
              id="monthly-revenue-existingConnections"
              data-cy="existingConnections"
              :class="{ valid: !v$.existingConnections.$invalid, invalid: v$.existingConnections.$invalid }"
              v-model="v$.existingConnections.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-totalActualRevenue">Total Actual Revenue</label>
            <input
              type="number"
              class="form-control"
              name="totalActualRevenue"
              id="monthly-revenue-totalActualRevenue"
              data-cy="totalActualRevenue"
              :class="{ valid: !v$.totalActualRevenue.$invalid, invalid: v$.totalActualRevenue.$invalid }"
              v-model.number="v$.totalActualRevenue.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-balanceOfClause">Balance Of Clause</label>
            <input
              type="number"
              class="form-control"
              name="balanceOfClause"
              id="monthly-revenue-balanceOfClause"
              data-cy="balanceOfClause"
              :class="{ valid: !v$.balanceOfClause.$invalid, invalid: v$.balanceOfClause.$invalid }"
              v-model.number="v$.balanceOfClause.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-comments">Comments</label>
            <input
              type="text"
              class="form-control"
              name="comments"
              id="monthly-revenue-comments"
              data-cy="comments"
              :class="{ valid: !v$.comments.$invalid, invalid: v$.comments.$invalid }"
              v-model="v$.comments.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-clause">Clause</label>
            <select class="form-control" id="monthly-revenue-clause" data-cy="clause" name="clause" v-model="monthlyRevenue.clause">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="monthlyRevenue.clause && clauseOption.id === monthlyRevenue.clause.id ? monthlyRevenue.clause : clauseOption"
                v-for="clauseOption in clauses"
                :key="clauseOption.id"
              >
                {{ clauseOption.name }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-revenue-jeha">Jeha</label>
            <select class="form-control" id="monthly-revenue-jeha" data-cy="jeha" name="jeha" v-model="monthlyRevenue.jeha">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="monthlyRevenue.jeha && jehaOption.id === monthlyRevenue.jeha.id ? monthlyRevenue.jeha : jehaOption"
                v-for="jehaOption in jehas"
                :key="jehaOption.id"
              >
                {{ jehaOption.name }}
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
<script lang="ts" src="./monthly-revenue-update.component.ts"></script>
