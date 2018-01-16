import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TenantMySuffix } from './tenant-my-suffix.model';
import { TenantMySuffixPopupService } from './tenant-my-suffix-popup.service';
import { TenantMySuffixService } from './tenant-my-suffix.service';

@Component({
    selector: 'jhi-tenant-my-suffix-dialog',
    templateUrl: './tenant-my-suffix-dialog.component.html'
})
export class TenantMySuffixDialogComponent implements OnInit {

    tenant: TenantMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private tenantService: TenantMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.tenant.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tenantService.update(this.tenant));
        } else {
            this.subscribeToSaveResponse(
                this.tenantService.create(this.tenant));
        }
    }

    private subscribeToSaveResponse(result: Observable<TenantMySuffix>) {
        result.subscribe((res: TenantMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: TenantMySuffix) {
        this.eventManager.broadcast({ name: 'tenantListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-tenant-my-suffix-popup',
    template: ''
})
export class TenantMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tenantPopupService: TenantMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tenantPopupService
                    .open(TenantMySuffixDialogComponent as Component, params['id']);
            } else {
                this.tenantPopupService
                    .open(TenantMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
