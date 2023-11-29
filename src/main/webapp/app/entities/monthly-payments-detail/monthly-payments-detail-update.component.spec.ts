/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MonthlyPaymentsDetailUpdate from './monthly-payments-detail-update.vue';
import MonthlyPaymentsDetailService from './monthly-payments-detail.service';
import AlertService from '@/shared/alert/alert.service';

import MonthlyPaymentService from '@/entities/monthly-payment/monthly-payment.service';

type MonthlyPaymentsDetailUpdateComponentType = InstanceType<typeof MonthlyPaymentsDetailUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const monthlyPaymentsDetailSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MonthlyPaymentsDetailUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MonthlyPaymentsDetail Management Update Component', () => {
    let comp: MonthlyPaymentsDetailUpdateComponentType;
    let monthlyPaymentsDetailServiceStub: SinonStubbedInstance<MonthlyPaymentsDetailService>;

    beforeEach(() => {
      route = {};
      monthlyPaymentsDetailServiceStub = sinon.createStubInstance<MonthlyPaymentsDetailService>(MonthlyPaymentsDetailService);
      monthlyPaymentsDetailServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

      alertService = new AlertService({
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'b-input-group': true,
          'b-input-group-prepend': true,
          'b-form-datepicker': true,
          'b-form-input': true,
        },
        provide: {
          alertService,
          monthlyPaymentsDetailService: () => monthlyPaymentsDetailServiceStub,
          monthlyPaymentService: () =>
            sinon.createStubInstance<MonthlyPaymentService>(MonthlyPaymentService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(MonthlyPaymentsDetailUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.monthlyPaymentsDetail = monthlyPaymentsDetailSample;
        monthlyPaymentsDetailServiceStub.update.resolves(monthlyPaymentsDetailSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentsDetailServiceStub.update.calledWith(monthlyPaymentsDetailSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        monthlyPaymentsDetailServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MonthlyPaymentsDetailUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.monthlyPaymentsDetail = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentsDetailServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        monthlyPaymentsDetailServiceStub.find.resolves(monthlyPaymentsDetailSample);
        monthlyPaymentsDetailServiceStub.retrieve.resolves([monthlyPaymentsDetailSample]);

        // WHEN
        route = {
          params: {
            monthlyPaymentsDetailId: '' + monthlyPaymentsDetailSample.id,
          },
        };
        const wrapper = shallowMount(MonthlyPaymentsDetailUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.monthlyPaymentsDetail).toMatchObject(monthlyPaymentsDetailSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        monthlyPaymentsDetailServiceStub.find.resolves(monthlyPaymentsDetailSample);
        const wrapper = shallowMount(MonthlyPaymentsDetailUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
