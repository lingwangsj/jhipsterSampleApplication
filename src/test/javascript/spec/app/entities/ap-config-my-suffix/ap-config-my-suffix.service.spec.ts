/* tslint:disable max-line-length */
import { TestBed, async } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { ConnectionBackend, RequestOptions, BaseRequestOptions, Http, Response, ResponseOptions } from '@angular/http';
import { JhiDateUtils } from 'ng-jhipster';

import { APConfigMySuffixService } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.service';
import { APConfigMySuffix } from '../../../../../../main/webapp/app/entities/ap-config-my-suffix/ap-config-my-suffix.model';
import { SERVER_API_URL } from '../../../../../../main/webapp/app/app.constants';

describe('Service Tests', () => {

    describe('APConfigMySuffix Service', () => {
        let service: APConfigMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                providers: [
                    {
                        provide: ConnectionBackend,
                        useClass: MockBackend
                    },
                    {
                        provide: RequestOptions,
                        useClass: BaseRequestOptions
                    },
                    Http,
                    JhiDateUtils,
                    APConfigMySuffixService
                ]
            });

            service = TestBed.get(APConfigMySuffixService);

            this.backend = TestBed.get(ConnectionBackend) as MockBackend;
            this.backend.connections.subscribe((connection: any) => {
                this.lastConnection = connection;
            });
        }));

        describe('Service methods', () => {
            it('should call correct URL', () => {
                service.find(123).subscribe(() => {});

                expect(this.lastConnection).toBeDefined();

                const resourceUrl = SERVER_API_URL + 'api/ap-configs';
                expect(this.lastConnection.request.url).toEqual(resourceUrl + '/' + 123);
            });
            it('should return APConfigMySuffix', () => {

                let entity: APConfigMySuffix;
                service.find(123).subscribe((_entity: APConfigMySuffix) => {
                    entity = _entity;
                });

                this.lastConnection.mockRespond(new Response(new ResponseOptions({
                    body: JSON.stringify({id: 123}),
                })));

                expect(entity).toBeDefined();
                expect(entity.id).toEqual(123);
            });

            it('should propagate not found response', () => {

                let error: any;
                service.find(123).subscribe(null, (_error: any) => {
                    error = _error;
                });

                this.lastConnection.mockError(new Response(new ResponseOptions({
                    status: 404,
                })));

                expect(error).toBeDefined();
                expect(error.status).toEqual(404);
            });
        });
    });

});
