/* tslint:disable max-line-length */
import { vitest } from 'vitest';
import { shallowMount, type MountingOptions } from '@vue/test-utils';
import sinon, { type SinonStubbedInstance } from 'sinon';
import { type RouteLocation } from 'vue-router';

import SectorDetails from './sector-details.vue';
import SectorService from './sector.service';
import AlertService from '@/shared/alert/alert.service';

type SectorDetailsComponentType = InstanceType<typeof SectorDetails>;

let route: Partial<RouteLocation>;
const routerGoMock = vitest.fn();

vitest.mock('vue-router', () => ({
  useRoute: () => route,
  useRouter: () => ({ go: routerGoMock }),
}));

const sectorSample = { id: 123 };

describe('Component Tests', () => {
  let alertService: AlertService;

  afterEach(() => {
    vitest.resetAllMocks();
  });

  describe('Sector Management Detail Component', () => {
    let sectorServiceStub: SinonStubbedInstance<SectorService>;
    let mountOptions: MountingOptions<SectorDetailsComponentType>['global'];

    beforeEach(() => {
      route = {};
      sectorServiceStub = sinon.createStubInstance<SectorService>(SectorService);

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
          sectorService: () => sectorServiceStub,
        },
      };
    });

    describe('Navigate to details', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        sectorServiceStub.find.resolves(sectorSample);
        route = {
          params: {
            sectorId: '' + 123,
          },
        };
        const wrapper = shallowMount(SectorDetails, { global: mountOptions });
        const comp = wrapper.vm;
        // WHEN
        await comp.$nextTick();

        // THEN
        expect(comp.sector).toMatchObject(sectorSample);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        sectorServiceStub.find.resolves(sectorSample);
        const wrapper = shallowMount(SectorDetails, { global: mountOptions });
        const comp = wrapper.vm;
        await comp.$nextTick();

        comp.previousState();
        await comp.$nextTick();

        expect(routerGoMock).toHaveBeenCalledWith(-1);
      });
    });
  });
});
