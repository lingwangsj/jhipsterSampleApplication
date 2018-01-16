package io.github.jhipster.application.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * not an ignored comment
 */
@ApiModel(description = "not an ignored comment")
@Entity
@Table(name = "ap")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class AP implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @NotNull
    @Column(name = "ap_name", nullable = false)
    private String apName;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ap_tags",
               joinColumns = @JoinColumn(name="aps_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="tags_id", referencedColumnName="id"))
    private Set<Tag> tags = new HashSet<>();

    @ManyToOne
    private Venue venue;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public AP serialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getApName() {
        return apName;
    }

    public AP apName(String apName) {
        this.apName = apName;
        return this;
    }

    public void setApName(String apName) {
        this.apName = apName;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public AP tags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public AP addTags(Tag tag) {
        this.tags.add(tag);
        tag.getAps().add(this);
        return this;
    }

    public AP removeTags(Tag tag) {
        this.tags.remove(tag);
        tag.getAps().remove(this);
        return this;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Venue getVenue() {
        return venue;
    }

    public AP venue(Venue venue) {
        this.venue = venue;
        return this;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
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
        AP aP = (AP) o;
        if (aP.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aP.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AP{" +
            "id=" + getId() +
            ", serialNumber='" + getSerialNumber() + "'" +
            ", apName='" + getApName() + "'" +
            "}";
    }
}
