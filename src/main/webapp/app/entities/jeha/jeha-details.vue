<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="jeha">
        <h2 class="jh-entity-heading" data-cy="jehaDetailsHeading"><span>Jeha</span> {{ jeha.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Name</span>
          </dt>
          <dd>
            <span>{{ jeha.name }}</span>
          </dd>
          <dt>
            <span>Website</span>
          </dt>
          <dd>
            <span>{{ jeha.website }}</span>
          </dd>
          <dt>
            <span>Mobile No</span>
          </dt>
          <dd>
            <span>{{ jeha.mobileNo }}</span>
          </dd>
          <dt>
            <span>Facebook Page</span>
          </dt>
          <dd>
            <span>{{ jeha.facebookPage }}</span>
          </dd>
          <dt>
            <span>Email</span>
          </dt>
          <dd>
            <span>{{ jeha.email }}</span>
          </dd>
          <dt>
            <span>Address</span>
          </dt>
          <dd>
            <span>{{ jeha.address }}</span>
          </dd>
          <dt>
            <span>Plus Code</span>
          </dt>
          <dd>
            <span>{{ jeha.plusCode }}</span>
          </dd>
          <dt>
            <span>Center Image</span>
          </dt>
          <dd>
            <div v-if="jeha.centerImage">
              <a v-on:click="openFile(jeha.centerImageContentType, jeha.centerImage)">
                <img
                  v-bind:src="'data:' + jeha.centerImageContentType + ';base64,' + jeha.centerImage"
                  style="max-width: 100%"
                  alt="jeha image"
                />
              </a>
              {{ jeha.centerImageContentType }}, {{ byteSize(jeha.centerImage) }}
            </div>
          </dd>
          <dt>
            <span>Center Image Url</span>
          </dt>
          <dd>
            <span>{{ jeha.centerImageUrl }}</span>
          </dd>
          <dt>
            <span>User</span>
          </dt>
          <dd>
            {{ jeha.user ? jeha.user.id : '' }}
          </dd>
          <dt>
            <span>Sector</span>
          </dt>
          <dd>
            <div v-if="jeha.sector">
              <router-link :to="{ name: 'SectorView', params: { sectorId: jeha.sector.id } }">{{ jeha.sector.name }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.back</span>
        </button>
        <router-link v-if="jeha.id" :to="{ name: 'JehaEdit', params: { jehaId: jeha.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./jeha-details.component.ts"></script>
