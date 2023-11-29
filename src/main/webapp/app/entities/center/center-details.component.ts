import { defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import CenterService from './center.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { type ICenter } from '@/shared/model/center.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'CenterDetails',
  setup() {
    const centerService = inject('centerService', () => new CenterService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const center: Ref<ICenter> = ref({});

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

    return {
      alertService,
      center,

      ...dataUtils,

      previousState,
    };
  },
});
