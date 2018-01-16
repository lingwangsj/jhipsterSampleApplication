import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { NetworkMySuffix } from './network-my-suffix.model';
import { NetworkMySuffixService } from './network-my-suffix.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-network-my-suffix',
    templateUrl: './network-my-suffix.component.html'
})
export class NetworkMySuffixComponent implements OnInit, OnDestroy {
networks: NetworkMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private networkService: NetworkMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.networkService.query().subscribe(
            (res: ResponseWrapper) => {
                this.networks = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInNetworks();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: NetworkMySuffix) {
        return item.id;
    }
    registerChangeInNetworks() {
        this.eventSubscriber = this.eventManager.subscribe('networkListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
