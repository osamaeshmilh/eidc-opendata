import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MonthlyPaymentService from './monthly-payment.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ClauseService from '@/entities/clause/clause.service';
import { type IClause } from '@/shared/model/clause.model';
import JehaService from '@/entities/jeha/jeha.service';
import { type IJeha } from '@/shared/model/jeha.model';
import { type IMonthlyPayment, MonthlyPayment } from '@/shared/model/monthly-payment.model';
import { Chapter } from '@/shared/model/enumerations/chapter.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MonthlyPaymentUpdate',
  setup() {
    const monthlyPaymentService = inject('monthlyPaymentService', () => new MonthlyPaymentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const monthlyPayment: Ref<IMonthlyPayment> = ref(new MonthlyPayment());

    const clauseService = inject('clauseService', () => new ClauseService());

    const clauses: Ref<IClause[]> = ref([]);

    const jehaService = inject('jehaService', () => new JehaService());

    const jehas: Ref<IJeha[]> = ref([]);
    const chapterValues: Ref<string[]> = ref(Object.keys(Chapter));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const initRelationships = () => {
      clauseService()
        .retrieve()
        .then(res => {
          clauses.value = res.data;
        });
      jehaService()
        .retrieve()
        .then(res => {
          jehas.value = res.data;
        });
    };

    initRelationships();

    const validations = useValidation();
    const validationRules = {
      chapter: {},
      month: {},
      year: {},
      isTwelve: {},
      annualEstablishedBudget: {},
      actualExpensesBeginningToEndLastMonth: {},
      actualExpensesDuringMonth: {},
      totalexpensesUntilEndMonth: {},
      existingConnections: {},
      totalActualExpenses: {},
      balanceOfClause: {},
      comments: {},
      clause: {},
      jeha: {},
      monthlyPaymentsDetails: {},
    };
    const v$ = useVuelidate(validationRules, monthlyPayment as any);
    v$.value.$validate();

    return {
      monthlyPaymentService,
      alertService,
      monthlyPayment,
      previousState,
      chapterValues,
      isSaving,
      currentLanguage,
      clauses,
      jehas,
      v$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.monthlyPayment.id) {
        this.monthlyPaymentService()
          .update(this.monthlyPayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo('A MonthlyPayment is updated with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.monthlyPaymentService()
          .create(this.monthlyPayment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess('A MonthlyPayment is created with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
