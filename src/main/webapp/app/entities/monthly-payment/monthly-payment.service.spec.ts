/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import MonthlyPaymentService from './monthly-payment.service';
import { MonthlyPayment } from '@/shared/model/monthly-payment.model';

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
  describe('MonthlyPayment Service', () => {
    let service: MonthlyPaymentService;
    let elemDefault;

    beforeEach(() => {
      service = new MonthlyPaymentService();
      elemDefault = new MonthlyPayment(123, 'FIRST', 0, 0, false, 0, 0, 0, 0, 'AAAAAAA', 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
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

      it('should create a MonthlyPayment', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a MonthlyPayment', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a MonthlyPayment', async () => {
        const returnedFromService = Object.assign(
          {
            chapter: 'BBBBBB',
            month: 1,
            year: 1,
            isTwelve: true,
            annualEstablishedBudget: 1,
            actualExpensesBeginningToEndLastMonth: 1,
            actualExpensesDuringMonth: 1,
            totalexpensesUntilEndMonth: 1,
            existingConnections: 'BBBBBB',
            totalActualExpenses: 1,
            balanceOfClause: 1,
            comments: 'BBBBBB',
          },
          elemDefault,
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a MonthlyPayment', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a MonthlyPayment', async () => {
        const patchObject = Object.assign(
          {
            month: 1,
            annualEstablishedBudget: 1,
            actualExpensesDuringMonth: 1,
            totalexpensesUntilEndMonth: 1,
            existingConnections: 'BBBBBB',
            balanceOfClause: 1,
          },
          new MonthlyPayment(),
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a MonthlyPayment', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of MonthlyPayment', async () => {
        const returnedFromService = Object.assign(
          {
            chapter: 'BBBBBB',
            month: 1,
            year: 1,
            isTwelve: true,
            annualEstablishedBudget: 1,
            actualExpensesBeginningToEndLastMonth: 1,
            actualExpensesDuringMonth: 1,
            totalexpensesUntilEndMonth: 1,
            existingConnections: 'BBBBBB',
            totalActualExpenses: 1,
            balanceOfClause: 1,
            comments: 'BBBBBB',
          },
          elemDefault,
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of MonthlyPayment', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a MonthlyPayment', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a MonthlyPayment', async () => {
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
