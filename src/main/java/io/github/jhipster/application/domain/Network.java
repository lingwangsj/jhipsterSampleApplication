package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Network.
 */
@Entity
@Table(name = "network")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Network implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "network_name", nullable = false)
    private String networkName;

    @NotNull
    @Column(name = "ssid", nullable = false)
    private String ssid;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "network_tags",
               joinColumns = @JoinColumn(name="networks_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="tags_id", referencedColumnName="id"))
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    private Tenant tenant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetworkName() {
        return networkName;
    }

    public Network networkName(String networkName) {
        this.networkName = networkName;
        return this;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getSsid() {
        return ssid;
    }

    public Network ssid(String ssid) {
        this.ssid = ssid;
        return this;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Network tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public Network addTags(Tag tag) {
        this.tags.add(tag);
        tag.getNetworks().add(this);
        return this;
    }

    public Network removeTags(Tag tag) {
        this.tags.remove(tag);
        tag.getNetworks().remove(this);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Network tenant(Tenant tenant) {
        this.tenant = tenant;
        return this;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
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
        Network network = (Network) o;
        if (network.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), network.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Network{" +
            "id=" + getId() +
            ", networkName='" + getNetworkName() + "'" +
            ", ssid='" + getSsid() + "'" +
            "}";
    }
}
