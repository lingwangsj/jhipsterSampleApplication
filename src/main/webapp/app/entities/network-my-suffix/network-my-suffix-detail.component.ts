import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { NetworkMySuffix } from './network-my-suffix.model';
import { NetworkMySuffixService } from './network-my-suffix.service';

@Component({
    selector: 'jhi-network-my-suffix-detail',
    templateUrl: './network-my-suffix-detail.component.html'
})
export class NetworkMySuffixDetailComponent implements OnInit, OnDestroy {

    network: NetworkMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private networkService: NetworkMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInNetworks();
    }

    load(id) {
        this.networkService.find(id).subscribe((network) => {
            this.network = network;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInNetworks() {
        this.eventSubscriber = this.eventManager.subscribe(
            'networkListModification',
            (response) => this.load(this.network.id)
        );
    }
}
