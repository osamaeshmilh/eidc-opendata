/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CenterUpdate from './center-update.vue';
import CenterService from './center.service';
import AlertService from '@/shared/alert/alert.service';

import SectorService from '@/entities/sector/sector.service';

import UserService from '@/entities/user/user.service';

type CenterUpdateComponentType = InstanceType<typeof CenterUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const centerSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<CenterUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Center Management Update Component', () => {
    let comp: CenterUpdateComponentType;
    let centerServiceStub: SinonStubbedInstance<CenterService>;

    beforeEach(() => {
      route = {};
      centerServiceStub = sinon.createStubInstance<CenterService>(CenterService);
      centerServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          centerService: () => centerServiceStub,
          sectorService: () =>
            sinon.createStubInstance<SectorService>(SectorService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

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
        const wrapper = shallowMount(CenterUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.center = centerSample;
        centerServiceStub.update.resolves(centerSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(centerServiceStub.update.calledWith(centerSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        centerServiceStub.create.resolves(entity);
        const wrapper = shallowMount(CenterUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.center = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(centerServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        centerServiceStub.find.resolves(centerSample);
        centerServiceStub.retrieve.resolves([centerSample]);

        // WHEN
        route = {
          params: {
            centerId: '' + centerSample.id,
          },
        };
        const wrapper = shallowMount(CenterUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.center).toMatchObject(centerSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        centerServiceStub.find.resolves(centerSample);
        const wrapper = shallowMount(CenterUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
