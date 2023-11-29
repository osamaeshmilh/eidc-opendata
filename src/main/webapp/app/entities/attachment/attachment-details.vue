<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="attachment">
        <h2 class="jh-entity-heading" data-cy="attachmentDetailsHeading"><span>Attachment</span> {{ attachment.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Name</span>
          </dt>
          <dd>
            <span>{{ attachment.name }}</span>
          </dd>
          <dt>
            <span>File</span>
          </dt>
          <dd>
            <div v-if="attachment.file">
              <a v-on:click="openFile(attachment.fileContentType, attachment.file)">Translation missing for entity.action.open</a>
              {{ attachment.fileContentType }}, {{ byteSize(attachment.file) }}
            </div>
          </dd>
          <dt>
            <span>File Url</span>
          </dt>
          <dd>
            <span>{{ attachment.fileUrl }}</span>
          </dd>
          <dt>
            <span>Reference Id</span>
          </dt>
          <dd>
            <span>{{ attachment.referenceId }}</span>
          </dd>
          <dt>
            <span>Reference</span>
          </dt>
          <dd>
            <span>{{ attachment.reference }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.back</span>
        </button>
        <router-link
          v-if="attachment.id"
          :to="{ name: 'AttachmentEdit', params: { attachmentId: attachment.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./attachment-details.component.ts"></script>
