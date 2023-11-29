/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';

import MonthlyPayment from './monthly-payment.vue';
import MonthlyPaymentService from './monthly-payment.service';
import AlertService from '@/shared/alert/alert.service';

type MonthlyPaymentComponentType = InstanceType<typeof MonthlyPayment>;

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  let alertService: AlertService;

  describe('MonthlyPayment Management Component', () => {
    let monthlyPaymentServiceStub: SinonStubbedInstance<MonthlyPaymentService>;
    let mountOptions: MountingOptions<MonthlyPaymentComponentType>['global'];

    beforeEach(() => {
      monthlyPaymentServiceStub = sinon.createStubInstance<MonthlyPaymentService>(MonthlyPaymentService);
      monthlyPaymentServiceStub.retrieve.resolves({ headers: {} });

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
          monthlyPaymentService: () => monthlyPaymentServiceStub,
        },
      };
    });

    describe('Mount', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        monthlyPaymentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        const wrapper = shallowMount(MonthlyPayment, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentServiceStub.retrieve.calledOnce).toBeTruthy();
        expect(comp.monthlyPayments[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for an id', async () => {
        // WHEN
        const wrapper = shallowMount(MonthlyPayment, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['id,asc'],
        });
      });
    });
    describe('Handles', () => {
      let comp: MonthlyPaymentComponentType;

      beforeEach(async () => {
        const wrapper = shallowMount(MonthlyPayment, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();
        monthlyPaymentServiceStub.retrieve.reset();
        monthlyPaymentServiceStub.retrieve.resolves({ headers: {}, data: [] });
      });

      it('should load a page', async () => {
        // GIVEN
        monthlyPaymentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.page = 2;
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentServiceStub.retrieve.called).toBeTruthy();
        expect(comp.monthlyPayments[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should not load a page if the page is the same as the previous page', () => {
        // WHEN
        comp.page = 1;

        // THEN
        expect(monthlyPaymentServiceStub.retrieve.called).toBeFalsy();
      });

      it('should re-initialize the page', async () => {
        // GIVEN
        comp.page = 2;
        await comp.$nextTick();
        monthlyPaymentServiceStub.retrieve.reset();
        monthlyPaymentServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

        // WHEN
        comp.clear();
        await comp.$nextTick();

        // THEN
        expect(comp.page).toEqual(1);
        expect(monthlyPaymentServiceStub.retrieve.callCount).toEqual(1);
        expect(comp.monthlyPayments[0]).toEqual(expect.objectContaining({ id: 123 }));
      });

      it('should calculate the sort attribute for a non-id attribute', async () => {
        // WHEN
        comp.propOrder = 'name';
        await comp.$nextTick();

        // THEN
        expect(monthlyPaymentServiceStub.retrieve.lastCall.firstArg).toMatchObject({
          sort: ['name,asc', 'id'],
        });
      });

      it('Should call delete service on confirmDelete', async () => {
        // GIVEN
        monthlyPaymentServiceStub.delete.resolves({});

        // WHEN
        comp.prepareRemove({ id: 123 });

        comp.removeMonthlyPayment();
        await comp.$nextTick(); // clear components

        // THEN
        expect(monthlyPaymentServiceStub.delete.called).toBeTruthy();

        // THEN
        await comp.$nextTick(); // handle component clear watch
        expect(monthlyPaymentServiceStub.retrieve.callCount).toEqual(1);
      });
    });
  });
});
