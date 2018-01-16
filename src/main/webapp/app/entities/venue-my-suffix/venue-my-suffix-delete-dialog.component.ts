import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { VenueMySuffix } from './venue-my-suffix.model';
import { VenueMySuffixPopupService } from './venue-my-suffix-popup.service';
import { VenueMySuffixService } from './venue-my-suffix.service';

@Component({
    selector: 'jhi-venue-my-suffix-delete-dialog',
    templateUrl: './venue-my-suffix-delete-dialog.component.html'
})
export class VenueMySuffixDeleteDialogComponent {

    venue: VenueMySuffix;

    constructor(
        private venueService: VenueMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.venueService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'venueListModification',
                content: 'Deleted an venue'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-venue-my-suffix-delete-popup',
    template: ''
})
export class VenueMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private venuePopupService: VenueMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.venuePopupService
                .open(VenueMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
