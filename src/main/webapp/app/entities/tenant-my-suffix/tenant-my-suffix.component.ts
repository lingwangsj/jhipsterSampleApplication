import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TenantMySuffix } from './tenant-my-suffix.model';
import { TenantMySuffixService } from './tenant-my-suffix.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-tenant-my-suffix',
    templateUrl: './tenant-my-suffix.component.html'
})
export class TenantMySuffixComponent implements OnInit, OnDestroy {
tenants: TenantMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tenantService: TenantMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.tenantService.query().subscribe(
            (res: ResponseWrapper) => {
                this.tenants = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTenants();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TenantMySuffix) {
        return item.id;
    }
    registerChangeInTenants() {
        this.eventSubscriber = this.eventManager.subscribe('tenantListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
