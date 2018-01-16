import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { NetworkMySuffix } from './network-my-suffix.model';
import { NetworkMySuffixPopupService } from './network-my-suffix-popup.service';
import { NetworkMySuffixService } from './network-my-suffix.service';
import { TagMySuffix, TagMySuffixService } from '../tag-my-suffix';
import { TenantMySuffix, TenantMySuffixService } from '../tenant-my-suffix';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-network-my-suffix-dialog',
    templateUrl: './network-my-suffix-dialog.component.html'
})
export class NetworkMySuffixDialogComponent implements OnInit {

    network: NetworkMySuffix;
    isSaving: boolean;

    tags: TagMySuffix[];

    tenants: TenantMySuffix[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private networkService: NetworkMySuffixService,
        private tagService: TagMySuffixService,
        private tenantService: TenantMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.tagService.query()
            .subscribe((res: ResponseWrapper) => { this.tags = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.tenantService.query()
            .subscribe((res: ResponseWrapper) => { this.tenants = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.network.id !== undefined) {
            this.subscribeToSaveResponse(
                this.networkService.update(this.network));
        } else {
            this.subscribeToSaveResponse(
                this.networkService.create(this.network));
        }
    }

    private subscribeToSaveResponse(result: Observable<NetworkMySuffix>) {
        result.subscribe((res: NetworkMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: NetworkMySuffix) {
        this.eventManager.broadcast({ name: 'networkListModification', content: 'OK'});
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

    trackTenantById(index: number, item: TenantMySuffix) {
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
    selector: 'jhi-network-my-suffix-popup',
    template: ''
})
export class NetworkMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private networkPopupService: NetworkMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.networkPopupService
                    .open(NetworkMySuffixDialogComponent as Component, params['id']);
            } else {
                this.networkPopupService
                    .open(NetworkMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
