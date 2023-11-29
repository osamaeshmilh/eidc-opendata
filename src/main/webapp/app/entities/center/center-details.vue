<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="center">
        <h2 class="jh-entity-heading" data-cy="centerDetailsHeading"><span>Center</span> {{ center.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Name</span>
          </dt>
          <dd>
            <span>{{ center.name }}</span>
          </dd>
          <dt>
            <span>Website</span>
          </dt>
          <dd>
            <span>{{ center.website }}</span>
          </dd>
          <dt>
            <span>Mobile No</span>
          </dt>
          <dd>
            <span>{{ center.mobileNo }}</span>
          </dd>
          <dt>
            <span>Facebook Page</span>
          </dt>
          <dd>
            <span>{{ center.facebookPage }}</span>
          </dd>
          <dt>
            <span>Email</span>
          </dt>
          <dd>
            <span>{{ center.email }}</span>
          </dd>
          <dt>
            <span>Address</span>
          </dt>
          <dd>
            <span>{{ center.address }}</span>
          </dd>
          <dt>
            <span>Plus Code</span>
          </dt>
          <dd>
            <span>{{ center.plusCode }}</span>
          </dd>
          <dt>
            <span>Center Image</span>
          </dt>
          <dd>
            <div v-if="center.centerImage">
              <a v-on:click="openFile(center.centerImageContentType, center.centerImage)">
                <img
                  v-bind:src="'data:' + center.centerImageContentType + ';base64,' + center.centerImage"
                  style="max-width: 100%"
                  alt="center image"
                />
              </a>
              {{ center.centerImageContentType }}, {{ byteSize(center.centerImage) }}
            </div>
          </dd>
          <dt>
            <span>Center Image Url</span>
          </dt>
          <dd>
            <span>{{ center.centerImageUrl }}</span>
          </dd>
          <dt>
            <span>Sector</span>
          </dt>
          <dd>
            <div v-if="center.sector">
              <router-link :to="{ name: 'SectorView', params: { sectorId: center.sector.id } }">{{ center.sector.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span>User</span>
          </dt>
          <dd>
            {{ center.user ? center.user.id : '' }}
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.back</span>
        </button>
        <router-link v-if="center.id" :to="{ name: 'CenterEdit', params: { centerId: center.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./center-details.component.ts"></script>
