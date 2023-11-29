/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MonthlyPaymentUpdate from './monthly-payment-update.vue';
import MonthlyPaymentService from './monthly-payment.service';
import AlertService from '@/shared/alert/alert.service';

import ClauseService from '@/entities/clause/clause.service';
import JehaService from '@/entities/jeha/jeha.service';

type MonthlyPaymentUpdateComponentType = InstanceType<typeof MonthlyPaymentUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const monthlyPaymentSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MonthlyPaymentUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MonthlyPayment Management Update Component', () => {
    let comp: MonthlyPaymentUpdateComponentType;
    let monthlyPaymentServiceStub: SinonStubbedInstance<MonthlyPaymentService>;

    beforeEach(() => {
      route = {};
      monthlyPaymentServiceStub = sinon.createStubInstance<MonthlyPaymentService>(MonthlyPaymentService);
      monthlyPaymentServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          monthlyPaymentService: () => monthlyPaymentServiceStub,
          clauseService: () =>
            sinon.createStubInstance<ClauseService>(ClauseService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          jehaService: () =>
            sinon.createStubInstance<JehaService>(JehaService, {
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
        const wrapper = shallowMount(MonthlyPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.monthlyPayment = monthlyPaymentSample;
        monthlyPaymentServiceStub.update.resolves(monthlyPaymentSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentServiceStub.update.calledWith(monthlyPaymentSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        monthlyPaymentServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MonthlyPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.monthlyPayment = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        monthlyPaymentServiceStub.find.resolves(monthlyPaymentSample);
        monthlyPaymentServiceStub.retrieve.resolves([monthlyPaymentSample]);

        // WHEN
        route = {
          params: {
            monthlyPaymentId: '' + monthlyPaymentSample.id,
          },
        };
        const wrapper = shallowMount(MonthlyPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.monthlyPayment).toMatchObject(monthlyPaymentSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        monthlyPaymentServiceStub.find.resolves(monthlyPaymentSample);
        const wrapper = shallowMount(MonthlyPaymentUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
