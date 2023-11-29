import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import SectorService from './sector.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';
import { type ISector, Sector } from '@/shared/model/sector.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SectorUpdate',
  setup() {
    const sectorService = inject('sectorService', () => new SectorService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const sector: Ref<ISector> = ref(new Sector());
    const userService = inject('userService', () => new UserService());
    const users: Ref<Array<any>> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveSector = async sectorId => {
      try {
        const res = await sectorService().find(sectorId);
        sector.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.sectorId) {
      retrieveSector(route.params.sectorId);
    }

    const initRelationships = () => {
      userService()
        .retrieve()
        .then(res => {
          users.value = res.data;
        });
    };

    initRelationships();

    const dataUtils = useDataUtils();

    const validations = useValidation();
    const validationRules = {
      name: {},
      sectorImage: {},
      sectorImageUrl: {},
      user: {},
      center: {},
    };
    const v$ = useVuelidate(validationRules, sector as any);
    v$.value.$validate();

    return {
      sectorService,
      alertService,
      sector,
      previousState,
      isSaving,
      currentLanguage,
      users,
      ...dataUtils,
      v$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.sector.id) {
        this.sectorService()
          .update(this.sector)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo('A Sector is updated with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.sectorService()
          .create(this.sector)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess('A Sector is created with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    clearInputImage(field, fieldContentType, idInput): void {
      if (this.sector && field && fieldContentType) {
        if (Object.prototype.hasOwnProperty.call(this.sector, field)) {
          this.sector[field] = null;
        }
        if (Object.prototype.hasOwnProperty.call(this.sector, fieldContentType)) {
          this.sector[fieldContentType] = null;
        }
        if (idInput) {
          (<any>this).$refs[idInput] = null;
        }
      }
    },
  },
});
