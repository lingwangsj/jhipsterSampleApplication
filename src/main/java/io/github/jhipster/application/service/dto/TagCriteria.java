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
 * Criteria class for the Tag entity. This class is used in TagResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /tags?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class TagCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter type;

    private StringFilter label;

    private LongFilter tenantId;

    private LongFilter apId;

    private LongFilter networkId;

    private LongFilter apConfigId;

    public TagCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public StringFilter getLabel() {
        return label;
    }

    public void setLabel(StringFilter label) {
        this.label = label;
    }

    public LongFilter getTenantId() {
        return tenantId;
    }

    public void setTenantId(LongFilter tenantId) {
        this.tenantId = tenantId;
    }

    public LongFilter getApId() {
        return apId;
    }

    public void setApId(LongFilter apId) {
        this.apId = apId;
    }

    public LongFilter getNetworkId() {
        return networkId;
    }

    public void setNetworkId(LongFilter networkId) {
        this.networkId = networkId;
    }

    public LongFilter getApConfigId() {
        return apConfigId;
    }

    public void setApConfigId(LongFilter apConfigId) {
        this.apConfigId = apConfigId;
    }

    @Override
    public String toString() {
        return "TagCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (label != null ? "label=" + label + ", " : "") +
                (tenantId != null ? "tenantId=" + tenantId + ", " : "") +
                (apId != null ? "apId=" + apId + ", " : "") +
                (networkId != null ? "networkId=" + networkId + ", " : "") +
                (apConfigId != null ? "apConfigId=" + apConfigId + ", " : "") +
            "}";
    }

}
