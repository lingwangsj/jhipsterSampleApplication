import { BaseEntity } from './../../shared';

export class NetworkMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public networkName?: string,
        public ssid?: string,
        public tags?: BaseEntity[],
        public tenantId?: number,
    ) {
    }
}
