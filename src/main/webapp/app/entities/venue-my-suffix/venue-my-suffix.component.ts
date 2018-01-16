import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VenueMySuffix } from './venue-my-suffix.model';
import { VenueMySuffixService } from './venue-my-suffix.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-venue-my-suffix',
    templateUrl: './venue-my-suffix.component.html'
})
export class VenueMySuffixComponent implements OnInit, OnDestroy {
venues: VenueMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private venueService: VenueMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.venueService.query().subscribe(
            (res: ResponseWrapper) => {
                this.venues = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInVenues();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: VenueMySuffix) {
        return item.id;
    }
    registerChangeInVenues() {
        this.eventSubscriber = this.eventManager.subscribe('venueListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
