/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { VenueMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/venue-my-suffix/venue-my-suffix-detail.component';
import { VenueMySuffixService } from '../../../../../../main/webapp/app/entities/venue-my-suffix/venue-my-suffix.service';
import { VenueMySuffix } from '../../../../../../main/webapp/app/entities/venue-my-suffix/venue-my-suffix.model';

describe('Component Tests', () => {

    describe('VenueMySuffix Management Detail Component', () => {
        let comp: VenueMySuffixDetailComponent;
        let fixture: ComponentFixture<VenueMySuffixDetailComponent>;
        let service: VenueMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [VenueMySuffixDetailComponent],
                providers: [
                    VenueMySuffixService
                ]
            })
            .overrideTemplate(VenueMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VenueMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VenueMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new VenueMySuffix(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.venue).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
