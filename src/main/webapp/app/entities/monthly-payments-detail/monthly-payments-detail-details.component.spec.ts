/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MonthlyPaymentsDetailDetails from './monthly-payments-detail-details.vue';
import MonthlyPaymentsDetailService from './monthly-payments-detail.service';
import AlertService from '@/shared/alert/alert.service';

type MonthlyPaymentsDetailDetailsComponentType = InstanceType<typeof MonthlyPaymentsDetailDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const monthlyPaymentsDetailSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MonthlyPaymentsDetail Management Detail Component', () => {
    let monthlyPaymentsDetailServiceStub: SinonStubbedInstance<MonthlyPaymentsDetailService>;
    let mountOptions: MountingOptions<MonthlyPaymentsDetailDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      monthlyPaymentsDetailServiceStub = sinon.createStubInstance<MonthlyPaymentsDetailService>(MonthlyPaymentsDetailService);

      alertService = new AlertService({
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          monthlyPaymentsDetailService: () => monthlyPaymentsDetailServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        monthlyPaymentsDetailServiceStub.find.resolves(monthlyPaymentsDetailSample);
        route = {
          params: {
            monthlyPaymentsDetailId: '' + 123,
          },
        };
        const wrapper = shallowMount(MonthlyPaymentsDetailDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.monthlyPaymentsDetail).toMatchObject(monthlyPaymentsDetailSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        monthlyPaymentsDetailServiceStub.find.resolves(monthlyPaymentsDetailSample);
        const wrapper = shallowMount(MonthlyPaymentsDetailDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
