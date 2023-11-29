import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import MonthlyRevenueService from './monthly-revenue.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import ClauseService from '@/entities/clause/clause.service';
import { type IClause } from '@/shared/model/clause.model';
import JehaService from '@/entities/jeha/jeha.service';
import { type IJeha } from '@/shared/model/jeha.model';
import { type IMonthlyRevenue, MonthlyRevenue } from '@/shared/model/monthly-revenue.model';
import { Chapter } from '@/shared/model/enumerations/chapter.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MonthlyRevenueUpdate',
  setup() {
    const monthlyRevenueService = inject('monthlyRevenueService', () => new MonthlyRevenueService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const monthlyRevenue: Ref<IMonthlyRevenue> = ref(new MonthlyRevenue());

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
      revenueCollected: {},
      annualEstablishedBudget: {},
      actualRevenueBeginningToEndLastMonth: {},
      actualRevenueDuringMonth: {},
      totalRevenueUntilEndMonth: {},
      existingConnections: {},
      totalActualRevenue: {},
      balanceOfClause: {},
      comments: {},
      clause: {},
      jeha: {},
    };
    const v$ = useVuelidate(validationRules, monthlyRevenue as any);
    v$.value.$validate();

    return {
      monthlyRevenueService,
      alertService,
      monthlyRevenue,
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
      if (this.monthlyRevenue.id) {
        this.monthlyRevenueService()
          .update(this.monthlyRevenue)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo('A MonthlyRevenue is updated with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.monthlyRevenueService()
          .create(this.monthlyRevenue)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess('A MonthlyRevenue is created with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
