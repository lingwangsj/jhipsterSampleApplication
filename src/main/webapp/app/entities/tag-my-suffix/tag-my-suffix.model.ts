import { BaseEntity } from './../../shared';

export class TagMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public type?: string,
        public label?: string,
        public tenantId?: number,
        public aps?: BaseEntity[],
        public networks?: BaseEntity[],
        public apConfigs?: BaseEntity[],
    ) {
    }
}
