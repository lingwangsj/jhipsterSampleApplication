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
 * A Tenant.
 */
@Entity
@Table(name = "tenant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tenant_name", nullable = false)
    private String tenantName;

    @OneToMany(mappedBy = "tenant")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Venue> ownedVenues = new HashSet<>();

    @OneToMany(mappedBy = "tenant")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Network> ownedNetworks = new HashSet<>();

    @OneToMany(mappedBy = "tenant")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Tag> ownedTags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantName() {
        return tenantName;
    }

    public Tenant tenantName(String tenantName) {
        this.tenantName = tenantName;
        return this;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Set<Venue> getOwnedVenues() {
        return ownedVenues;
    }

    public Tenant ownedVenues(Set<Venue> venues) {
        this.ownedVenues = venues;
        return this;
    }

    public Tenant addOwnedVenue(Venue venue) {
        this.ownedVenues.add(venue);
        venue.setTenant(this);
        return this;
    }

    public Tenant removeOwnedVenue(Venue venue) {
        this.ownedVenues.remove(venue);
        venue.setTenant(null);
        return this;
    }

    public void setOwnedVenues(Set<Venue> venues) {
        this.ownedVenues = venues;
    }

    public Set<Network> getOwnedNetworks() {
        return ownedNetworks;
    }

    public Tenant ownedNetworks(Set<Network> networks) {
        this.ownedNetworks = networks;
        return this;
    }

    public Tenant addOwnedNetwork(Network network) {
        this.ownedNetworks.add(network);
        network.setTenant(this);
        return this;
    }

    public Tenant removeOwnedNetwork(Network network) {
        this.ownedNetworks.remove(network);
        network.setTenant(null);
        return this;
    }

    public void setOwnedNetworks(Set<Network> networks) {
        this.ownedNetworks = networks;
    }

    public Set<Tag> getOwnedTags() {
        return ownedTags;
    }

    public Tenant ownedTags(Set<Tag> tags) {
        this.ownedTags = tags;
        return this;
    }

    public Tenant addOwnedTag(Tag tag) {
        this.ownedTags.add(tag);
        tag.setTenant(this);
        return this;
    }

    public Tenant removeOwnedTag(Tag tag) {
        this.ownedTags.remove(tag);
        tag.setTenant(null);
        return this;
    }

    public void setOwnedTags(Set<Tag> tags) {
        this.ownedTags = tags;
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
        Tenant tenant = (Tenant) o;
        if (tenant.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tenant.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tenant{" +
            "id=" + getId() +
            ", tenantName='" + getTenantName() + "'" +
            "}";
    }
}
