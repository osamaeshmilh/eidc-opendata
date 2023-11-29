<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="eidcOpendataApp.center.home.createOrEditLabel" data-cy="CenterCreateUpdateHeading">Create or edit a Center</h2>
        <div>
          <div class="form-group" v-if="center.id">
            <label for="id">Translation missing for global.field.id</label>
            <input type="text" class="form-control" id="id" name="id" v-model="center.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="center-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-website">Website</label>
            <input
              type="text"
              class="form-control"
              name="website"
              id="center-website"
              data-cy="website"
              :class="{ valid: !v$.website.$invalid, invalid: v$.website.$invalid }"
              v-model="v$.website.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-mobileNo">Mobile No</label>
            <input
              type="text"
              class="form-control"
              name="mobileNo"
              id="center-mobileNo"
              data-cy="mobileNo"
              :class="{ valid: !v$.mobileNo.$invalid, invalid: v$.mobileNo.$invalid }"
              v-model="v$.mobileNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-facebookPage">Facebook Page</label>
            <input
              type="text"
              class="form-control"
              name="facebookPage"
              id="center-facebookPage"
              data-cy="facebookPage"
              :class="{ valid: !v$.facebookPage.$invalid, invalid: v$.facebookPage.$invalid }"
              v-model="v$.facebookPage.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="center-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-address">Address</label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="center-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-plusCode">Plus Code</label>
            <input
              type="text"
              class="form-control"
              name="plusCode"
              id="center-plusCode"
              data-cy="plusCode"
              :class="{ valid: !v$.plusCode.$invalid, invalid: v$.plusCode.$invalid }"
              v-model="v$.plusCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-centerImage">Center Image</label>
            <div>
              <img
                v-bind:src="'data:' + center.centerImageContentType + ';base64,' + center.centerImage"
                style="max-height: 100px"
                v-if="center.centerImage"
                alt="center image"
              />
              <div v-if="center.centerImage" class="form-text text-danger clearfix">
                <span class="pull-left">{{ center.centerImageContentType }}, {{ byteSize(center.centerImage) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('centerImage', 'centerImageContentType', 'file_centerImage')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <label for="file_centerImage" class="btn btn-primary pull-right">Translation missing for entity.action.addimage</label>
              <input
                type="file"
                ref="file_centerImage"
                id="file_centerImage"
                style="display: none"
                data-cy="centerImage"
                v-on:change="setFileData($event, center, 'centerImage', true)"
                accept="image/*"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="centerImage"
              id="center-centerImage"
              data-cy="centerImage"
              :class="{ valid: !v$.centerImage.$invalid, invalid: v$.centerImage.$invalid }"
              v-model="v$.centerImage.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="centerImageContentType"
              id="center-centerImageContentType"
              v-model="center.centerImageContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-centerImageUrl">Center Image Url</label>
            <input
              type="text"
              class="form-control"
              name="centerImageUrl"
              id="center-centerImageUrl"
              data-cy="centerImageUrl"
              :class="{ valid: !v$.centerImageUrl.$invalid, invalid: v$.centerImageUrl.$invalid }"
              v-model="v$.centerImageUrl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-sector">Sector</label>
            <select class="form-control" id="center-sector" data-cy="sector" name="sector" v-model="center.sector">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="center.sector && sectorOption.id === center.sector.id ? center.sector : sectorOption"
                v-for="sectorOption in sectors"
                :key="sectorOption.id"
              >
                {{ sectorOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="center-user">User</label>
            <select class="form-control" id="center-user" data-cy="user" name="user" v-model="center.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="center.user && userOption.id === center.user.id ? center.user : userOption"
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
<script lang="ts" src="./center-update.component.ts"></script>
