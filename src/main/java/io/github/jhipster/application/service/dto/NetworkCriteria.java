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
 * Criteria class for the Network entity. This class is used in NetworkResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /networks?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NetworkCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter networkName;

    private StringFilter ssid;

    private LongFilter tagsId;

    private LongFilter tenantId;

    public NetworkCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNetworkName() {
        return networkName;
    }

    public void setNetworkName(StringFilter networkName) {
        this.networkName = networkName;
    }

    public StringFilter getSsid() {
        return ssid;
    }

    public void setSsid(StringFilter ssid) {
        this.ssid = ssid;
    }

    public LongFilter getTagsId() {
        return tagsId;
    }

    public void setTagsId(LongFilter tagsId) {
        this.tagsId = tagsId;
    }

    public LongFilter getTenantId() {
        return tenantId;
    }

    public void setTenantId(LongFilter tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "NetworkCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (networkName != null ? "networkName=" + networkName + ", " : "") +
                (ssid != null ? "ssid=" + ssid + ", " : "") +
                (tagsId != null ? "tagsId=" + tagsId + ", " : "") +
                (tenantId != null ? "tenantId=" + tenantId + ", " : "") +
            "}";
    }

}
