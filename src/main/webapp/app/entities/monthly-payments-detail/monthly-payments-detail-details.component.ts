import { defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import MonthlyPaymentsDetailService from './monthly-payments-detail.service';
import { type IMonthlyPaymentsDetail } from '@/shared/model/monthly-payments-detail.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MonthlyPaymentsDetailDetails',
  setup() {
    const monthlyPaymentsDetailService = inject('monthlyPaymentsDetailService', () => new MonthlyPaymentsDetailService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const monthlyPaymentsDetail: Ref<IMonthlyPaymentsDetail> = ref({});

    const retrieveMonthlyPaymentsDetail = async monthlyPaymentsDetailId => {
      try {
        const res = await monthlyPaymentsDetailService().find(monthlyPaymentsDetailId);
        monthlyPaymentsDetail.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.monthlyPaymentsDetailId) {
      retrieveMonthlyPaymentsDetail(route.params.monthlyPaymentsDetailId);
    }

    return {
      alertService,
      monthlyPaymentsDetail,

      previousState,
    };
  },
});
