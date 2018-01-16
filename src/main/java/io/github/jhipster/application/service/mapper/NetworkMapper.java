package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.NetworkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Network and its DTO NetworkDTO.
 */
@Mapper(componentModel = "spring", uses = {TagMapper.class, TenantMapper.class})
public interface NetworkMapper extends EntityMapper<NetworkDTO, Network> {

    @Mapping(source = "tenant.id", target = "tenantId")
    NetworkDTO toDto(Network network);

    @Mapping(source = "tenantId", target = "tenant")
    Network toEntity(NetworkDTO networkDTO);

    default Network fromId(Long id) {
        if (id == null) {
            return null;
        }
        Network network = new Network();
        network.setId(id);
        return network;
    }
}
