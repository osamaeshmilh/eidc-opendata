import { defineComponent, inject, onMounted, ref, type Ref, watch } from 'vue';

import MonthlyPaymentsDetailService from './monthly-payments-detail.service';
import { type IMonthlyPaymentsDetail } from '@/shared/model/monthly-payments-detail.model';
import { useAlertService } from '@/shared/alert/alert.service';

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'MonthlyPaymentsDetail',
  setup() {
    const monthlyPaymentsDetailService = inject('monthlyPaymentsDetailService', () => new MonthlyPaymentsDetailService());
    const alertService = inject('alertService', () => useAlertService(), true);

    const itemsPerPage = ref(20);
    const queryCount: Ref<number> = ref(null);
    const page: Ref<number> = ref(1);
    const propOrder = ref('id');
    const reverse = ref(false);
    const totalItems = ref(0);

    const monthlyPaymentsDetails: Ref<IMonthlyPaymentsDetail[]> = ref([]);

    const isFetching = ref(false);

    const clear = () => {
      page.value = 1;
    };

    const sort = (): Array<any> => {
      const result = [propOrder.value + ',' + (reverse.value ? 'desc' : 'asc')];
      if (propOrder.value !== 'id') {
        result.push('id');
      }
      return result;
    };

    const retrieveMonthlyPaymentsDetails = async () => {
      isFetching.value = true;
      try {
        const paginationQuery = {
          page: page.value - 1,
          size: itemsPerPage.value,
          sort: sort(),
        };
        const res = await monthlyPaymentsDetailService().retrieve(paginationQuery);
        totalItems.value = Number(res.headers['x-total-count']);
        queryCount.value = totalItems.value;
        monthlyPaymentsDetails.value = res.data;
      } catch (err) {
        alertService.showHttpError(err.response);
      } finally {
        isFetching.value = false;
      }
    };

    const handleSyncList = () => {
      retrieveMonthlyPaymentsDetails();
    };

    onMounted(async () => {
      await retrieveMonthlyPaymentsDetails();
    });

    const removeId: Ref<number> = ref(null);
    const removeEntity = ref<any>(null);
    const prepareRemove = (instance: IMonthlyPaymentsDetail) => {
      removeId.value = instance.id;
      removeEntity.value.show();
    };
    const closeDialog = () => {
      removeEntity.value.hide();
    };
    const removeMonthlyPaymentsDetail = async () => {
      try {
        await monthlyPaymentsDetailService().delete(removeId.value);
        const message = 'A MonthlyPaymentsDetail is deleted with identifier ' + removeId.value;
        alertService.showInfo(message, { variant: 'danger' });
        removeId.value = null;
        retrieveMonthlyPaymentsDetails();
        closeDialog();
      } catch (error) {
        alertService.showHttpError(error.response);
      }
    };

    const changeOrder = (newOrder: string) => {
      if (propOrder.value === newOrder) {
        reverse.value = !reverse.value;
      } else {
        reverse.value = false;
      }
      propOrder.value = newOrder;
    };

    // Whenever order changes, reset the pagination
    watch([propOrder, reverse], async () => {
      if (page.value === 1) {
        // first page, retrieve new data
        await retrieveMonthlyPaymentsDetails();
      } else {
        // reset the pagination
        clear();
      }
    });

    // Whenever page changes, switch to the new page.
    watch(page, async () => {
      await retrieveMonthlyPaymentsDetails();
    });

    return {
      monthlyPaymentsDetails,
      handleSyncList,
      isFetching,
      retrieveMonthlyPaymentsDetails,
      clear,
      removeId,
      removeEntity,
      prepareRemove,
      closeDialog,
      removeMonthlyPaymentsDetail,
      itemsPerPage,
      queryCount,
      page,
      propOrder,
      reverse,
      totalItems,
      changeOrder,
    };
  },
});
