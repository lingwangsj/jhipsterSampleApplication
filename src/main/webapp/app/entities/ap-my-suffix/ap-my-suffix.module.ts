import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    APMySuffixService,
    APMySuffixPopupService,
    APMySuffixComponent,
    APMySuffixDetailComponent,
    APMySuffixDialogComponent,
    APMySuffixPopupComponent,
    APMySuffixDeletePopupComponent,
    APMySuffixDeleteDialogComponent,
    aPRoute,
    aPPopupRoute,
} from './';

const ENTITY_STATES = [
    ...aPRoute,
    ...aPPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        APMySuffixComponent,
        APMySuffixDetailComponent,
        APMySuffixDialogComponent,
        APMySuffixDeleteDialogComponent,
        APMySuffixPopupComponent,
        APMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        APMySuffixComponent,
        APMySuffixDialogComponent,
        APMySuffixPopupComponent,
        APMySuffixDeleteDialogComponent,
        APMySuffixDeletePopupComponent,
    ],
    providers: [
        APMySuffixService,
        APMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationAPMySuffixModule {}
