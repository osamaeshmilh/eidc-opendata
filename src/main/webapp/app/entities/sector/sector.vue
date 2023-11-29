<template>
  <div>
    <h2 id="page-heading" data-cy="SectorHeading">
      <span id="sector-heading">Sectors</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh list</span>
        </button>
        <router-link :to="{ name: 'SectorCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sector"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>Create a new Sector</span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && sectors && sectors.length === 0">
      <span>No Sectors found</span>
    </div>
    <div class="table-responsive" v-if="sectors && sectors.length > 0">
      <table class="table table-striped" aria-describedby="sectors">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>Translation missing for global.field.id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sectorImage')">
              <span>Sector Image</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sectorImage'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('sectorImageUrl')">
              <span>Sector Image Url</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'sectorImageUrl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('user.id')">
              <span>User</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'user.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="sector in sectors" :key="sector.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SectorView', params: { sectorId: sector.id } }">{{ sector.id }}</router-link>
            </td>
            <td>{{ sector.name }}</td>
            <td>
              <a v-if="sector.sectorImage" v-on:click="openFile(sector.sectorImageContentType, sector.sectorImage)">
                <img
                  v-bind:src="'data:' + sector.sectorImageContentType + ';base64,' + sector.sectorImage"
                  style="max-height: 30px"
                  alt="sector image"
                />
              </a>
              <span v-if="sector.sectorImage">{{ sector.sectorImageContentType }}, {{ byteSize(sector.sectorImage) }}</span>
            </td>
            <td>{{ sector.sectorImageUrl }}</td>
            <td>
              {{ sector.user ? sector.user.id : '' }}
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SectorView', params: { sectorId: sector.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.view</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'SectorEdit', params: { sectorId: sector.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(sector)"
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
        <span id="eidcOpendataApp.sector.delete.question" data-cy="sectorDeleteDialogHeading"
          >Translation missing for entity.delete.title</span
        >
      </template>
      <div class="modal-body">
        <p id="jhi-delete-sector-heading">Are you sure you want to delete Sector {{ removeId }}?</p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Translation missing for entity.action.cancel</button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-sector"
            data-cy="entityConfirmDeleteButton"
            v-on:click="removeSector()"
          >
            Translation missing for entity.action.delete
          </button>
        </div>
      </template>
    </b-modal>
    <div v-show="sectors && sectors.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./sector.component.ts"></script>
