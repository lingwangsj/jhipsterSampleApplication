import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { APConfigMySuffix } from './ap-config-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class APConfigMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/ap-configs';

    constructor(private http: Http) { }

    create(aPConfig: APConfigMySuffix): Observable<APConfigMySuffix> {
        const copy = this.convert(aPConfig);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(aPConfig: APConfigMySuffix): Observable<APConfigMySuffix> {
        const copy = this.convert(aPConfig);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<APConfigMySuffix> {
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
     * Convert a returned JSON object to APConfigMySuffix.
     */
    private convertItemFromServer(json: any): APConfigMySuffix {
        const entity: APConfigMySuffix = Object.assign(new APConfigMySuffix(), json);
        return entity;
    }

    /**
     * Convert a APConfigMySuffix to a JSON which can be sent to the server.
     */
    private convert(aPConfig: APConfigMySuffix): APConfigMySuffix {
        const copy: APConfigMySuffix = Object.assign({}, aPConfig);
        return copy;
    }
}
