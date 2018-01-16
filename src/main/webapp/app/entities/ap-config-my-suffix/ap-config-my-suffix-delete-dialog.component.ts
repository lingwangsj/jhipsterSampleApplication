import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { APConfigMySuffix } from './ap-config-my-suffix.model';
import { APConfigMySuffixPopupService } from './ap-config-my-suffix-popup.service';
import { APConfigMySuffixService } from './ap-config-my-suffix.service';

@Component({
    selector: 'jhi-ap-config-my-suffix-delete-dialog',
    templateUrl: './ap-config-my-suffix-delete-dialog.component.html'
})
export class APConfigMySuffixDeleteDialogComponent {

    aPConfig: APConfigMySuffix;

    constructor(
        private aPConfigService: APConfigMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.aPConfigService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'aPConfigListModification',
                content: 'Deleted an aPConfig'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ap-config-my-suffix-delete-popup',
    template: ''
})
export class APConfigMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private aPConfigPopupService: APConfigMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.aPConfigPopupService
                .open(APConfigMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
