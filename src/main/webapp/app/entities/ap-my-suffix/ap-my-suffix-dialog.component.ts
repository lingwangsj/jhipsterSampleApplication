import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { APMySuffix } from './ap-my-suffix.model';
import { APMySuffixPopupService } from './ap-my-suffix-popup.service';
import { APMySuffixService } from './ap-my-suffix.service';
import { TagMySuffix, TagMySuffixService } from '../tag-my-suffix';
import { VenueMySuffix, VenueMySuffixService } from '../venue-my-suffix';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-ap-my-suffix-dialog',
    templateUrl: './ap-my-suffix-dialog.component.html'
})
export class APMySuffixDialogComponent implements OnInit {

    aP: APMySuffix;
    isSaving: boolean;

    tags: TagMySuffix[];

    venues: VenueMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private aPService: APMySuffixService,
        private tagService: TagMySuffixService,
        private venueService: VenueMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.tagService.query()
            .subscribe((res: ResponseWrapper) => { this.tags = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.venueService.query()
            .subscribe((res: ResponseWrapper) => { this.venues = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.aP.id !== undefined) {
            this.subscribeToSaveResponse(
                this.aPService.update(this.aP));
        } else {
            this.subscribeToSaveResponse(
                this.aPService.create(this.aP));
        }
    }

    private subscribeToSaveResponse(result: Observable<APMySuffix>) {
        result.subscribe((res: APMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: APMySuffix) {
        this.eventManager.broadcast({ name: 'aPListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackTagById(index: number, item: TagMySuffix) {
        return item.id;
    }

    trackVenueById(index: number, item: VenueMySuffix) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-ap-my-suffix-popup',
    template: ''
})
export class APMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private aPPopupService: APMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.aPPopupService
                    .open(APMySuffixDialogComponent as Component, params['id']);
            } else {
                this.aPPopupService
                    .open(APMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
