<template>
  <div>
    <h2 id="page-heading" data-cy="ClauseHeading">
      <span id="clause-heading">Clauses</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh list</span>
        </button>
        <router-link :to="{ name: 'ClauseCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-clause"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>Create a new Clause</span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && clauses && clauses.length === 0">
      <span>No Clauses found</span>
    </div>
    <div class="table-responsive" v-if="clauses && clauses.length > 0">
      <table class="table table-striped" aria-describedby="clauses">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>Translation missing for global.field.id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('chapter')">
              <span>Chapter</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'chapter'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="clause in clauses" :key="clause.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ClauseView', params: { clauseId: clause.id } }">{{ clause.id }}</router-link>
            </td>
            <td>{{ clause.name }}</td>
            <td>{{ clause.chapter }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ClauseView', params: { clauseId: clause.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.view</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ClauseEdit', params: { clauseId: clause.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(clause)"
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
        <span id="eidcOpendataApp.clause.delete.question" data-cy="clauseDeleteDialogHeading"
          >Translation missing for entity.delete.title</span
        >
      </template>
      <div class="modal-body">
        <p id="jhi-delete-clause-heading">Are you sure you want to delete Clause {{ removeId }}?</p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Translation missing for entity.action.cancel</button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-clause"
            data-cy="entityConfirmDeleteButton"
            v-on:click="removeClause()"
          >
            Translation missing for entity.action.delete
          </button>
        </div>
      </template>
    </b-modal>
    <div v-show="clauses && clauses.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./clause.component.ts"></script>
