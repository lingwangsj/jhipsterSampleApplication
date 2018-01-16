package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TenantDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Tenant and its DTO TenantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TenantMapper extends EntityMapper<TenantDTO, Tenant> {


    @Mapping(target = "ownedVenues", ignore = true)
    @Mapping(target = "ownedNetworks", ignore = true)
    @Mapping(target = "ownedTags", ignore = true)
    Tenant toEntity(TenantDTO tenantDTO);

    default Tenant fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tenant tenant = new Tenant();
        tenant.setId(id);
        return tenant;
    }
}
