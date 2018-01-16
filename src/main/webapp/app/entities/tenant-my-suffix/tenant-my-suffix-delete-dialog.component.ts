import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TenantMySuffix } from './tenant-my-suffix.model';
import { TenantMySuffixPopupService } from './tenant-my-suffix-popup.service';
import { TenantMySuffixService } from './tenant-my-suffix.service';

@Component({
    selector: 'jhi-tenant-my-suffix-delete-dialog',
    templateUrl: './tenant-my-suffix-delete-dialog.component.html'
})
export class TenantMySuffixDeleteDialogComponent {

    tenant: TenantMySuffix;

    constructor(
        private tenantService: TenantMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tenantService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tenantListModification',
                content: 'Deleted an tenant'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tenant-my-suffix-delete-popup',
    template: ''
})
export class TenantMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tenantPopupService: TenantMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tenantPopupService
                .open(TenantMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
