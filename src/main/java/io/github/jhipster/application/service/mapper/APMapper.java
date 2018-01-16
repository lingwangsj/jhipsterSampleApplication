package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.APDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AP and its DTO APDTO.
 */
@Mapper(componentModel = "spring", uses = {TagMapper.class, VenueMapper.class})
public interface APMapper extends EntityMapper<APDTO, AP> {

    @Mapping(source = "venue.id", target = "venueId")
    APDTO toDto(AP aP);

    @Mapping(source = "venueId", target = "venue")
    AP toEntity(APDTO aPDTO);

    default AP fromId(Long id) {
        if (id == null) {
            return null;
        }
        AP aP = new AP();
        aP.setId(id);
        return aP;
    }
}
