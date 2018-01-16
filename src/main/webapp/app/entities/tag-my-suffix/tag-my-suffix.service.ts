import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TagMySuffix } from './tag-my-suffix.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TagMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/tags';

    constructor(private http: Http) { }

    create(tag: TagMySuffix): Observable<TagMySuffix> {
        const copy = this.convert(tag);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(tag: TagMySuffix): Observable<TagMySuffix> {
        const copy = this.convert(tag);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<TagMySuffix> {
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
     * Convert a returned JSON object to TagMySuffix.
     */
    private convertItemFromServer(json: any): TagMySuffix {
        const entity: TagMySuffix = Object.assign(new TagMySuffix(), json);
        return entity;
    }

    /**
     * Convert a TagMySuffix to a JSON which can be sent to the server.
     */
    private convert(tag: TagMySuffix): TagMySuffix {
        const copy: TagMySuffix = Object.assign({}, tag);
        return copy;
    }
}
