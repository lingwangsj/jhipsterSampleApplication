import { BaseEntity } from './../../shared';

export class VenueMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public venueName?: string,
        public ownedAPS?: BaseEntity[],
        public tenant?: BaseEntity,
    ) {
    }
}
