/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MonthlyPaymentDetails from './monthly-payment-details.vue';
import MonthlyPaymentService from './monthly-payment.service';
import AlertService from '@/shared/alert/alert.service';

type MonthlyPaymentDetailsComponentType = InstanceType<typeof MonthlyPaymentDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const monthlyPaymentSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('MonthlyPayment Management Detail Component', () => {
    let monthlyPaymentServiceStub: SinonStubbedInstance<MonthlyPaymentService>;
    let mountOptions: MountingOptions<MonthlyPaymentDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      monthlyPaymentServiceStub = sinon.createStubInstance<MonthlyPaymentService>(MonthlyPaymentService);

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
          monthlyPaymentService: () => monthlyPaymentServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        monthlyPaymentServiceStub.find.resolves(monthlyPaymentSample);
        route = {
          params: {
            monthlyPaymentId: '' + 123,
          },
        };
        const wrapper = shallowMount(MonthlyPaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.monthlyPayment).toMatchObject(monthlyPaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        monthlyPaymentServiceStub.find.resolves(monthlyPaymentSample);
        const wrapper = shallowMount(MonthlyPaymentDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
