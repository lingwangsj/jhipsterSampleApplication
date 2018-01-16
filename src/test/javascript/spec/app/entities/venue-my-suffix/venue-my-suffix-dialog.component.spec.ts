/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { VenueMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/venue-my-suffix/venue-my-suffix-dialog.component';
import { VenueMySuffixService } from '../../../../../../main/webapp/app/entities/venue-my-suffix/venue-my-suffix.service';
import { VenueMySuffix } from '../../../../../../main/webapp/app/entities/venue-my-suffix/venue-my-suffix.model';
import { TenantMySuffixService } from '../../../../../../main/webapp/app/entities/tenant-my-suffix';

describe('Component Tests', () => {

    describe('VenueMySuffix Management Dialog Component', () => {
        let comp: VenueMySuffixDialogComponent;
        let fixture: ComponentFixture<VenueMySuffixDialogComponent>;
        let service: VenueMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [VenueMySuffixDialogComponent],
                providers: [
                    TenantMySuffixService,
                    VenueMySuffixService
                ]
            })
            .overrideTemplate(VenueMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VenueMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VenueMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new VenueMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.venue = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'venueListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new VenueMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.venue = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'venueListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
