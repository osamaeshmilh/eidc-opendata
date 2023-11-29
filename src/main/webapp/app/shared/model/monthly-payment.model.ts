import { type IClause } from '@/shared/model/clause.model';
import { type IJeha } from '@/shared/model/jeha.model';
import { type IMonthlyPaymentsDetail } from '@/shared/model/monthly-payments-detail.model';

import { type Chapter } from '@/shared/model/enumerations/chapter.model';
export interface IMonthlyPayment {
  id?: number;
  chapter?: keyof typeof Chapter | null;
  month?: number | null;
  year?: number | null;
  isTwelve?: boolean | null;
  annualEstablishedBudget?: number | null;
  actualExpensesBeginningToEndLastMonth?: number | null;
  actualExpensesDuringMonth?: number | null;
  totalexpensesUntilEndMonth?: number | null;
  existingConnections?: string | null;
  totalActualExpenses?: number | null;
  balanceOfClause?: number | null;
  comments?: string | null;
  clause?: IClause | null;
  jeha?: IJeha | null;
  monthlyPaymentsDetails?: IMonthlyPaymentsDetail[] | null;
}

export class MonthlyPayment implements IMonthlyPayment {
  constructor(
    public id?: number,
    public chapter?: keyof typeof Chapter | null,
    public month?: number | null,
    public year?: number | null,
    public isTwelve?: boolean | null,
    public annualEstablishedBudget?: number | null,
    public actualExpensesBeginningToEndLastMonth?: number | null,
    public actualExpensesDuringMonth?: number | null,
    public totalexpensesUntilEndMonth?: number | null,
    public existingConnections?: string | null,
    public totalActualExpenses?: number | null,
    public balanceOfClause?: number | null,
    public comments?: string | null,
    public clause?: IClause | null,
    public jeha?: IJeha | null,
    public monthlyPaymentsDetails?: IMonthlyPaymentsDetail[] | null,
  ) {
    this.isTwelve = this.isTwelve ?? false;
  }
}
