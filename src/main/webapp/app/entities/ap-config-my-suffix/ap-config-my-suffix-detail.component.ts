import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { APConfigMySuffix } from './ap-config-my-suffix.model';
import { APConfigMySuffixService } from './ap-config-my-suffix.service';

@Component({
    selector: 'jhi-ap-config-my-suffix-detail',
    templateUrl: './ap-config-my-suffix-detail.component.html'
})
export class APConfigMySuffixDetailComponent implements OnInit, OnDestroy {

    aPConfig: APConfigMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private aPConfigService: APConfigMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAPConfigs();
    }

    load(id) {
        this.aPConfigService.find(id).subscribe((aPConfig) => {
            this.aPConfig = aPConfig;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAPConfigs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'aPConfigListModification',
            (response) => this.load(this.aPConfig.id)
        );
    }
}
