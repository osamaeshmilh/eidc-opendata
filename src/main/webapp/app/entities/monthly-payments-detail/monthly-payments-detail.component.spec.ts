/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import MonthlyPaymentsDetail from './monthly-payments-detail.vue';
import MonthlyPaymentsDetailService from './monthly-payments-detail.service';
import AlertService from '@/shared/alert/alert.service';

type MonthlyPaymentsDetailComponentType = InstanceType<typeof MonthlyPaymentsDetail>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('MonthlyPaymentsDetail Management Component', () => {
    let monthlyPaymentsDetailServiceStub: SinonStubbedInstance<MonthlyPaymentsDetailService>;
    let mountOptions: MountingOptions<MonthlyPaymentsDetailComponentType>['global'];

    beforeEach(() => {
      monthlyPaymentsDetailServiceStub = sinon.createStubInstance<MonthlyPaymentsDetailService>(MonthlyPaymentsDetailService);
      monthlyPaymentsDetailServiceStub.retrieve.resolves({ headers: {} });

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
          monthlyPaymentsDetailService: () => monthlyPaymentsDetailServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        monthlyPaymentsDetailServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(MonthlyPaymentsDetail, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentsDetailServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.monthlyPaymentsDetails[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(MonthlyPaymentsDetail, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentsDetailServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: MonthlyPaymentsDetailComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(MonthlyPaymentsDetail, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        monthlyPaymentsDetailServiceStub.retrieve.reset();
        monthlyPaymentsDetailServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        monthlyPaymentsDetailServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentsDetailServiceStub.retrieve.called).toBeTruthy();
        expect(comp.monthlyPaymentsDetails[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(monthlyPaymentsDetailServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        monthlyPaymentsDetailServiceStub.retrieve.reset();
        monthlyPaymentsDetailServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(monthlyPaymentsDetailServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.monthlyPaymentsDetails[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentsDetailServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        monthlyPaymentsDetailServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeMonthlyPaymentsDetail();
        await comp.$nextTick(); // clear components

        // THEN
        expect(monthlyPaymentsDetailServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(monthlyPaymentsDetailServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
