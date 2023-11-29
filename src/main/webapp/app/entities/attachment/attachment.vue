<template>
  <div>
    <h2 id="page-heading" data-cy="AttachmentHeading">
      <span id="attachment-heading">Attachments</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh list</span>
        </button>
        <router-link :to="{ name: 'AttachmentCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-attachment"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span>Create a new Attachment</span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && attachments && attachments.length === 0">
      <span>No Attachments found</span>
    </div>
    <div class="table-responsive" v-if="attachments && attachments.length > 0">
      <table class="table table-striped" aria-describedby="attachments">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span>Translation missing for global.field.id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('name')">
              <span>Name</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'name'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('file')">
              <span>File</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'file'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fileUrl')">
              <span>File Url</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fileUrl'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('referenceId')">
              <span>Reference Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'referenceId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reference')">
              <span>Reference</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reference'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="attachment in attachments" :key="attachment.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AttachmentView', params: { attachmentId: attachment.id } }">{{ attachment.id }}</router-link>
            </td>
            <td>{{ attachment.name }}</td>
            <td>
              <a v-if="attachment.file" v-on:click="openFile(attachment.fileContentType, attachment.file)"
                >Translation missing for entity.action.open</a
              >
              <span v-if="attachment.file">{{ attachment.fileContentType }}, {{ byteSize(attachment.file) }}</span>
            </td>
            <td>{{ attachment.fileUrl }}</td>
            <td>{{ attachment.referenceId }}</td>
            <td>{{ attachment.reference }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'AttachmentView', params: { attachmentId: attachment.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.view</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'AttachmentEdit', params: { attachmentId: attachment.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Translation missing for entity.action.edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(attachment)"
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
        <span id="eidcOpendataApp.attachment.delete.question" data-cy="attachmentDeleteDialogHeading"
          >Translation missing for entity.delete.title</span
        >
      </template>
      <div class="modal-body">
        <p id="jhi-delete-attachment-heading">Are you sure you want to delete Attachment {{ removeId }}?</p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Translation missing for entity.action.cancel</button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-attachment"
            data-cy="entityConfirmDeleteButton"
            v-on:click="removeAttachment()"
          >
            Translation missing for entity.action.delete
          </button>
        </div>
      </template>
    </b-modal>
    <div v-show="attachments && attachments.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./attachment.component.ts"></script>
