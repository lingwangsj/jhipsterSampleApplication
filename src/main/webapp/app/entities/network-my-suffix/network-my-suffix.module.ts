import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    NetworkMySuffixService,
    NetworkMySuffixPopupService,
    NetworkMySuffixComponent,
    NetworkMySuffixDetailComponent,
    NetworkMySuffixDialogComponent,
    NetworkMySuffixPopupComponent,
    NetworkMySuffixDeletePopupComponent,
    NetworkMySuffixDeleteDialogComponent,
    networkRoute,
    networkPopupRoute,
} from './';

const ENTITY_STATES = [
    ...networkRoute,
    ...networkPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        NetworkMySuffixComponent,
        NetworkMySuffixDetailComponent,
        NetworkMySuffixDialogComponent,
        NetworkMySuffixDeleteDialogComponent,
        NetworkMySuffixPopupComponent,
        NetworkMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        NetworkMySuffixComponent,
        NetworkMySuffixDialogComponent,
        NetworkMySuffixPopupComponent,
        NetworkMySuffixDeleteDialogComponent,
        NetworkMySuffixDeletePopupComponent,
    ],
    providers: [
        NetworkMySuffixService,
        NetworkMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationNetworkMySuffixModule {}
