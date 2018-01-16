import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TenantMySuffixComponent } from './tenant-my-suffix.component';
import { TenantMySuffixDetailComponent } from './tenant-my-suffix-detail.component';
import { TenantMySuffixPopupComponent } from './tenant-my-suffix-dialog.component';
import { TenantMySuffixDeletePopupComponent } from './tenant-my-suffix-delete-dialog.component';

export const tenantRoute: Routes = [
    {
        path: 'tenant-my-suffix',
        component: TenantMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenants'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tenant-my-suffix/:id',
        component: TenantMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenants'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tenantPopupRoute: Routes = [
    {
        path: 'tenant-my-suffix-new',
        component: TenantMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenants'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tenant-my-suffix/:id/edit',
        component: TenantMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenants'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tenant-my-suffix/:id/delete',
        component: TenantMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tenants'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
