/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { Headers } from '@angular/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { NetworkMySuffixComponent } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix.component';
import { NetworkMySuffixService } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix.service';
import { NetworkMySuffix } from '../../../../../../main/webapp/app/entities/network-my-suffix/network-my-suffix.model';

describe('Component Tests', () => {

    describe('NetworkMySuffix Management Component', () => {
        let comp: NetworkMySuffixComponent;
        let fixture: ComponentFixture<NetworkMySuffixComponent>;
        let service: NetworkMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [NetworkMySuffixComponent],
                providers: [
                    NetworkMySuffixService
                ]
            })
            .overrideTemplate(NetworkMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(NetworkMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NetworkMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new NetworkMySuffix(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.networks[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
