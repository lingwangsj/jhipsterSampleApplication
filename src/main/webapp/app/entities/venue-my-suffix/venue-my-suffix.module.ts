import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    VenueMySuffixService,
    VenueMySuffixPopupService,
    VenueMySuffixComponent,
    VenueMySuffixDetailComponent,
    VenueMySuffixDialogComponent,
    VenueMySuffixPopupComponent,
    VenueMySuffixDeletePopupComponent,
    VenueMySuffixDeleteDialogComponent,
    venueRoute,
    venuePopupRoute,
} from './';

const ENTITY_STATES = [
    ...venueRoute,
    ...venuePopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        VenueMySuffixComponent,
        VenueMySuffixDetailComponent,
        VenueMySuffixDialogComponent,
        VenueMySuffixDeleteDialogComponent,
        VenueMySuffixPopupComponent,
        VenueMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        VenueMySuffixComponent,
        VenueMySuffixDialogComponent,
        VenueMySuffixPopupComponent,
        VenueMySuffixDeleteDialogComponent,
        VenueMySuffixDeletePopupComponent,
    ],
    providers: [
        VenueMySuffixService,
        VenueMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationVenueMySuffixModule {}
