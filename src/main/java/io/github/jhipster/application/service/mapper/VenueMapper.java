package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.VenueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Venue and its DTO VenueDTO.
 */
@Mapper(componentModel = "spring", uses = {TenantMapper.class})
public interface VenueMapper extends EntityMapper<VenueDTO, Venue> {

    @Mapping(source = "tenant.id", target = "tenantId")
    VenueDTO toDto(Venue venue);

    @Mapping(target = "ownedAPS", ignore = true)
    @Mapping(source = "tenantId", target = "tenant")
    Venue toEntity(VenueDTO venueDTO);

    default Venue fromId(Long id) {
        if (id == null) {
            return null;
        }
        Venue venue = new Venue();
        venue.setId(id);
        return venue;
    }
}
