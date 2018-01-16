/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TagMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/tag-my-suffix/tag-my-suffix-dialog.component';
import { TagMySuffixService } from '../../../../../../main/webapp/app/entities/tag-my-suffix/tag-my-suffix.service';
import { TagMySuffix } from '../../../../../../main/webapp/app/entities/tag-my-suffix/tag-my-suffix.model';
import { TenantMySuffixService } from '../../../../../../main/webapp/app/entities/tenant-my-suffix';
import { APMySuffixService } from '../../../../../../main/webapp/app/entities/ap-my-suffix';
import { NetworkMySuffixService } from '../../../../../../main/webapp/app/entities/network-my-suffix';
import { APConfigMySuffixService } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix';

describe('Component Tests', () => {

    describe('TagMySuffix Management Dialog Component', () => {
        let comp: TagMySuffixDialogComponent;
        let fixture: ComponentFixture<TagMySuffixDialogComponent>;
        let service: TagMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TagMySuffixDialogComponent],
                providers: [
                    TenantMySuffixService,
                    APMySuffixService,
                    NetworkMySuffixService,
                    APConfigMySuffixService,
                    TagMySuffixService
                ]
            })
            .overrideTemplate(TagMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TagMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TagMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TagMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.tag = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'tagListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TagMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.tag = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'tagListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
