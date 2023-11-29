import { computed, defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useVuelidate } from '@vuelidate/core';

import AttachmentService from './attachment.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { useValidation } from '@/shared/composables';
import { useAlertService } from '@/shared/alert/alert.service';

import { type IAttachment, Attachment } from '@/shared/model/attachment.model';
import { Reference } from '@/shared/model/enumerations/reference.model';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AttachmentUpdate',
  setup() {
    const attachmentService = inject('attachmentService', () => new AttachmentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const attachment: Ref<IAttachment> = ref(new Attachment());
    const referenceValues: Ref<string[]> = ref(Object.keys(Reference));
    const isSaving = ref(false);
    const currentLanguage = inject('currentLanguage', () => computed(() => navigator.language ?? 'en'), true);

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);

    const retrieveAttachment = async attachmentId => {
      try {
        const res = await attachmentService().find(attachmentId);
        attachment.value = res;
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    if (route.params?.attachmentId) {
      retrieveAttachment(route.params.attachmentId);
    }

    const dataUtils = useDataUtils();

    const validations = useValidation();
    const validationRules = {
      name: {},
      file: {},
      fileUrl: {},
      referenceId: {},
      reference: {},
    };
    const v$ = useVuelidate(validationRules, attachment as any);
    v$.value.$validate();

    return {
      attachmentService,
      alertService,
      attachment,
      previousState,
      referenceValues,
      isSaving,
      currentLanguage,
      ...dataUtils,
      v$,
    };
  },
  created(): void {},
  methods: {
    save(): void {
      this.isSaving = true;
      if (this.attachment.id) {
        this.attachmentService()
          .update(this.attachment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showInfo('A Attachment is updated with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      } else {
        this.attachmentService()
          .create(this.attachment)
          .then(param => {
            this.isSaving = false;
            this.previousState();
            this.alertService.showSuccess('A Attachment is created with identifier ' + param.id);
          })
          .catch(error => {
            this.isSaving = false;
            this.alertService.showHttpError(error.response);
          });
      }
    },
  },
});
