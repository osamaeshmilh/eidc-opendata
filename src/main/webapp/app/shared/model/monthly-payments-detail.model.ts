import { type IMonthlyPayment } from '@/shared/model/monthly-payment.model';

export interface IMonthlyPaymentsDetail {
  id?: number;
  beneficiary?: string | null;
  payment?: number | null;
  dateOfPayment?: Date | null;
  description?: string | null;
  notes?: string | null;
  monthlyPayment?: IMonthlyPayment | null;
}

export class MonthlyPaymentsDetail implements IMonthlyPaymentsDetail {
  constructor(
    public id?: number,
    public beneficiary?: string | null,
    public payment?: number | null,
    public dateOfPayment?: Date | null,
    public description?: string | null,
    public notes?: string | null,
    public monthlyPayment?: IMonthlyPayment | null,
  ) {}
}
