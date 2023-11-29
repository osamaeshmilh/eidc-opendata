import { type Reference } from '@/shared/model/enumerations/reference.model';
export interface IAttachment {
  id?: number;
  name?: string | null;
  fileContentType?: string | null;
  file?: string | null;
  fileUrl?: string | null;
  referenceId?: number | null;
  reference?: keyof typeof Reference | null;
}

export class Attachment implements IAttachment {
  constructor(
    public id?: number,
    public name?: string | null,
    public fileContentType?: string | null,
    public file?: string | null,
    public fileUrl?: string | null,
    public referenceId?: number | null,
    public reference?: keyof typeof Reference | null,
  ) {}
}
