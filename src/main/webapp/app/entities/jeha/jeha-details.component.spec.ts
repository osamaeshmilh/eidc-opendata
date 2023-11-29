/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import JehaDetails from './jeha-details.vue';
import JehaService from './jeha.service';
import AlertService from '@/shared/alert/alert.service';

type JehaDetailsComponentType = InstanceType<typeof JehaDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const jehaSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Jeha Management Detail Component', () => {
    let jehaServiceStub: SinonStubbedInstance<JehaService>;
    let mountOptions: MountingOptions<JehaDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      jehaServiceStub = sinon.createStubInstance<JehaService>(JehaService);

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
          jehaService: () => jehaServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        jehaServiceStub.find.resolves(jehaSample);
        route = {
          params: {
            jehaId: '' + 123,
          },
        };
        const wrapper = shallowMount(JehaDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.jeha).toMatchObject(jehaSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        jehaServiceStub.find.resolves(jehaSample);
        const wrapper = shallowMount(JehaDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
