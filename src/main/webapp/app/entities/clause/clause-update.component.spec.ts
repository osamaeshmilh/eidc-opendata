/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ClauseUpdate from './clause-update.vue';
import ClauseService from './clause.service';
import AlertService from '@/shared/alert/alert.service';

type ClauseUpdateComponentType = InstanceType<typeof ClauseUpdate>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const clauseSample = { id: 123 };

describe('Component Tests', () => {
  let mountOptions: MountingOptions<ClauseUpdateComponentType>['global'];
  let alertService: AlertService;

  describe('Clause Management Update Component', () => {
    let comp: ClauseUpdateComponentType;
    let clauseServiceStub: SinonStubbedInstance<ClauseService>;

    beforeEach(() => {
      route = {};
      clauseServiceStub = sinon.createStubInstance<ClauseService>(ClauseService);
      clauseServiceStub.retrieve.onFirstCall().resolves(Promise.resolve([]));

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
          clauseService: () => clauseServiceStub,
        },
      };
    });

    afterEach(() => {
      vitest.resetAllMocks();
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const wrapper = shallowMount(ClauseUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.clause = clauseSample;
        clauseServiceStub.update.resolves(clauseSample);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(clauseServiceStub.update.calledWith(clauseSample)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        clauseServiceStub.create.resolves(entity);
        const wrapper = shallowMount(ClauseUpdate, { global: mountOptions });
        comp = wrapper.vm;
        comp.clause = entity;

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(clauseServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        clauseServiceStub.find.resolves(clauseSample);
        clauseServiceStub.retrieve.resolves([clauseSample]);

        // WHEN
        route = {
          params: {
            clauseId: '' + clauseSample.id,
          },
        };
        const wrapper = shallowMount(ClauseUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        // THEN
        expect(comp.clause).toMatchObject(clauseSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        clauseServiceStub.find.resolves(clauseSample);
        const wrapper = shallowMount(ClauseUpdate, { global: mountOptions });
        comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
