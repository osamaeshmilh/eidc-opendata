<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="clause">
        <h2 class="jh-entity-heading" data-cy="clauseDetailsHeading"><span>Clause</span> {{ clause.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Name</span>
          </dt>
          <dd>
            <span>{{ clause.name }}</span>
          </dd>
          <dt>
            <span>Chapter</span>
          </dt>
          <dd>
            <span>{{ clause.chapter }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.back</span>
        </button>
        <router-link v-if="clause.id" :to="{ name: 'ClauseEdit', params: { clauseId: clause.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span>Translation missing for entity.action.edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./clause-details.component.ts"></script>
