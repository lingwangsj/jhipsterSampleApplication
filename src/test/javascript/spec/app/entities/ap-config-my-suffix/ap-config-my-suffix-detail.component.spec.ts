/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { APConfigMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix-detail.component';
import { APConfigMySuffixService } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.service';
import { APConfigMySuffix } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.model';

describe('Component Tests', () => {

    describe('APConfigMySuffix Management Detail Component', () => {
        let comp: APConfigMySuffixDetailComponent;
        let fixture: ComponentFixture<APConfigMySuffixDetailComponent>;
        let service: APConfigMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [APConfigMySuffixDetailComponent],
                providers: [
                    APConfigMySuffixService
                ]
            })
            .overrideTemplate(APConfigMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(APConfigMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(APConfigMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new APConfigMySuffix(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.aPConfig).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
