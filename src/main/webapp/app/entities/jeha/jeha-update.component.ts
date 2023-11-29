import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import JehaService from './jeha.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';
import SectorService from '@/entities/sector/sector.service';
import { type ISector } from '@/shared/model/sector.model';
import { type IJeha, Jeha } from '@/shared/model/jeha.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'JehaUpdate',
  setup() {
    const jehaService = inject('jehaService', () => new JehaService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const jeha: Ref<IJeha> = ref(new Jeha());
    const userService = inject('userService', () => new UserService());
    const users: Ref<Array<any>> = ref([]);

    const sectorService = inject('sectorService', () => new SectorService());

    const sectors: Ref<ISector[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveJeha = async jehaId => {
      try {
        const res = await jehaService().find(jehaId);
        jeha.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.jehaId) {
      retrieveJeha(route.params.jehaId);
    }

    const initRelationships = () => {
      userService()
        .retrieve()
        .then(res => {
          users.value = res.data;
        });
      sectorService()
        .retrieve()
        .then(res => {
          sectors.value = res.data;
        });
    };

    initRelationships();

    const dataUtils = useDataUtils();

    const validations = useValidation();
    const validationRules = {
      name: {},
      website: {},
      mobileNo: {},
      facebookPage: {},
      email: {},
      address: {},
      plusCode: {},
      centerImage: {},
      centerImageUrl: {},
      user: {},
      sector: {},
    };
    const v$ = useVuelidate(validationRules, jeha as any);
    v$.value.$validate();

    return {
      jehaService,
      alertService,
      jeha,
      previousState,
      isSaving,
      currentLanguage,
      users,
      sectors,
      ...dataUtils,
      v$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.jeha.id) {
        this.jehaService()
          .update(this.jeha)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo('A Jeha is updated with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.jehaService()
          .create(this.jeha)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess('A Jeha is created with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    clearInputImage(field, fieldContentType, idInput): void {
      if (this.jeha && field && fieldContentType) {
        if (Object.prototype.hasOwnProperty.call(this.jeha, field)) {
          this.jeha[field] = null;
        }
        if (Object.prototype.hasOwnProperty.call(this.jeha, fieldContentType)) {
          this.jeha[fieldContentType] = null;
        }
        if (idInput) {
          (<any>this).$refs[idInput] = null;
        }
      }
    },
  },
});
