/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import ClauseDetails from './clause-details.vue';
import ClauseService from './clause.service';
import AlertService from '@/shared/alert/alert.service';

type ClauseDetailsComponentType = InstanceType<typeof ClauseDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const clauseSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Clause Management Detail Component', () => {
    let clauseServiceStub: SinonStubbedInstance<ClauseService>;
    let mountOptions: MountingOptions<ClauseDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      clauseServiceStub = sinon.createStubInstance<ClauseService>(ClauseService);

      alertService = new AlertService({
        bvToast: {
          toast: vitest.fn(),
        } as any,
      });

      mountOptions = {
        stubs: {
          'font-awesome-icon': true,
          'router-link': true,
        },
        provide: {
          alertService,
          clauseService: () => clauseServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        clauseServiceStub.find.resolves(clauseSample);
        route = {
          params: {
            clauseId: '' + 123,
          },
        };
        const wrapper = shallowMount(ClauseDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.clause).toMatchObject(clauseSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        clauseServiceStub.find.resolves(clauseSample);
        const wrapper = shallowMount(ClauseDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
