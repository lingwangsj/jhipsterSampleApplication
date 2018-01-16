import { BaseEntity } from './../../shared';

export class APMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public serialNumber?: string,
        public apName?: string,
        public tags?: BaseEntity[],
        public venue?: BaseEntity,
    ) {
    }
}
