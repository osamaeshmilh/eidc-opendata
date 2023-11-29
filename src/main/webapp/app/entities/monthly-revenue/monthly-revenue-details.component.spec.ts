/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MonthlyRevenueDetails from './monthly-revenue-details.vue';
import MonthlyRevenueService from './monthly-revenue.service';
import AlertService from '@/shared/alert/alert.service';

type MonthlyRevenueDetailsComponentType = InstanceType<typeof MonthlyRevenueDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const monthlyRevenueSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MonthlyRevenue Management Detail Component', () => {
    let monthlyRevenueServiceStub: SinonStubbedInstance<MonthlyRevenueService>;
    let mountOptions: MountingOptions<MonthlyRevenueDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      monthlyRevenueServiceStub = sinon.createStubInstance<MonthlyRevenueService>(MonthlyRevenueService);

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
          monthlyRevenueService: () => monthlyRevenueServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        monthlyRevenueServiceStub.find.resolves(monthlyRevenueSample);
        route = {
          params: {
            monthlyRevenueId: '' + 123,
          },
        };
        const wrapper = shallowMount(MonthlyRevenueDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.monthlyRevenue).toMatchObject(monthlyRevenueSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        monthlyRevenueServiceStub.find.resolves(monthlyRevenueSample);
        const wrapper = shallowMount(MonthlyRevenueDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
