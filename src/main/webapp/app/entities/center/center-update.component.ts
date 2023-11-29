import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import CenterService from './center.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import SectorService from '@/entities/sector/sector.service';
import { type ISector } from '@/shared/model/sector.model';
import UserService from '@/entities/user/user.service';
import { type ICenter, Center } from '@/shared/model/center.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CenterUpdate',
  setup() {
    const centerService = inject('centerService', () => new CenterService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const center: Ref<ICenter> = ref(new Center());

    const sectorService = inject('sectorService', () => new SectorService());

    const sectors: Ref<ISector[]> = ref([]);
    const userService = inject('userService', () => new UserService());
    const users: Ref<Array<any>> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveCenter = async centerId => {
      try {
        const res = await centerService().find(centerId);
        center.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.centerId) {
      retrieveCenter(route.params.centerId);
    }

    const initRelationships = () => {
      sectorService()
        .retrieve()
        .then(res => {
          sectors.value = res.data;
        });
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
      website: {},
      mobileNo: {},
      facebookPage: {},
      email: {},
      address: {},
      plusCode: {},
      centerImage: {},
      centerImageUrl: {},
      sector: {},
      user: {},
    };
    const v$ = useVuelidate(validationRules, center as any);
    v$.value.$validate();

    return {
      centerService,
      alertService,
      center,
      previousState,
      isSaving,
      currentLanguage,
      sectors,
      users,
      ...dataUtils,
      v$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.center.id) {
        this.centerService()
          .update(this.center)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo('A Center is updated with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.centerService()
          .create(this.center)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess('A Center is created with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },

    clearInputImage(field, fieldContentType, idInput): void {
      if (this.center && field && fieldContentType) {
        if (Object.prototype.hasOwnProperty.call(this.center, field)) {
          this.center[field] = null;
        }
        if (Object.prototype.hasOwnProperty.call(this.center, fieldContentType)) {
          this.center[fieldContentType] = null;
        }
        if (idInput) {
          (<any>this).$refs[idInput] = null;
        }
      }
    },
  },
});
