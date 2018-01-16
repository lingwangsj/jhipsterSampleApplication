package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.APConfigDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity APConfig and its DTO APConfigDTO.
 */
@Mapper(componentModel = "spring", uses = {TagMapper.class})
public interface APConfigMapper extends EntityMapper<APConfigDTO, APConfig> {



    default APConfig fromId(Long id) {
        if (id == null) {
            return null;
        }
        APConfig aPConfig = new APConfig();
        aPConfig.setId(id);
        return aPConfig;
    }
}
