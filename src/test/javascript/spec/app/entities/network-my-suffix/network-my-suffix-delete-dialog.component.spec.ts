/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { NetworkMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix-delete-dialog.component';
import { NetworkMySuffixService } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix.service';

describe('Component Tests', () => {

    describe('NetworkMySuffix Management Delete Component', () => {
        let comp: NetworkMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<NetworkMySuffixDeleteDialogComponent>;
        let service: NetworkMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [NetworkMySuffixDeleteDialogComponent],
                providers: [
                    NetworkMySuffixService
                ]
            })
            .overrideTemplate(NetworkMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NetworkMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NetworkMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
