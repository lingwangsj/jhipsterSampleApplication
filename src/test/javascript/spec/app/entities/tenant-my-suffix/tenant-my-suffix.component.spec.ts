/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TenantMySuffixComponent } from '../../../../../../main/webapp/app/entities/tenant-my-suffix/tenant-my-suffix.component';
import { TenantMySuffixService } from '../../../../../../main/webapp/app/entities/tenant-my-suffix/tenant-my-suffix.service';
import { TenantMySuffix } from '../../../../../../main/webapp/app/entities/tenant-my-suffix/tenant-my-suffix.model';

describe('Component Tests', () => {

    describe('TenantMySuffix Management Component', () => {
        let comp: TenantMySuffixComponent;
        let fixture: ComponentFixture<TenantMySuffixComponent>;
        let service: TenantMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TenantMySuffixComponent],
                providers: [
                    TenantMySuffixService
                ]
            })
            .overrideTemplate(TenantMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TenantMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TenantMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new TenantMySuffix(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tenants[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
