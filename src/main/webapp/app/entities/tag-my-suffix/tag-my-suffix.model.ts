import { BaseEntity } from './../../shared';

export class TagMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public type?: string,
        public label?: string,
        public tenant?: BaseEntity,
        public aps?: BaseEntity[],
        public networks?: BaseEntity[],
        public apConfigs?: BaseEntity[],
    ) {
    }
}
