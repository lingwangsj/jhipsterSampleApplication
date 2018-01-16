/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { NetworkMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix-detail.component';
import { NetworkMySuffixService } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix.service';
import { NetworkMySuffix } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix.model';

describe('Component Tests', () => {

    describe('NetworkMySuffix Management Detail Component', () => {
        let comp: NetworkMySuffixDetailComponent;
        let fixture: ComponentFixture<NetworkMySuffixDetailComponent>;
        let service: NetworkMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [NetworkMySuffixDetailComponent],
                providers: [
                    NetworkMySuffixService
                ]
            })
            .overrideTemplate(NetworkMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NetworkMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NetworkMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new NetworkMySuffix(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.network).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
