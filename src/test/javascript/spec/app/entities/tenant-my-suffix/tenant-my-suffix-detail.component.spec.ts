/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TenantMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/tenant-my-suffix/tenant-my-suffix-detail.component';
import { TenantMySuffixService } from '../../../../../../main/webapp/app/entities/tenant-my-suffix/tenant-my-suffix.service';
import { TenantMySuffix } from '../../../../../../main/webapp/app/entities/tenant-my-suffix/tenant-my-suffix.model';

describe('Component Tests', () => {

    describe('TenantMySuffix Management Detail Component', () => {
        let comp: TenantMySuffixDetailComponent;
        let fixture: ComponentFixture<TenantMySuffixDetailComponent>;
        let service: TenantMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TenantMySuffixDetailComponent],
                providers: [
                    TenantMySuffixService
                ]
            })
            .overrideTemplate(TenantMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TenantMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TenantMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new TenantMySuffix(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tenant).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
