/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { APConfigMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix-dialog.component';
import { APConfigMySuffixService } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.service';
import { APConfigMySuffix } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.model';
import { TagMySuffixService } from '../../../../../../main/webapp/app/entities/tag-my-suffix';

describe('Component Tests', () => {

    describe('APConfigMySuffix Management Dialog Component', () => {
        let comp: APConfigMySuffixDialogComponent;
        let fixture: ComponentFixture<APConfigMySuffixDialogComponent>;
        let service: APConfigMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [APConfigMySuffixDialogComponent],
                providers: [
                    TagMySuffixService,
                    APConfigMySuffixService
                ]
            })
            .overrideTemplate(APConfigMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(APConfigMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(APConfigMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new APConfigMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.aPConfig = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'aPConfigListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new APConfigMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.aPConfig = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'aPConfigListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
