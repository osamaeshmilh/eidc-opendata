/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import CenterDetails from './center-details.vue';
import CenterService from './center.service';
import AlertService from '@/shared/alert/alert.service';

type CenterDetailsComponentType = InstanceType<typeof CenterDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const centerSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Center Management Detail Component', () => {
    let centerServiceStub: SinonStubbedInstance<CenterService>;
    let mountOptions: MountingOptions<CenterDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      centerServiceStub = sinon.createStubInstance<CenterService>(CenterService);

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
          centerService: () => centerServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        centerServiceStub.find.resolves(centerSample);
        route = {
          params: {
            centerId: '' + 123,
          },
        };
        const wrapper = shallowMount(CenterDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.center).toMatchObject(centerSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        centerServiceStub.find.resolves(centerSample);
        const wrapper = shallowMount(CenterDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
