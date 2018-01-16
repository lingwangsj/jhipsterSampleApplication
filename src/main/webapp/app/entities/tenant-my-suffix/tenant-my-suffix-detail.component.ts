import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TenantMySuffix } from './tenant-my-suffix.model';
import { TenantMySuffixService } from './tenant-my-suffix.service';

@Component({
    selector: 'jhi-tenant-my-suffix-detail',
    templateUrl: './tenant-my-suffix-detail.component.html'
})
export class TenantMySuffixDetailComponent implements OnInit, OnDestroy {

    tenant: TenantMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tenantService: TenantMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTenants();
    }

    load(id) {
        this.tenantService.find(id).subscribe((tenant) => {
            this.tenant = tenant;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTenants() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tenantListModification',
            (response) => this.load(this.tenant.id)
        );
    }
}
