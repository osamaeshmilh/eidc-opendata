import { type Chapter } from '@/shared/model/enumerations/chapter.model';
export interface IClause {
  id?: number;
  name?: string | null;
  chapter?: keyof typeof Chapter | null;
}

export class Clause implements IClause {
  constructor(
    public id?: number,
    public name?: string | null,
    public chapter?: keyof typeof Chapter | null,
  ) {}
}
