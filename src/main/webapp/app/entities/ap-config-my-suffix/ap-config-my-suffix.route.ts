import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { APConfigMySuffixComponent } from './ap-config-my-suffix.component';
import { APConfigMySuffixDetailComponent } from './ap-config-my-suffix-detail.component';
import { APConfigMySuffixPopupComponent } from './ap-config-my-suffix-dialog.component';
import { APConfigMySuffixDeletePopupComponent } from './ap-config-my-suffix-delete-dialog.component';

export const aPConfigRoute: Routes = [
    {
        path: 'ap-config-my-suffix',
        component: APConfigMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'APConfigs'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ap-config-my-suffix/:id',
        component: APConfigMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'APConfigs'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const aPConfigPopupRoute: Routes = [
    {
        path: 'ap-config-my-suffix-new',
        component: APConfigMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'APConfigs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ap-config-my-suffix/:id/edit',
        component: APConfigMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'APConfigs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ap-config-my-suffix/:id/delete',
        component: APConfigMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'APConfigs'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
