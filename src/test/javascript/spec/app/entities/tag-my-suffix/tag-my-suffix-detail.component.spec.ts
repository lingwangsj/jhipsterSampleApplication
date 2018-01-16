/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TagMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/tag-my-suffix/tag-my-suffix-detail.component';
import { TagMySuffixService } from '../../../../../../main/webapp/app/entities/tag-my-suffix/tag-my-suffix.service';
import { TagMySuffix } from '../../../../../../main/webapp/app/entities/tag-my-suffix/tag-my-suffix.model';

describe('Component Tests', () => {

    describe('TagMySuffix Management Detail Component', () => {
        let comp: TagMySuffixDetailComponent;
        let fixture: ComponentFixture<TagMySuffixDetailComponent>;
        let service: TagMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TagMySuffixDetailComponent],
                providers: [
                    TagMySuffixService
                ]
            })
            .overrideTemplate(TagMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TagMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TagMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new TagMySuffix(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tag).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
