/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { APMySuffixComponent } from '../../../../../../main/webapp/app/entities/ap-my-suffix/ap-my-suffix.component';
import { APMySuffixService } from '../../../../../../main/webapp/app/entities/ap-my-suffix/ap-my-suffix.service';
import { APMySuffix } from '../../../../../../main/webapp/app/entities/ap-my-suffix/ap-my-suffix.model';

describe('Component Tests', () => {

    describe('APMySuffix Management Component', () => {
        let comp: APMySuffixComponent;
        let fixture: ComponentFixture<APMySuffixComponent>;
        let service: APMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [APMySuffixComponent],
                providers: [
                    APMySuffixService
                ]
            })
            .overrideTemplate(APMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(APMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(APMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new APMySuffix(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.aPS[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
