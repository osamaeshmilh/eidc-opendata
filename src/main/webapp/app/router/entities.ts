import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

const Attachment = () => import('@/entities/attachment/attachment.vue');
const AttachmentUpdate = () => import('@/entities/attachment/attachment-update.vue');
const AttachmentDetails = () => import('@/entities/attachment/attachment-details.vue');

const Sector = () => import('@/entities/sector/sector.vue');
const SectorUpdate = () => import('@/entities/sector/sector-update.vue');
const SectorDetails = () => import('@/entities/sector/sector-details.vue');

const Center = () => import('@/entities/center/center.vue');
const CenterUpdate = () => import('@/entities/center/center-update.vue');
const CenterDetails = () => import('@/entities/center/center-details.vue');

const Jeha = () => import('@/entities/jeha/jeha.vue');
const JehaUpdate = () => import('@/entities/jeha/jeha-update.vue');
const JehaDetails = () => import('@/entities/jeha/jeha-details.vue');

const Clause = () => import('@/entities/clause/clause.vue');
const ClauseUpdate = () => import('@/entities/clause/clause-update.vue');
const ClauseDetails = () => import('@/entities/clause/clause-details.vue');

const MonthlyPayment = () => import('@/entities/monthly-payment/monthly-payment.vue');
const MonthlyPaymentUpdate = () => import('@/entities/monthly-payment/monthly-payment-update.vue');
const MonthlyPaymentDetails = () => import('@/entities/monthly-payment/monthly-payment-details.vue');

const MonthlyRevenue = () => import('@/entities/monthly-revenue/monthly-revenue.vue');
const MonthlyRevenueUpdate = () => import('@/entities/monthly-revenue/monthly-revenue-update.vue');
const MonthlyRevenueDetails = () => import('@/entities/monthly-revenue/monthly-revenue-details.vue');

const MonthlyPaymentsDetail = () => import('@/entities/monthly-payments-detail/monthly-payments-detail.vue');
const MonthlyPaymentsDetailUpdate = () => import('@/entities/monthly-payments-detail/monthly-payments-detail-update.vue');
const MonthlyPaymentsDetailDetails = () => import('@/entities/monthly-payments-detail/monthly-payments-detail-details.vue');

// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'attachment',
      name: 'Attachment',
      component: Attachment,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'attachment/new',
      name: 'AttachmentCreate',
      component: AttachmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'attachment/:attachmentId/edit',
      name: 'AttachmentEdit',
      component: AttachmentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'attachment/:attachmentId/view',
      name: 'AttachmentView',
      component: AttachmentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sector',
      name: 'Sector',
      component: Sector,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sector/new',
      name: 'SectorCreate',
      component: SectorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sector/:sectorId/edit',
      name: 'SectorEdit',
      component: SectorUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'sector/:sectorId/view',
      name: 'SectorView',
      component: SectorDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'center',
      name: 'Center',
      component: Center,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'center/new',
      name: 'CenterCreate',
      component: CenterUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'center/:centerId/edit',
      name: 'CenterEdit',
      component: CenterUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'center/:centerId/view',
      name: 'CenterView',
      component: CenterDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'jeha',
      name: 'Jeha',
      component: Jeha,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'jeha/new',
      name: 'JehaCreate',
      component: JehaUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'jeha/:jehaId/edit',
      name: 'JehaEdit',
      component: JehaUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'jeha/:jehaId/view',
      name: 'JehaView',
      component: JehaDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'clause',
      name: 'Clause',
      component: Clause,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'clause/new',
      name: 'ClauseCreate',
      component: ClauseUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'clause/:clauseId/edit',
      name: 'ClauseEdit',
      component: ClauseUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'clause/:clauseId/view',
      name: 'ClauseView',
      component: ClauseDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-payment',
      name: 'MonthlyPayment',
      component: MonthlyPayment,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-payment/new',
      name: 'MonthlyPaymentCreate',
      component: MonthlyPaymentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-payment/:monthlyPaymentId/edit',
      name: 'MonthlyPaymentEdit',
      component: MonthlyPaymentUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-payment/:monthlyPaymentId/view',
      name: 'MonthlyPaymentView',
      component: MonthlyPaymentDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-revenue',
      name: 'MonthlyRevenue',
      component: MonthlyRevenue,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-revenue/new',
      name: 'MonthlyRevenueCreate',
      component: MonthlyRevenueUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-revenue/:monthlyRevenueId/edit',
      name: 'MonthlyRevenueEdit',
      component: MonthlyRevenueUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-revenue/:monthlyRevenueId/view',
      name: 'MonthlyRevenueView',
      component: MonthlyRevenueDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-payments-detail',
      name: 'MonthlyPaymentsDetail',
      component: MonthlyPaymentsDetail,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-payments-detail/new',
      name: 'MonthlyPaymentsDetailCreate',
      component: MonthlyPaymentsDetailUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-payments-detail/:monthlyPaymentsDetailId/edit',
      name: 'MonthlyPaymentsDetailEdit',
      component: MonthlyPaymentsDetailUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'monthly-payments-detail/:monthlyPaymentsDetailId/view',
      name: 'MonthlyPaymentsDetailView',
      component: MonthlyPaymentsDetailDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
