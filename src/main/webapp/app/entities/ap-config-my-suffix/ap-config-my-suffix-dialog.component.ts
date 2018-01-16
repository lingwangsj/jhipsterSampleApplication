import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { APConfigMySuffix } from './ap-config-my-suffix.model';
import { APConfigMySuffixPopupService } from './ap-config-my-suffix-popup.service';
import { APConfigMySuffixService } from './ap-config-my-suffix.service';
import { TagMySuffix, TagMySuffixService } from '../tag-my-suffix';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-ap-config-my-suffix-dialog',
    templateUrl: './ap-config-my-suffix-dialog.component.html'
})
export class APConfigMySuffixDialogComponent implements OnInit {

    aPConfig: APConfigMySuffix;
    isSaving: boolean;

    tags: TagMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private aPConfigService: APConfigMySuffixService,
        private tagService: TagMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.tagService.query()
            .subscribe((res: ResponseWrapper) => { this.tags = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.aPConfig.id !== undefined) {
            this.subscribeToSaveResponse(
                this.aPConfigService.update(this.aPConfig));
        } else {
            this.subscribeToSaveResponse(
                this.aPConfigService.create(this.aPConfig));
        }
    }

    private subscribeToSaveResponse(result: Observable<APConfigMySuffix>) {
        result.subscribe((res: APConfigMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: APConfigMySuffix) {
        this.eventManager.broadcast({ name: 'aPConfigListModification', content: 'OK'});
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
    selector: 'jhi-ap-config-my-suffix-popup',
    template: ''
})
export class APConfigMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private aPConfigPopupService: APConfigMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.aPConfigPopupService
                    .open(APConfigMySuffixDialogComponent as Component, params['id']);
            } else {
                this.aPConfigPopupService
                    .open(APConfigMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
