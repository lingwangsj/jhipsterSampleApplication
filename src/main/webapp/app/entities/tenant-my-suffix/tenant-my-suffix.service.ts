import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TenantMySuffix } from './tenant-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TenantMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/tenants';

    constructor(private http: Http) { }

    create(tenant: TenantMySuffix): Observable<TenantMySuffix> {
        const copy = this.convert(tenant);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(tenant: TenantMySuffix): Observable<TenantMySuffix> {
        const copy = this.convert(tenant);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<TenantMySuffix> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to TenantMySuffix.
     */
    private convertItemFromServer(json: any): TenantMySuffix {
        const entity: TenantMySuffix = Object.assign(new TenantMySuffix(), json);
        return entity;
    }

    /**
     * Convert a TenantMySuffix to a JSON which can be sent to the server.
     */
    private convert(tenant: TenantMySuffix): TenantMySuffix {
        const copy: TenantMySuffix = Object.assign({}, tenant);
        return copy;
    }
}
