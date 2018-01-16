import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TagMySuffix } from './tag-my-suffix.model';
import { TagMySuffixService } from './tag-my-suffix.service';
import { Principal, ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-tag-my-suffix',
    templateUrl: './tag-my-suffix.component.html'
})
export class TagMySuffixComponent implements OnInit, OnDestroy {
tags: TagMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tagService: TagMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.tagService.query().subscribe(
            (res: ResponseWrapper) => {
                this.tags = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTags();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TagMySuffix) {
        return item.id;
    }
    registerChangeInTags() {
        this.eventSubscriber = this.eventManager.subscribe('tagListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
