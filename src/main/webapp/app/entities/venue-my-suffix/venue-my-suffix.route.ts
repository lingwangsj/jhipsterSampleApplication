import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { VenueMySuffixComponent } from './venue-my-suffix.component';
import { VenueMySuffixDetailComponent } from './venue-my-suffix-detail.component';
import { VenueMySuffixPopupComponent } from './venue-my-suffix-dialog.component';
import { VenueMySuffixDeletePopupComponent } from './venue-my-suffix-delete-dialog.component';

export const venueRoute: Routes = [
    {
        path: 'venue-my-suffix',
        component: VenueMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Venues'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'venue-my-suffix/:id',
        component: VenueMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Venues'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const venuePopupRoute: Routes = [
    {
        path: 'venue-my-suffix-new',
        component: VenueMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Venues'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'venue-my-suffix/:id/edit',
        component: VenueMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Venues'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'venue-my-suffix/:id/delete',
        component: VenueMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Venues'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
