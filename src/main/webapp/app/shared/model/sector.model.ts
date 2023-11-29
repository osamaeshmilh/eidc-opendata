import { type IUser } from '@/shared/model/user.model';
import { type ICenter } from '@/shared/model/center.model';

export interface ISector {
  id?: number;
  name?: string | null;
  sectorImageContentType?: string | null;
  sectorImage?: string | null;
  sectorImageUrl?: string | null;
  user?: IUser | null;
  center?: ICenter | null;
}

export class Sector implements ISector {
  constructor(
    public id?: number,
    public name?: string | null,
    public sectorImageContentType?: string | null,
    public sectorImage?: string | null,
    public sectorImageUrl?: string | null,
    public user?: IUser | null,
    public center?: ICenter | null,
  ) {}
}
