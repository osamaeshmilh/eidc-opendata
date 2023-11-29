/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SectorUpdate from './sector-update.vue';
import SectorService from './sector.service';
import AlertService from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';

type SectorUpdateComponentType = InstanceType<typeof SectorUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sectorSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<SectorUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Sector Management Update Component', () => {
    let comp: SectorUpdateComponentType;
    let sectorServiceStub: SinonStubbedInstance<SectorService>;

    beforeEach(() => {
      route = {};
      sectorServiceStub = sinon.createStubInstance<SectorService>(SectorService);
      sectorServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          sectorService: () => sectorServiceStub,

          userService: () =>
            sinon.createStubInstance<UserService>(UserService, {
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
        const wrapper = shallowMount(SectorUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sector = sectorSample;
        sectorServiceStub.update.resolves(sectorSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sectorServiceStub.update.calledWith(sectorSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        sectorServiceStub.create.resolves(entity);
        const wrapper = shallowMount(SectorUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.sector = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(sectorServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        sectorServiceStub.find.resolves(sectorSample);
        sectorServiceStub.retrieve.resolves([sectorSample]);

        // WHEN
        route = {
          params: {
            sectorId: '' + sectorSample.id,
          },
        };
        const wrapper = shallowMount(SectorUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.sector).toMatchObject(sectorSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sectorServiceStub.find.resolves(sectorSample);
        const wrapper = shallowMount(SectorUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
