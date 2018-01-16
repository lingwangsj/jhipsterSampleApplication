/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { NetworkMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix-dialog.component';
import { NetworkMySuffixService } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix.service';
import { NetworkMySuffix } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix.model';
import { TagMySuffixService } from '../../../../../../main/webapp/app/entities/tag-my-suffix';
import { TenantMySuffixService } from '../../../../../../main/webapp/app/entities/tenant-my-suffix';

describe('Component Tests', () => {

    describe('NetworkMySuffix Management Dialog Component', () => {
        let comp: NetworkMySuffixDialogComponent;
        let fixture: ComponentFixture<NetworkMySuffixDialogComponent>;
        let service: NetworkMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [NetworkMySuffixDialogComponent],
                providers: [
                    TagMySuffixService,
                    TenantMySuffixService,
                    NetworkMySuffixService
                ]
            })
            .overrideTemplate(NetworkMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NetworkMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NetworkMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new NetworkMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.network = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'networkListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new NetworkMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.network = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'networkListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
