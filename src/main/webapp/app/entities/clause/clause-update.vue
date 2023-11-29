<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="eidcOpendataApp.clause.home.createOrEditLabel" data-cy="ClauseCreateUpdateHeading">Create or edit a Clause</h2>
        <div>
          <div class="form-group" v-if="clause.id">
            <label for="id">Translation missing for global.field.id</label>
            <input type="text" class="form-control" id="id" name="id" v-model="clause.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="clause-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="clause-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="clause-chapter">Chapter</label>
            <select
              class="form-control"
              name="chapter"
              :class="{ valid: !v$.chapter.$invalid, invalid: v$.chapter.$invalid }"
              v-model="v$.chapter.$model"
              id="clause-chapter"
              data-cy="chapter"
            >
              <option v-for="chapter in chapterValues" :key="chapter" v-bind:value="chapter">{{ chapter }}</option>
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
<script lang="ts" src="./clause-update.component.ts"></script>
