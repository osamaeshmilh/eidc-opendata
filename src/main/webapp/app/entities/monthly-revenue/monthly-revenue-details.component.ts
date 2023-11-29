import { defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import MonthlyRevenueService from './monthly-revenue.service';
import { type IMonthlyRevenue } from '@/shared/model/monthly-revenue.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MonthlyRevenueDetails',
  setup() {
    const monthlyRevenueService = inject('monthlyRevenueService', () => new MonthlyRevenueService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const monthlyRevenue: Ref<IMonthlyRevenue> = ref({});

    const retrieveMonthlyRevenue = async monthlyRevenueId => {
      try {
        const res = await monthlyRevenueService().find(monthlyRevenueId);
        monthlyRevenue.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.monthlyRevenueId) {
      retrieveMonthlyRevenue(route.params.monthlyRevenueId);
    }

    return {
      alertService,
      monthlyRevenue,

      previousState,
    };
  },
});
