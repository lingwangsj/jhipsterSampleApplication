import { BaseEntity } from './../../shared';

export class TenantMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public tenantName?: string,
        public ownedVenues?: BaseEntity[],
        public ownedNetworks?: BaseEntity[],
        public ownedTags?: BaseEntity[],
    ) {
    }
}
