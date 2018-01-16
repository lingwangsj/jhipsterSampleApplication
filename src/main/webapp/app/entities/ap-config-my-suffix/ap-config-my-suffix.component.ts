import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { APConfigMySuffix } from './ap-config-my-suffix.model';
import { APConfigMySuffixService } from './ap-config-my-suffix.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-ap-config-my-suffix',
    templateUrl: './ap-config-my-suffix.component.html'
})
export class APConfigMySuffixComponent implements OnInit, OnDestroy {
aPConfigs: APConfigMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private aPConfigService: APConfigMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.aPConfigService.query().subscribe(
            (res: ResponseWrapper) => {
                this.aPConfigs = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAPConfigs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: APConfigMySuffix) {
        return item.id;
    }
    registerChangeInAPConfigs() {
        this.eventSubscriber = this.eventManager.subscribe('aPConfigListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
