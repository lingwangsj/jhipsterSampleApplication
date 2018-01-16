package io.github.jhipster.application.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Network entity.
 */
public class NetworkDTO implements Serializable {

    private Long id;

    @NotNull
    private String networkName;

    @NotNull
    private String ssid;

    private Set<TagDTO> tags = new HashSet<>();

    private Long tenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NetworkDTO networkDTO = (NetworkDTO) o;
        if(networkDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), networkDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NetworkDTO{" +
            "id=" + getId() +
            ", networkName='" + getNetworkName() + "'" +
            ", ssid='" + getSsid() + "'" +
            "}";
    }
}
