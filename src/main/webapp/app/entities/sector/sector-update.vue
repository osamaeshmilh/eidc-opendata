<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="eidcOpendataApp.sector.home.createOrEditLabel" data-cy="SectorCreateUpdateHeading">Create or edit a Sector</h2>
        <div>
          <div class="form-group" v-if="sector.id">
            <label for="id">Translation missing for global.field.id</label>
            <input type="text" class="form-control" id="id" name="id" v-model="sector.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="sector-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="sector-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="sector-sectorImage">Sector Image</label>
            <div>
              <img
                v-bind:src="'data:' + sector.sectorImageContentType + ';base64,' + sector.sectorImage"
                style="max-height: 100px"
                v-if="sector.sectorImage"
                alt="sector image"
              />
              <div v-if="sector.sectorImage" class="form-text text-danger clearfix">
                <span class="pull-left">{{ sector.sectorImageContentType }}, {{ byteSize(sector.sectorImage) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('sectorImage', 'sectorImageContentType', 'file_sectorImage')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <label for="file_sectorImage" class="btn btn-primary pull-right">Translation missing for entity.action.addimage</label>
              <input
                type="file"
                ref="file_sectorImage"
                id="file_sectorImage"
                style="display: none"
                data-cy="sectorImage"
                v-on:change="setFileData($event, sector, 'sectorImage', true)"
                accept="image/*"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="sectorImage"
              id="sector-sectorImage"
              data-cy="sectorImage"
              :class="{ valid: !v$.sectorImage.$invalid, invalid: v$.sectorImage.$invalid }"
              v-model="v$.sectorImage.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="sectorImageContentType"
              id="sector-sectorImageContentType"
              v-model="sector.sectorImageContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="sector-sectorImageUrl">Sector Image Url</label>
            <input
              type="text"
              class="form-control"
              name="sectorImageUrl"
              id="sector-sectorImageUrl"
              data-cy="sectorImageUrl"
              :class="{ valid: !v$.sectorImageUrl.$invalid, invalid: v$.sectorImageUrl.$invalid }"
              v-model="v$.sectorImageUrl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="sector-user">User</label>
            <select class="form-control" id="sector-user" data-cy="user" name="user" v-model="sector.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="sector.user && userOption.id === sector.user.id ? sector.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.id }}
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
<script lang="ts" src="./sector-update.component.ts"></script>
