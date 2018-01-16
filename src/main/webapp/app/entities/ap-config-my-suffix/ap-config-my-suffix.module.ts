import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    APConfigMySuffixService,
    APConfigMySuffixPopupService,
    APConfigMySuffixComponent,
    APConfigMySuffixDetailComponent,
    APConfigMySuffixDialogComponent,
    APConfigMySuffixPopupComponent,
    APConfigMySuffixDeletePopupComponent,
    APConfigMySuffixDeleteDialogComponent,
    aPConfigRoute,
    aPConfigPopupRoute,
} from './';

const ENTITY_STATES = [
    ...aPConfigRoute,
    ...aPConfigPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        APConfigMySuffixComponent,
        APConfigMySuffixDetailComponent,
        APConfigMySuffixDialogComponent,
        APConfigMySuffixDeleteDialogComponent,
        APConfigMySuffixPopupComponent,
        APConfigMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        APConfigMySuffixComponent,
        APConfigMySuffixDialogComponent,
        APConfigMySuffixPopupComponent,
        APConfigMySuffixDeleteDialogComponent,
        APConfigMySuffixDeletePopupComponent,
    ],
    providers: [
        APConfigMySuffixService,
        APConfigMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAPConfigMySuffixModule {}
