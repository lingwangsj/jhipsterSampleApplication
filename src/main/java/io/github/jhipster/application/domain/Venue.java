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
 * A Venue.
 */
@Entity
@Table(name = "venue")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Venue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "venue_name", nullable = false)
    private String venueName;

    @OneToMany(mappedBy = "venue")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AP> ownedAPS = new HashSet<>();

    @ManyToOne
    private Tenant tenant;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVenueName() {
        return venueName;
    }

    public Venue venueName(String venueName) {
        this.venueName = venueName;
        return this;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public Set<AP> getOwnedAPS() {
        return ownedAPS;
    }

    public Venue ownedAPS(Set<AP> aPS) {
        this.ownedAPS = aPS;
        return this;
    }

    public Venue addOwnedAP(AP aP) {
        this.ownedAPS.add(aP);
        aP.setVenue(this);
        return this;
    }

    public Venue removeOwnedAP(AP aP) {
        this.ownedAPS.remove(aP);
        aP.setVenue(null);
        return this;
    }

    public void setOwnedAPS(Set<AP> aPS) {
        this.ownedAPS = aPS;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public Venue tenant(Tenant tenant) {
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
        Venue venue = (Venue) o;
        if (venue.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), venue.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Venue{" +
            "id=" + getId() +
            ", venueName='" + getVenueName() + "'" +
            "}";
    }
}
