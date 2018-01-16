import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TagMySuffix } from './tag-my-suffix.model';
import { TagMySuffixService } from './tag-my-suffix.service';

@Component({
    selector: 'jhi-tag-my-suffix-detail',
    templateUrl: './tag-my-suffix-detail.component.html'
})
export class TagMySuffixDetailComponent implements OnInit, OnDestroy {

    tag: TagMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tagService: TagMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTags();
    }

    load(id) {
        this.tagService.find(id).subscribe((tag) => {
            this.tag = tag;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTags() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tagListModification',
            (response) => this.load(this.tag.id)
        );
    }
}
