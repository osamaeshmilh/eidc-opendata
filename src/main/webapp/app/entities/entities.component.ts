import { defineComponent, provide } from 'vue';

import AttachmentService from './attachment/attachment.service';
import SectorService from './sector/sector.service';
import CenterService from './center/center.service';
import JehaService from './jeha/jeha.service';
import ClauseService from './clause/clause.service';
import MonthlyPaymentService from './monthly-payment/monthly-payment.service';
import MonthlyRevenueService from './monthly-revenue/monthly-revenue.service';
import MonthlyPaymentsDetailService from './monthly-payments-detail/monthly-payments-detail.service';
import UserService from '@/entities/user/user.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

export default defineComponent({
  compatConfig: { MODE: 3 },
  name: 'Entities',
  setup() {
    provide('userService', () => new UserService());
    provide('attachmentService', () => new AttachmentService());
    provide('sectorService', () => new SectorService());
    provide('centerService', () => new CenterService());
    provide('jehaService', () => new JehaService());
    provide('clauseService', () => new ClauseService());
    provide('monthlyPaymentService', () => new MonthlyPaymentService());
    provide('monthlyRevenueService', () => new MonthlyRevenueService());
    provide('monthlyPaymentsDetailService', () => new MonthlyPaymentsDetailService());
    // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
  },
});
