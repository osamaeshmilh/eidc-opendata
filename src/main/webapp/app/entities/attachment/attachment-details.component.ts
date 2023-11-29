import { defineComponent, inject, ref, type Ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import AttachmentService from './attachment.service';
import useDataUtils from '@/shared/data/data-utils.service';
import { type IAttachment } from '@/shared/model/attachment.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'AttachmentDetails',
  setup() {
    const attachmentService = inject('attachmentService', () => new AttachmentService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const dataUtils = useDataUtils();

    const route = useRoute();
    const router = useRouter();

    const previousState = () => router.go(-1);
    const attachment: Ref<IAttachment> = ref({});

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

    return {
      alertService,
      attachment,

      ...dataUtils,

      previousState,
    };
  },
});
