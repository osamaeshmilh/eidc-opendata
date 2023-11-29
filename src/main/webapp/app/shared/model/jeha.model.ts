import { type IUser } from '@/shared/model/user.model';
import { type ISector } from '@/shared/model/sector.model';

export interface IJeha {
  id?: number;
  name?: string | null;
  website?: string | null;
  mobileNo?: string | null;
  facebookPage?: string | null;
  email?: string | null;
  address?: string | null;
  plusCode?: string | null;
  centerImageContentType?: string | null;
  centerImage?: string | null;
  centerImageUrl?: string | null;
  user?: IUser | null;
  sector?: ISector | null;
}

export class Jeha implements IJeha {
  constructor(
    public id?: number,
    public name?: string | null,
    public website?: string | null,
    public mobileNo?: string | null,
    public facebookPage?: string | null,
    public email?: string | null,
    public address?: string | null,
    public plusCode?: string | null,
    public centerImageContentType?: string | null,
    public centerImage?: string | null,
    public centerImageUrl?: string | null,
    public user?: IUser | null,
    public sector?: ISector | null,
  ) {}
}
