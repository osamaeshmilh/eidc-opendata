/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import MonthlyRevenue from './monthly-revenue.vue';
import MonthlyRevenueService from './monthly-revenue.service';
import AlertService from '@/shared/alert/alert.service';

type MonthlyRevenueComponentType = InstanceType<typeof MonthlyRevenue>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('MonthlyRevenue Management Component', () => {
    let monthlyRevenueServiceStub: SinonStubbedInstance<MonthlyRevenueService>;
    let mountOptions: MountingOptions<MonthlyRevenueComponentType>['global'];

    beforeEach(() => {
      monthlyRevenueServiceStub = sinon.createStubInstance<MonthlyRevenueService>(MonthlyRevenueService);
      monthlyRevenueServiceStub.retrieve.resolves({ headers: {} });

      alertService = new AlertService({
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          jhiItemCount: true,
          bPagination: true,
          bModal: bModalStub as any,
          'font-awesome-icon': true,
          'b-badge': true,
          'jhi-sort-indicator': true,
          'b-button': true,
          'router-link': true,
        },
        directives: {
          'b-modal': {},
        },
        provide: {
          alertService,
          monthlyRevenueService: () => monthlyRevenueServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        monthlyRevenueServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(MonthlyRevenue, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(monthlyRevenueServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.monthlyRevenues[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(MonthlyRevenue, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(monthlyRevenueServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: MonthlyRevenueComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(MonthlyRevenue, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        monthlyRevenueServiceStub.retrieve.reset();
        monthlyRevenueServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        monthlyRevenueServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(monthlyRevenueServiceStub.retrieve.called).toBeTruthy();
        expect(comp.monthlyRevenues[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(monthlyRevenueServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        monthlyRevenueServiceStub.retrieve.reset();
        monthlyRevenueServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(monthlyRevenueServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.monthlyRevenues[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(monthlyRevenueServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        monthlyRevenueServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeMonthlyRevenue();
        await comp.$nextTick(); // clear components

        // THEN
        expect(monthlyRevenueServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(monthlyRevenueServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
