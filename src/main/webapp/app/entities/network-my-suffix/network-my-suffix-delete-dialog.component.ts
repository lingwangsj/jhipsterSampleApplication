import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { NetworkMySuffix } from './network-my-suffix.model';
import { NetworkMySuffixPopupService } from './network-my-suffix-popup.service';
import { NetworkMySuffixService } from './network-my-suffix.service';

@Component({
    selector: 'jhi-network-my-suffix-delete-dialog',
    templateUrl: './network-my-suffix-delete-dialog.component.html'
})
export class NetworkMySuffixDeleteDialogComponent {

    network: NetworkMySuffix;

    constructor(
        private networkService: NetworkMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.networkService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'networkListModification',
                content: 'Deleted an network'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-network-my-suffix-delete-popup',
    template: ''
})
export class NetworkMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private networkPopupService: NetworkMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.networkPopupService
                .open(NetworkMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
