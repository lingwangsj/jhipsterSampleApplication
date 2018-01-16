import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { VenueMySuffix } from './venue-my-suffix.model';
import { VenueMySuffixService } from './venue-my-suffix.service';

@Component({
    selector: 'jhi-venue-my-suffix-detail',
    templateUrl: './venue-my-suffix-detail.component.html'
})
export class VenueMySuffixDetailComponent implements OnInit, OnDestroy {

    venue: VenueMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private venueService: VenueMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInVenues();
    }

    load(id) {
        this.venueService.find(id).subscribe((venue) => {
            this.venue = venue;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInVenues() {
        this.eventSubscriber = this.eventManager.subscribe(
            'venueListModification',
            (response) => this.load(this.venue.id)
        );
    }
}
