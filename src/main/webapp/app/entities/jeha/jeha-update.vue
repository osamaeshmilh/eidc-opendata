<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="eidcOpendataApp.jeha.home.createOrEditLabel" data-cy="JehaCreateUpdateHeading">Create or edit a Jeha</h2>
        <div>
          <div class="form-group" v-if="jeha.id">
            <label for="id">Translation missing for global.field.id</label>
            <input type="text" class="form-control" id="id" name="id" v-model="jeha.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="jeha-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-website">Website</label>
            <input
              type="text"
              class="form-control"
              name="website"
              id="jeha-website"
              data-cy="website"
              :class="{ valid: !v$.website.$invalid, invalid: v$.website.$invalid }"
              v-model="v$.website.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-mobileNo">Mobile No</label>
            <input
              type="text"
              class="form-control"
              name="mobileNo"
              id="jeha-mobileNo"
              data-cy="mobileNo"
              :class="{ valid: !v$.mobileNo.$invalid, invalid: v$.mobileNo.$invalid }"
              v-model="v$.mobileNo.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-facebookPage">Facebook Page</label>
            <input
              type="text"
              class="form-control"
              name="facebookPage"
              id="jeha-facebookPage"
              data-cy="facebookPage"
              :class="{ valid: !v$.facebookPage.$invalid, invalid: v$.facebookPage.$invalid }"
              v-model="v$.facebookPage.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="jeha-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-address">Address</label>
            <input
              type="text"
              class="form-control"
              name="address"
              id="jeha-address"
              data-cy="address"
              :class="{ valid: !v$.address.$invalid, invalid: v$.address.$invalid }"
              v-model="v$.address.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-plusCode">Plus Code</label>
            <input
              type="text"
              class="form-control"
              name="plusCode"
              id="jeha-plusCode"
              data-cy="plusCode"
              :class="{ valid: !v$.plusCode.$invalid, invalid: v$.plusCode.$invalid }"
              v-model="v$.plusCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-centerImage">Center Image</label>
            <div>
              <img
                v-bind:src="'data:' + jeha.centerImageContentType + ';base64,' + jeha.centerImage"
                style="max-height: 100px"
                v-if="jeha.centerImage"
                alt="jeha image"
              />
              <div v-if="jeha.centerImage" class="form-text text-danger clearfix">
                <span class="pull-left">{{ jeha.centerImageContentType }}, {{ byteSize(jeha.centerImage) }}</span>
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
                v-on:change="setFileData($event, jeha, 'centerImage', true)"
                accept="image/*"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="centerImage"
              id="jeha-centerImage"
              data-cy="centerImage"
              :class="{ valid: !v$.centerImage.$invalid, invalid: v$.centerImage.$invalid }"
              v-model="v$.centerImage.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="centerImageContentType"
              id="jeha-centerImageContentType"
              v-model="jeha.centerImageContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-centerImageUrl">Center Image Url</label>
            <input
              type="text"
              class="form-control"
              name="centerImageUrl"
              id="jeha-centerImageUrl"
              data-cy="centerImageUrl"
              :class="{ valid: !v$.centerImageUrl.$invalid, invalid: v$.centerImageUrl.$invalid }"
              v-model="v$.centerImageUrl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-user">User</label>
            <select class="form-control" id="jeha-user" data-cy="user" name="user" v-model="jeha.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="jeha.user && userOption.id === jeha.user.id ? jeha.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="jeha-sector">Sector</label>
            <select class="form-control" id="jeha-sector" data-cy="sector" name="sector" v-model="jeha.sector">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="jeha.sector && sectorOption.id === jeha.sector.id ? jeha.sector : sectorOption"
                v-for="sectorOption in sectors"
                :key="sectorOption.id"
              >
                {{ sectorOption.name }}
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
<script lang="ts" src="./jeha-update.component.ts"></script>
