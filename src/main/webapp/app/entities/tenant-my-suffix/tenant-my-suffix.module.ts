import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    TenantMySuffixService,
    TenantMySuffixPopupService,
    TenantMySuffixComponent,
    TenantMySuffixDetailComponent,
    TenantMySuffixDialogComponent,
    TenantMySuffixPopupComponent,
    TenantMySuffixDeletePopupComponent,
    TenantMySuffixDeleteDialogComponent,
    tenantRoute,
    tenantPopupRoute,
} from './';

const ENTITY_STATES = [
    ...tenantRoute,
    ...tenantPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TenantMySuffixComponent,
        TenantMySuffixDetailComponent,
        TenantMySuffixDialogComponent,
        TenantMySuffixDeleteDialogComponent,
        TenantMySuffixPopupComponent,
        TenantMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        TenantMySuffixComponent,
        TenantMySuffixDialogComponent,
        TenantMySuffixPopupComponent,
        TenantMySuffixDeleteDialogComponent,
        TenantMySuffixDeletePopupComponent,
    ],
    providers: [
        TenantMySuffixService,
        TenantMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationTenantMySuffixModule {}
