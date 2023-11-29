import { defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import MonthlyPaymentService from './monthly-payment.service';
import { type IMonthlyPayment } from '@/shared/model/monthly-payment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MonthlyPaymentDetails',
  setup() {
    const monthlyPaymentService = inject('monthlyPaymentService', () => new MonthlyPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const monthlyPayment: Ref<IMonthlyPayment> = ref({});

    const retrieveMonthlyPayment = async monthlyPaymentId => {
      try {
        const res = await monthlyPaymentService().find(monthlyPaymentId);
        monthlyPayment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.monthlyPaymentId) {
      retrieveMonthlyPayment(route.params.monthlyPaymentId);
    }

    return {
      alertService,
      monthlyPayment,

      previousState,
    };
  },
});
