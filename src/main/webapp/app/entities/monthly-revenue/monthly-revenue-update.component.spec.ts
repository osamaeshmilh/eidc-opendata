/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import MonthlyRevenueUpdate from './monthly-revenue-update.vue';
import MonthlyRevenueService from './monthly-revenue.service';
import AlertService from '@/shared/alert/alert.service';

import ClauseService from '@/entities/clause/clause.service';
import JehaService from '@/entities/jeha/jeha.service';

type MonthlyRevenueUpdateComponentType = InstanceType<typeof MonthlyRevenueUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const monthlyRevenueSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<MonthlyRevenueUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('MonthlyRevenue Management Update Component', () => {
    let comp: MonthlyRevenueUpdateComponentType;
    let monthlyRevenueServiceStub: SinonStubbedInstance<MonthlyRevenueService>;

    beforeEach(() => {
      route = {};
      monthlyRevenueServiceStub = sinon.createStubInstance<MonthlyRevenueService>(MonthlyRevenueService);
      monthlyRevenueServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          monthlyRevenueService: () => monthlyRevenueServiceStub,
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
        const wrapper = shallowMount(MonthlyRevenueUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.monthlyRevenue = monthlyRevenueSample;
        monthlyRevenueServiceStub.update.resolves(monthlyRevenueSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(monthlyRevenueServiceStub.update.calledWith(monthlyRevenueSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        monthlyRevenueServiceStub.create.resolves(entity);
        const wrapper = shallowMount(MonthlyRevenueUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.monthlyRevenue = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(monthlyRevenueServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        monthlyRevenueServiceStub.find.resolves(monthlyRevenueSample);
        monthlyRevenueServiceStub.retrieve.resolves([monthlyRevenueSample]);

        // WHEN
        route = {
          params: {
            monthlyRevenueId: '' + monthlyRevenueSample.id,
          },
        };
        const wrapper = shallowMount(MonthlyRevenueUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.monthlyRevenue).toMatchObject(monthlyRevenueSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        monthlyRevenueServiceStub.find.resolves(monthlyRevenueSample);
        const wrapper = shallowMount(MonthlyRevenueUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
