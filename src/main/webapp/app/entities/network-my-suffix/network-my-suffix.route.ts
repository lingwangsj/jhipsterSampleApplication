import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { NetworkMySuffixComponent } from './network-my-suffix.component';
import { NetworkMySuffixDetailComponent } from './network-my-suffix-detail.component';
import { NetworkMySuffixPopupComponent } from './network-my-suffix-dialog.component';
import { NetworkMySuffixDeletePopupComponent } from './network-my-suffix-delete-dialog.component';

export const networkRoute: Routes = [
    {
        path: 'network-my-suffix',
        component: NetworkMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Networks'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'network-my-suffix/:id',
        component: NetworkMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Networks'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const networkPopupRoute: Routes = [
    {
        path: 'network-my-suffix-new',
        component: NetworkMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Networks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'network-my-suffix/:id/edit',
        component: NetworkMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Networks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'network-my-suffix/:id/delete',
        component: NetworkMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Networks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
