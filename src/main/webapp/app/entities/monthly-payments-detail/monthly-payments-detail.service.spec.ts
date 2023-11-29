/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';
import dayjs from 'dayjs';

import MonthlyPaymentsDetailService from './monthly-payments-detail.service';
import { DATE_FORMAT } from '@/shared/composables/date-format';
import { MonthlyPaymentsDetail } from '@/shared/model/monthly-payments-detail.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('MonthlyPaymentsDetail Service', () => {
    let service: MonthlyPaymentsDetailService;
    let elemDefault;
    let currentDate: Date;

    beforeEach(() => {
      service = new MonthlyPaymentsDetailService();
      currentDate = new Date();
      elemDefault = new MonthlyPaymentsDetail(123, 'AAAAAAA', 0, currentDate, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            dateOfPayment: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a MonthlyPaymentsDetail', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
            dateOfPayment: dayjs(currentDate).format(DATE_FORMAT),
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            dateOfPayment: currentDate,
          },
          returnedFromService,
        );

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a MonthlyPaymentsDetail', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a MonthlyPaymentsDetail', async () => {
        const returnedFromService = Object.assign(
          {
            beneficiary: 'BBBBBB',
            payment: 1,
            dateOfPayment: dayjs(currentDate).format(DATE_FORMAT),
            description: 'BBBBBB',
            notes: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign(
          {
            dateOfPayment: currentDate,
          },
          returnedFromService,
        );
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a MonthlyPaymentsDetail', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a MonthlyPaymentsDetail', async () => {
        const patchObject = Object.assign(
          {
            beneficiary: 'BBBBBB',
            description: 'BBBBBB',
            notes: 'BBBBBB',
          },
          new MonthlyPaymentsDetail(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            dateOfPayment: currentDate,
          },
          returnedFromService,
        );
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a MonthlyPaymentsDetail', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of MonthlyPaymentsDetail', async () => {
        const returnedFromService = Object.assign(
          {
            beneficiary: 'BBBBBB',
            payment: 1,
            dateOfPayment: dayjs(currentDate).format(DATE_FORMAT),
            description: 'BBBBBB',
            notes: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign(
          {
            dateOfPayment: currentDate,
          },
          returnedFromService,
        );
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of MonthlyPaymentsDetail', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a MonthlyPaymentsDetail', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a MonthlyPaymentsDetail', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
