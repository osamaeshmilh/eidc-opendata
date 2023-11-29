<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="eidcOpendataApp.monthlyPayment.home.createOrEditLabel" data-cy="MonthlyPaymentCreateUpdateHeading">
          Create or edit a Monthly Payment
        </h2>
        <div>
          <div class="form-group" v-if="monthlyPayment.id">
            <label for="id">Translation missing for global.field.id</label>
            <input type="text" class="form-control" id="id" name="id" v-model="monthlyPayment.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-chapter">Chapter</label>
            <select
              class="form-control"
              name="chapter"
              :class="{ valid: !v$.chapter.$invalid, invalid: v$.chapter.$invalid }"
              v-model="v$.chapter.$model"
              id="monthly-payment-chapter"
              data-cy="chapter"
            >
              <option v-for="chapter in chapterValues" :key="chapter" v-bind:value="chapter">{{ chapter }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-month">Month</label>
            <input
              type="number"
              class="form-control"
              name="month"
              id="monthly-payment-month"
              data-cy="month"
              :class="{ valid: !v$.month.$invalid, invalid: v$.month.$invalid }"
              v-model.number="v$.month.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-year">Year</label>
            <input
              type="number"
              class="form-control"
              name="year"
              id="monthly-payment-year"
              data-cy="year"
              :class="{ valid: !v$.year.$invalid, invalid: v$.year.$invalid }"
              v-model.number="v$.year.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-isTwelve">Is Twelve</label>
            <input
              type="checkbox"
              class="form-check"
              name="isTwelve"
              id="monthly-payment-isTwelve"
              data-cy="isTwelve"
              :class="{ valid: !v$.isTwelve.$invalid, invalid: v$.isTwelve.$invalid }"
              v-model="v$.isTwelve.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-annualEstablishedBudget">Annual Established Budget</label>
            <input
              type="number"
              class="form-control"
              name="annualEstablishedBudget"
              id="monthly-payment-annualEstablishedBudget"
              data-cy="annualEstablishedBudget"
              :class="{ valid: !v$.annualEstablishedBudget.$invalid, invalid: v$.annualEstablishedBudget.$invalid }"
              v-model.number="v$.annualEstablishedBudget.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-actualExpensesBeginningToEndLastMonth"
              >Actual Expenses Beginning To End Last Month</label
            >
            <input
              type="number"
              class="form-control"
              name="actualExpensesBeginningToEndLastMonth"
              id="monthly-payment-actualExpensesBeginningToEndLastMonth"
              data-cy="actualExpensesBeginningToEndLastMonth"
              :class="{
                valid: !v$.actualExpensesBeginningToEndLastMonth.$invalid,
                invalid: v$.actualExpensesBeginningToEndLastMonth.$invalid,
              }"
              v-model.number="v$.actualExpensesBeginningToEndLastMonth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-actualExpensesDuringMonth">Actual Expenses During Month</label>
            <input
              type="number"
              class="form-control"
              name="actualExpensesDuringMonth"
              id="monthly-payment-actualExpensesDuringMonth"
              data-cy="actualExpensesDuringMonth"
              :class="{ valid: !v$.actualExpensesDuringMonth.$invalid, invalid: v$.actualExpensesDuringMonth.$invalid }"
              v-model.number="v$.actualExpensesDuringMonth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-totalexpensesUntilEndMonth">Totalexpenses Until End Month</label>
            <input
              type="number"
              class="form-control"
              name="totalexpensesUntilEndMonth"
              id="monthly-payment-totalexpensesUntilEndMonth"
              data-cy="totalexpensesUntilEndMonth"
              :class="{ valid: !v$.totalexpensesUntilEndMonth.$invalid, invalid: v$.totalexpensesUntilEndMonth.$invalid }"
              v-model.number="v$.totalexpensesUntilEndMonth.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-existingConnections">Existing Connections</label>
            <input
              type="text"
              class="form-control"
              name="existingConnections"
              id="monthly-payment-existingConnections"
              data-cy="existingConnections"
              :class="{ valid: !v$.existingConnections.$invalid, invalid: v$.existingConnections.$invalid }"
              v-model="v$.existingConnections.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-totalActualExpenses">Total Actual Expenses</label>
            <input
              type="number"
              class="form-control"
              name="totalActualExpenses"
              id="monthly-payment-totalActualExpenses"
              data-cy="totalActualExpenses"
              :class="{ valid: !v$.totalActualExpenses.$invalid, invalid: v$.totalActualExpenses.$invalid }"
              v-model.number="v$.totalActualExpenses.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-balanceOfClause">Balance Of Clause</label>
            <input
              type="number"
              class="form-control"
              name="balanceOfClause"
              id="monthly-payment-balanceOfClause"
              data-cy="balanceOfClause"
              :class="{ valid: !v$.balanceOfClause.$invalid, invalid: v$.balanceOfClause.$invalid }"
              v-model.number="v$.balanceOfClause.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-comments">Comments</label>
            <input
              type="text"
              class="form-control"
              name="comments"
              id="monthly-payment-comments"
              data-cy="comments"
              :class="{ valid: !v$.comments.$invalid, invalid: v$.comments.$invalid }"
              v-model="v$.comments.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-clause">Clause</label>
            <select class="form-control" id="monthly-payment-clause" data-cy="clause" name="clause" v-model="monthlyPayment.clause">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="monthlyPayment.clause && clauseOption.id === monthlyPayment.clause.id ? monthlyPayment.clause : clauseOption"
                v-for="clauseOption in clauses"
                :key="clauseOption.id"
              >
                {{ clauseOption.name }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="monthly-payment-jeha">Jeha</label>
            <select class="form-control" id="monthly-payment-jeha" data-cy="jeha" name="jeha" v-model="monthlyPayment.jeha">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="monthlyPayment.jeha && jehaOption.id === monthlyPayment.jeha.id ? monthlyPayment.jeha : jehaOption"
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
<script lang="ts" src="./monthly-payment-update.component.ts"></script>
