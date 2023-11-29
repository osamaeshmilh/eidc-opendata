import { type ISector } from '@/shared/model/sector.model';
import { type IUser } from '@/shared/model/user.model';

export interface ICenter {
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
  sector?: ISector | null;
  user?: IUser | null;
}

export class Center implements ICenter {
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
    public sector?: ISector | null,
    public user?: IUser | null,
  ) {}
}
