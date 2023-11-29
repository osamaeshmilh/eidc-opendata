import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MonthlyPaymentsDetailService from './monthly-payments-detail.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import MonthlyPaymentService from '@/entities/monthly-payment/monthly-payment.service';
import { type IMonthlyPayment } from '@/shared/model/monthly-payment.model';
import { type IMonthlyPaymentsDetail, MonthlyPaymentsDetail } from '@/shared/model/monthly-payments-detail.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MonthlyPaymentsDetailUpdate',
  setup() {
    const monthlyPaymentsDetailService = inject('monthlyPaymentsDetailService', () => new MonthlyPaymentsDetailService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const monthlyPaymentsDetail: Ref<IMonthlyPaymentsDetail> = ref(new MonthlyPaymentsDetail());

    const monthlyPaymentService = inject('monthlyPaymentService', () => new MonthlyPaymentService());

    const monthlyPayments: Ref<IMonthlyPayment[]> = ref([]);
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const initRelationships = () => {
      monthlyPaymentService()
        .retrieve()
        .then(res => {
          monthlyPayments.value = res.data;
        });
    };

    initRelationships();

    const validations = useValidation();
    const validationRules = {
      beneficiary: {},
      payment: {},
      dateOfPayment: {},
      description: {},
      notes: {},
      monthlyPayment: {},
    };
    const v$ = useVuelidate(validationRules, monthlyPaymentsDetail as any);
    v$.value.$validate();

    return {
      monthlyPaymentsDetailService,
      alertService,
      monthlyPaymentsDetail,
      previousState,
      isSaving,
      currentLanguage,
      monthlyPayments,
      v$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.monthlyPaymentsDetail.id) {
        this.monthlyPaymentsDetailService()
          .update(this.monthlyPaymentsDetail)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo('A MonthlyPaymentsDetail is updated with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.monthlyPaymentsDetailService()
          .create(this.monthlyPaymentsDetail)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess('A MonthlyPaymentsDetail is created with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
