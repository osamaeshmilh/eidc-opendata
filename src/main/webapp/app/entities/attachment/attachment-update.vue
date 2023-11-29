<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="eidcOpendataApp.attachment.home.createOrEditLabel" data-cy="AttachmentCreateUpdateHeading">Create or edit a Attachment</h2>
        <div>
          <div class="form-group" v-if="attachment.id">
            <label for="id">Translation missing for global.field.id</label>
            <input type="text" class="form-control" id="id" name="id" v-model="attachment.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="attachment-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="attachment-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="attachment-file">File</label>
            <div>
              <div v-if="attachment.file" class="form-text text-danger clearfix">
                <a class="pull-left" v-on:click="openFile(attachment.fileContentType, attachment.file)"
                  >Translation missing for entity.action.open</a
                ><br />
                <span class="pull-left">{{ attachment.fileContentType }}, {{ byteSize(attachment.file) }}</span>
                <button
                  type="button"
                  v-on:click="
                    attachment.file = null;
                    attachment.fileContentType = null;
                  "
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <label for="file_file" class="btn btn-primary pull-right">Translation missing for entity.action.addblob</label>
              <input
                type="file"
                ref="file_file"
                id="file_file"
                style="display: none"
                data-cy="file"
                v-on:change="setFileData($event, attachment, 'file', false)"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="file"
              id="attachment-file"
              data-cy="file"
              :class="{ valid: !v$.file.$invalid, invalid: v$.file.$invalid }"
              v-model="v$.file.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="fileContentType"
              id="attachment-fileContentType"
              v-model="attachment.fileContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="attachment-fileUrl">File Url</label>
            <input
              type="text"
              class="form-control"
              name="fileUrl"
              id="attachment-fileUrl"
              data-cy="fileUrl"
              :class="{ valid: !v$.fileUrl.$invalid, invalid: v$.fileUrl.$invalid }"
              v-model="v$.fileUrl.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="attachment-referenceId">Reference Id</label>
            <input
              type="number"
              class="form-control"
              name="referenceId"
              id="attachment-referenceId"
              data-cy="referenceId"
              :class="{ valid: !v$.referenceId.$invalid, invalid: v$.referenceId.$invalid }"
              v-model.number="v$.referenceId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="attachment-reference">Reference</label>
            <select
              class="form-control"
              name="reference"
              :class="{ valid: !v$.reference.$invalid, invalid: v$.reference.$invalid }"
              v-model="v$.reference.$model"
              id="attachment-reference"
              data-cy="reference"
            >
              <option v-for="reference in referenceValues" :key="reference" v-bind:value="reference">{{ reference }}</option>
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
<script lang="ts" src="./attachment-update.component.ts"></script>
