import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationTenantMySuffixModule } from './tenant-my-suffix/tenant-my-suffix.module';
import { JhipsterSampleApplicationVenueMySuffixModule } from './venue-my-suffix/venue-my-suffix.module';
import { JhipsterSampleApplicationAPMySuffixModule } from './ap-my-suffix/ap-my-suffix.module';
import { JhipsterSampleApplicationNetworkMySuffixModule } from './network-my-suffix/network-my-suffix.module';
import { JhipsterSampleApplicationAPConfigMySuffixModule } from './ap-config-my-suffix/ap-config-my-suffix.module';
import { JhipsterSampleApplicationTagMySuffixModule } from './tag-my-suffix/tag-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhipsterSampleApplicationTenantMySuffixModule,
        JhipsterSampleApplicationVenueMySuffixModule,
        JhipsterSampleApplicationAPMySuffixModule,
        JhipsterSampleApplicationNetworkMySuffixModule,
        JhipsterSampleApplicationAPConfigMySuffixModule,
        JhipsterSampleApplicationTagMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}
