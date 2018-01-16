/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { APConfigMySuffixComponent } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.component';
import { APConfigMySuffixService } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.service';
import { APConfigMySuffix } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.model';

describe('Component Tests', () => {

    describe('APConfigMySuffix Management Component', () => {
        let comp: APConfigMySuffixComponent;
        let fixture: ComponentFixture<APConfigMySuffixComponent>;
        let service: APConfigMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [APConfigMySuffixComponent],
                providers: [
                    APConfigMySuffixService
                ]
            })
            .overrideTemplate(APConfigMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(APConfigMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(APConfigMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new APConfigMySuffix(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.aPConfigs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
