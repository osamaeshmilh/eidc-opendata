import { type IClause } from '@/shared/model/clause.model';
import { type IJeha } from '@/shared/model/jeha.model';

import { type Chapter } from '@/shared/model/enumerations/chapter.model';
export interface IMonthlyRevenue {
  id?: number;
  chapter?: keyof typeof Chapter | null;
  month?: number | null;
  year?: number | null;
  revenueCollected?: number | null;
  annualEstablishedBudget?: number | null;
  actualRevenueBeginningToEndLastMonth?: number | null;
  actualRevenueDuringMonth?: number | null;
  totalRevenueUntilEndMonth?: number | null;
  existingConnections?: string | null;
  totalActualRevenue?: number | null;
  balanceOfClause?: number | null;
  comments?: string | null;
  clause?: IClause | null;
  jeha?: IJeha | null;
}

export class MonthlyRevenue implements IMonthlyRevenue {
  constructor(
    public id?: number,
    public chapter?: keyof typeof Chapter | null,
    public month?: number | null,
    public year?: number | null,
    public revenueCollected?: number | null,
    public annualEstablishedBudget?: number | null,
    public actualRevenueBeginningToEndLastMonth?: number | null,
    public actualRevenueDuringMonth?: number | null,
    public totalRevenueUntilEndMonth?: number | null,
    public existingConnections?: string | null,
    public totalActualRevenue?: number | null,
    public balanceOfClause?: number | null,
    public comments?: string | null,
    public clause?: IClause | null,
    public jeha?: IJeha | null,
  ) {}
}
