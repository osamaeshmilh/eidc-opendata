<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="sector">
        <h2 class="jh-entity-heading" data-cy="sectorDetailsHeading"><span>Sector</span> {{ sector.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Name</span>
          </dt>
          <dd>
            <span>{{ sector.name }}</span>
          </dd>
          <dt>
            <span>Sector Image</span>
          </dt>
          <dd>
            <div v-if="sector.sectorImage">
              <a v-on:click="openFile(sector.sectorImageContentType, sector.sectorImage)">
                <img
                  v-bind:src="'data:' + sector.sectorImageContentType + ';base64,' + sector.sectorImage"
                  style="max-width: 100%"
                  alt="sector image"
                />
              </a>
              {{ sector.sectorImageContentType }}, {{ byteSize(sector.sectorImage) }}
            </div>
          </dd>
          <dt>
            <span>Sector Image Url</span>
          </dt>
          <dd>
            <span>{{ sector.sectorImageUrl }}</span>
          </dd>
          <dt>
            <span>User</span>
          </dt>
          <dd>
            {{ sector.user ? sector.user.id : '' }}
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.back</span>
        </button>
        <router-link v-if="sector.id" :to="{ name: 'SectorEdit', params: { sectorId: sector.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./sector-details.component.ts"></script>
