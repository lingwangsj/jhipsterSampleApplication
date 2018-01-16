import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TagMySuffix } from './tag-my-suffix.model';
import { TagMySuffixPopupService } from './tag-my-suffix-popup.service';
import { TagMySuffixService } from './tag-my-suffix.service';

@Component({
    selector: 'jhi-tag-my-suffix-delete-dialog',
    templateUrl: './tag-my-suffix-delete-dialog.component.html'
})
export class TagMySuffixDeleteDialogComponent {

    tag: TagMySuffix;

    constructor(
        private tagService: TagMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tagService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tagListModification',
                content: 'Deleted an tag'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tag-my-suffix-delete-popup',
    template: ''
})
export class TagMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tagPopupService: TagMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tagPopupService
                .open(TagMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
