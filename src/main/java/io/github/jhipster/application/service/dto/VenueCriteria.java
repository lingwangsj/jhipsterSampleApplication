package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Venue entity. This class is used in VenueResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /venues?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VenueCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter venueName;

    private LongFilter ownedAPId;

    private LongFilter tenantId;

    public VenueCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getVenueName() {
        return venueName;
    }

    public void setVenueName(StringFilter venueName) {
        this.venueName = venueName;
    }

    public LongFilter getOwnedAPId() {
        return ownedAPId;
    }

    public void setOwnedAPId(LongFilter ownedAPId) {
        this.ownedAPId = ownedAPId;
    }

    public LongFilter getTenantId() {
        return tenantId;
    }

    public void setTenantId(LongFilter tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "VenueCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (venueName != null ? "venueName=" + venueName + ", " : "") +
                (ownedAPId != null ? "ownedAPId=" + ownedAPId + ", " : "") +
                (tenantId != null ? "tenantId=" + tenantId + ", " : "") +
            "}";
    }

}
