package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Tag.
 */
@Entity
@Table(name = "tag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_type", nullable = false)
    private String type;

    @Column(name = "jhi_label")
    private String label;

    @ManyToOne
    private Tenant tenant;

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AP> aps = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Network> networks = new HashSet<>();

    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<APConfig> apConfigs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Tag type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public Tag label(String label) {
        this.label = label;
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Tag tenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Set<AP> getAps() {
        return aps;
    }

    public Tag aps(Set<AP> aPS) {
        this.aps = aPS;
        return this;
    }

    public Tag addAp(AP aP) {
        this.aps.add(aP);
        aP.getTags().add(this);
        return this;
    }

    public Tag removeAp(AP aP) {
        this.aps.remove(aP);
        aP.getTags().remove(this);
        return this;
    }

    public void setAps(Set<AP> aPS) {
        this.aps = aPS;
    }

    public Set<Network> getNetworks() {
        return networks;
    }

    public Tag networks(Set<Network> networks) {
        this.networks = networks;
        return this;
    }

    public Tag addNetwork(Network network) {
        this.networks.add(network);
        network.getTags().add(this);
        return this;
    }

    public Tag removeNetwork(Network network) {
        this.networks.remove(network);
        network.getTags().remove(this);
        return this;
    }

    public void setNetworks(Set<Network> networks) {
        this.networks = networks;
    }

    public Set<APConfig> getApConfigs() {
        return apConfigs;
    }

    public Tag apConfigs(Set<APConfig> aPConfigs) {
        this.apConfigs = aPConfigs;
        return this;
    }

    public Tag addApConfig(APConfig aPConfig) {
        this.apConfigs.add(aPConfig);
        aPConfig.getTags().add(this);
        return this;
    }

    public Tag removeApConfig(APConfig aPConfig) {
        this.apConfigs.remove(aPConfig);
        aPConfig.getTags().remove(this);
        return this;
    }

    public void setApConfigs(Set<APConfig> aPConfigs) {
        this.apConfigs = aPConfigs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        if (tag.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tag{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", label='" + getLabel() + "'" +
            "}";
    }
}
