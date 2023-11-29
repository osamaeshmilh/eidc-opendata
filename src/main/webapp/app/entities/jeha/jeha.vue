<template>
  <div>
    <h2 id="page-heading" data-cy="JehaHeading">
      <span id="jeha-heading">Jehas</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh list</span>
        </button>
        <router-link :to="{ name: 'JehaCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-jeha">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>Create a new Jeha</span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && jehas && jehas.length === 0">
      <span>No Jehas found</span>
    </div>
    <div class="table-responsive" v-if="jehas && jehas.length > 0">
      <table class="table table-striped" aria-describedby="jehas">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>Translation missing for global.field.id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('website')">
              <span>Website</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'website'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mobileNo')">
              <span>Mobile No</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mobileNo'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('facebookPage')">
              <span>Facebook Page</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'facebookPage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('email')">
              <span>Email</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'email'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('address')">
              <span>Address</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'address'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('plusCode')">
              <span>Plus Code</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'plusCode'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('centerImage')">
              <span>Center Image</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'centerImage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('centerImageUrl')">
              <span>Center Image Url</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'centerImageUrl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('user.id')">
              <span>User</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sector.name')">
              <span>Sector</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sector.name'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="jeha in jehas" :key="jeha.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'JehaView', params: { jehaId: jeha.id } }">{{ jeha.id }}</router-link>
            </td>
            <td>{{ jeha.name }}</td>
            <td>{{ jeha.website }}</td>
            <td>{{ jeha.mobileNo }}</td>
            <td>{{ jeha.facebookPage }}</td>
            <td>{{ jeha.email }}</td>
            <td>{{ jeha.address }}</td>
            <td>{{ jeha.plusCode }}</td>
            <td>
              <a v-if="jeha.centerImage" v-on:click="openFile(jeha.centerImageContentType, jeha.centerImage)">
                <img
                  v-bind:src="'data:' + jeha.centerImageContentType + ';base64,' + jeha.centerImage"
                  style="max-height: 30px"
                  alt="jeha image"
                />
              </a>
              <span v-if="jeha.centerImage">{{ jeha.centerImageContentType }}, {{ byteSize(jeha.centerImage) }}</span>
            </td>
            <td>{{ jeha.centerImageUrl }}</td>
            <td>
              {{ jeha.user ? jeha.user.id : '' }}
            </td>
            <td>
              <div v-if="jeha.sector">
                <router-link :to="{ name: 'SectorView', params: { sectorId: jeha.sector.id } }">{{ jeha.sector.name }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'JehaView', params: { jehaId: jeha.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.view</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'JehaEdit', params: { jehaId: jeha.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(jeha)"
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
        <span id="eidcOpendataApp.jeha.delete.question" data-cy="jehaDeleteDialogHeading">Translation missing for entity.delete.title</span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-jeha-heading">Are you sure you want to delete Jeha {{ removeId }}?</p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Translation missing for entity.action.cancel</button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-jeha"
            data-cy="entityConfirmDeleteButton"
            v-on:click="removeJeha()"
          >
            Translation missing for entity.action.delete
          </button>
        </div>
      </template>
    </b-modal>
    <div v-show="jehas && jehas.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jeha.component.ts"></script>
