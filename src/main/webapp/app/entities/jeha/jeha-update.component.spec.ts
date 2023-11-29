/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import JehaUpdate from './jeha-update.vue';
import JehaService from './jeha.service';
import AlertService from '@/shared/alert/alert.service';

import UserService from '@/entities/user/user.service';
import SectorService from '@/entities/sector/sector.service';

type JehaUpdateComponentType = InstanceType<typeof JehaUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const jehaSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<JehaUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Jeha Management Update Component', () => {
    let comp: JehaUpdateComponentType;
    let jehaServiceStub: SinonStubbedInstance<JehaService>;

    beforeEach(() => {
      route = {};
      jehaServiceStub = sinon.createStubInstance<JehaService>(JehaService);
      jehaServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          jehaService: () => jehaServiceStub,

          userService: () =>
            sinon.createStubInstance<UserService>(UserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
          sectorService: () =>
            sinon.createStubInstance<SectorService>(SectorService, {
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
        const wrapper = shallowMount(JehaUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.jeha = jehaSample;
        jehaServiceStub.update.resolves(jehaSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jehaServiceStub.update.calledWith(jehaSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        jehaServiceStub.create.resolves(entity);
        const wrapper = shallowMount(JehaUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.jeha = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(jehaServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        jehaServiceStub.find.resolves(jehaSample);
        jehaServiceStub.retrieve.resolves([jehaSample]);

        // WHEN
        route = {
          params: {
            jehaId: '' + jehaSample.id,
          },
        };
        const wrapper = shallowMount(JehaUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.jeha).toMatchObject(jehaSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        jehaServiceStub.find.resolves(jehaSample);
        const wrapper = shallowMount(JehaUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
