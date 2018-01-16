import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { VenueMySuffix } from './venue-my-suffix.model';
import { VenueMySuffixPopupService } from './venue-my-suffix-popup.service';
import { VenueMySuffixService } from './venue-my-suffix.service';
import { TenantMySuffix, TenantMySuffixService } from '../tenant-my-suffix';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-venue-my-suffix-dialog',
    templateUrl: './venue-my-suffix-dialog.component.html'
})
export class VenueMySuffixDialogComponent implements OnInit {

    venue: VenueMySuffix;
    isSaving: boolean;

    tenants: TenantMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private venueService: VenueMySuffixService,
        private tenantService: TenantMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.tenantService.query()
            .subscribe((res: ResponseWrapper) => { this.tenants = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.venue.id !== undefined) {
            this.subscribeToSaveResponse(
                this.venueService.update(this.venue));
        } else {
            this.subscribeToSaveResponse(
                this.venueService.create(this.venue));
        }
    }

    private subscribeToSaveResponse(result: Observable<VenueMySuffix>) {
        result.subscribe((res: VenueMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: VenueMySuffix) {
        this.eventManager.broadcast({ name: 'venueListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTenantById(index: number, item: TenantMySuffix) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-venue-my-suffix-popup',
    template: ''
})
export class VenueMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private venuePopupService: VenueMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.venuePopupService
                    .open(VenueMySuffixDialogComponent as Component, params['id']);
            } else {
                this.venuePopupService
                    .open(VenueMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
