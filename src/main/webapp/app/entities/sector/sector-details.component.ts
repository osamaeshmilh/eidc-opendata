import { defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import SectorService from './sector.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { type ISector } from '@/shared/model/sector.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'SectorDetails',
  setup() {
    const sectorService = inject('sectorService', () => new SectorService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const sector: Ref<ISector> = ref({});

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

    return {
      alertService,
      sector,

      ...dataUtils,

      previousState,
    };
  },
});
