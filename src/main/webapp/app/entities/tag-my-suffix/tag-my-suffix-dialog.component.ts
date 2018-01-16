import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TagMySuffix } from './tag-my-suffix.model';
import { TagMySuffixPopupService } from './tag-my-suffix-popup.service';
import { TagMySuffixService } from './tag-my-suffix.service';
import { TenantMySuffix, TenantMySuffixService } from '../tenant-my-suffix';
import { APMySuffix, APMySuffixService } from '../ap-my-suffix';
import { NetworkMySuffix, NetworkMySuffixService } from '../network-my-suffix';
import { APConfigMySuffix, APConfigMySuffixService } from '../ap-config-my-suffix';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-tag-my-suffix-dialog',
    templateUrl: './tag-my-suffix-dialog.component.html'
})
export class TagMySuffixDialogComponent implements OnInit {

    tag: TagMySuffix;
    isSaving: boolean;

    tenants: TenantMySuffix[];

    aps: APMySuffix[];

    networks: NetworkMySuffix[];

    apconfigs: APConfigMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private tagService: TagMySuffixService,
        private tenantService: TenantMySuffixService,
        private aPService: APMySuffixService,
        private networkService: NetworkMySuffixService,
        private aPConfigService: APConfigMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.tenantService.query()
            .subscribe((res: ResponseWrapper) => { this.tenants = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.aPService.query()
            .subscribe((res: ResponseWrapper) => { this.aps = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.networkService.query()
            .subscribe((res: ResponseWrapper) => { this.networks = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.aPConfigService.query()
            .subscribe((res: ResponseWrapper) => { this.apconfigs = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.tag.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tagService.update(this.tag));
        } else {
            this.subscribeToSaveResponse(
                this.tagService.create(this.tag));
        }
    }

    private subscribeToSaveResponse(result: Observable<TagMySuffix>) {
        result.subscribe((res: TagMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: TagMySuffix) {
        this.eventManager.broadcast({ name: 'tagListModification', content: 'OK'});
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

    trackAPById(index: number, item: APMySuffix) {
        return item.id;
    }

    trackNetworkById(index: number, item: NetworkMySuffix) {
        return item.id;
    }

    trackAPConfigById(index: number, item: APConfigMySuffix) {
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
    selector: 'jhi-tag-my-suffix-popup',
    template: ''
})
export class TagMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tagPopupService: TagMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tagPopupService
                    .open(TagMySuffixDialogComponent as Component, params['id']);
            } else {
                this.tagPopupService
                    .open(TagMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
