import { defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import JehaService from './jeha.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { type IJeha } from '@/shared/model/jeha.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'JehaDetails',
  setup() {
    const jehaService = inject('jehaService', () => new JehaService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const jeha: Ref<IJeha> = ref({});

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

    return {
      alertService,
      jeha,

      ...dataUtils,

      previousState,
    };
  },
});
