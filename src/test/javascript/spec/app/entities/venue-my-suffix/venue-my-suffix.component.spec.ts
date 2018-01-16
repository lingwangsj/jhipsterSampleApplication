/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { VenueMySuffixComponent } from '../../../../../../main/webapp/app/entities/venue-my-suffix/venue-my-suffix.component';
import { VenueMySuffixService } from '../../../../../../main/webapp/app/entities/venue-my-suffix/venue-my-suffix.service';
import { VenueMySuffix } from '../../../../../../main/webapp/app/entities/venue-my-suffix/venue-my-suffix.model';

describe('Component Tests', () => {

    describe('VenueMySuffix Management Component', () => {
        let comp: VenueMySuffixComponent;
        let fixture: ComponentFixture<VenueMySuffixComponent>;
        let service: VenueMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [VenueMySuffixComponent],
                providers: [
                    VenueMySuffixService
                ]
            })
            .overrideTemplate(VenueMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(VenueMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VenueMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new VenueMySuffix(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.venues[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
