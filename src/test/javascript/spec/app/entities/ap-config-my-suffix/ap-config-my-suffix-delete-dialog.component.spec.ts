/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { APConfigMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix-delete-dialog.component';
import { APConfigMySuffixService } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.service';

describe('Component Tests', () => {

    describe('APConfigMySuffix Management Delete Component', () => {
        let comp: APConfigMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<APConfigMySuffixDeleteDialogComponent>;
        let service: APConfigMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [APConfigMySuffixDeleteDialogComponent],
                providers: [
                    APConfigMySuffixService
                ]
            })
            .overrideTemplate(APConfigMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(APConfigMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(APConfigMySuffixService);
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
