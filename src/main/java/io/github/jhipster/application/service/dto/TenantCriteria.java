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
 * Criteria class for the Tenant entity. This class is used in TenantResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /tenants?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TenantCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter tenantName;

    private LongFilter ownedVenueId;

    private LongFilter ownedNetworkId;

    private LongFilter ownedTagId;

    public TenantCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTenantName() {
        return tenantName;
    }

    public void setTenantName(StringFilter tenantName) {
        this.tenantName = tenantName;
    }

    public LongFilter getOwnedVenueId() {
        return ownedVenueId;
    }

    public void setOwnedVenueId(LongFilter ownedVenueId) {
        this.ownedVenueId = ownedVenueId;
    }

    public LongFilter getOwnedNetworkId() {
        return ownedNetworkId;
    }

    public void setOwnedNetworkId(LongFilter ownedNetworkId) {
        this.ownedNetworkId = ownedNetworkId;
    }

    public LongFilter getOwnedTagId() {
        return ownedTagId;
    }

    public void setOwnedTagId(LongFilter ownedTagId) {
        this.ownedTagId = ownedTagId;
    }

    @Override
    public String toString() {
        return "TenantCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tenantName != null ? "tenantName=" + tenantName + ", " : "") +
                (ownedVenueId != null ? "ownedVenueId=" + ownedVenueId + ", " : "") +
                (ownedNetworkId != null ? "ownedNetworkId=" + ownedNetworkId + ", " : "") +
                (ownedTagId != null ? "ownedTagId=" + ownedTagId + ", " : "") +
            "}";
    }

}
