import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import ClauseService from './clause.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IClause, Clause } from '@/shared/model/clause.model';
import { Chapter } from '@/shared/model/enumerations/chapter.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'ClauseUpdate',
  setup() {
    const clauseService = inject('clauseService', () => new ClauseService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const clause: Ref<IClause> = ref(new Clause());
    const chapterValues: Ref<string[]> = ref(Object.keys(Chapter));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

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

    const validations = useValidation();
    const validationRules = {
      name: {},
      chapter: {},
    };
    const v$ = useVuelidate(validationRules, clause as any);
    v$.value.$validate();

    return {
      clauseService,
      alertService,
      clause,
      previousState,
      chapterValues,
      isSaving,
      currentLanguage,
      v$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.clause.id) {
        this.clauseService()
          .update(this.clause)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo('A Clause is updated with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.clauseService()
          .create(this.clause)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess('A Clause is created with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
