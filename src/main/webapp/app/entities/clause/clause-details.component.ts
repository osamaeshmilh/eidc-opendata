import { defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import ClauseService from './clause.service';
import { type IClause } from '@/shared/model/clause.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClauseDetails',
  setup() {
    const clauseService = inject('clauseService', () => new ClauseService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const clause: Ref<IClause> = ref({});

    const retrieveClause = async clauseId => {
      try {
        const res = await clauseService().find(clauseId);
        clause.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.clauseId) {
      retrieveClause(route.params.clauseId);
    }

    return {
      alertService,
      clause,

      previousState,
    };
  },
});
