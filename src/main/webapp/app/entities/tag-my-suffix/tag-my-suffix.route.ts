import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TagMySuffixComponent } from './tag-my-suffix.component';
import { TagMySuffixDetailComponent } from './tag-my-suffix-detail.component';
import { TagMySuffixPopupComponent } from './tag-my-suffix-dialog.component';
import { TagMySuffixDeletePopupComponent } from './tag-my-suffix-delete-dialog.component';

export const tagRoute: Routes = [
    {
        path: 'tag-my-suffix',
        component: TagMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tags'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tag-my-suffix/:id',
        component: TagMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tags'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tagPopupRoute: Routes = [
    {
        path: 'tag-my-suffix-new',
        component: TagMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tags'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tag-my-suffix/:id/edit',
        component: TagMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tags'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tag-my-suffix/:id/delete',
        component: TagMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tags'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
