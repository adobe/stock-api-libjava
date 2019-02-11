/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Listing of the available entitlement for an account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Entitlement {
    /**
     * Entitlement Id.
     */
    private Integer mEntitlementId;
    /**
     * Entitlement Label.
     */
    private String mEntitlementLabel;
    /**
     * Stock user id.
     */
    private String mGuid;
    /**
     * Entitlement Creation Date.
     */
    private String mCreationDate;
    /**
     * Number of active allotments.
     */
    private Integer mNumOfActiveAllotments;
    /**
     * Number of Licenses Available.
     */
    private Integer mLicensesAvailable;
    /**
     * Number of Licenses used.
     */
    private Integer mLicensesUsed;
    /**
     * Boolean flag for Overage.
     */
    private Boolean mHasOverage;
    /**
     * Check for default entitlement of feature flag is on.
     */
    private Boolean mIsDefaultEntitlement;
    /**
     * Entitlement Suspend Date.
     */
    private String mSuspendDate;
    /**
     * Default Constructor.
     */
    public Entitlement() {
    }
    /**
     * Gets Entitlement Id.
     * @return Entitlement Id
     */
    public Integer getEntitlementId() {
        return mEntitlementId;
    }
    /**
     * Sets Entitlement Id.
     * @param entitlementId Entitlement Id.
     */
    @JsonSetter("entitlement_id")
    public void setEntitlementId(final Integer entitlementId) {
        this.mEntitlementId = entitlementId;
    }
    /**
     * Gets Entitlement Label.
     * @return Entitlement Label
     */
    public String getEntitlementLabel() {
        return mEntitlementLabel;
    }
    /**
     * Sets Entitlement Label.
     * @param entitlementLabel Entitlement Label
     */
    @JsonSetter("label")
    public void setEntitlementLabel(final String entitlementLabel) {
        this.mEntitlementLabel = entitlementLabel;
    }
    /**
     * Gets Stock User Guid.
     * @return Stock User Guid
     */
    public String getGuid() {
        return mGuid;
    }
    /**
     * Sets Stock User Guid.
     * @param guid Stock User Guid
     */
    @JsonSetter("guid")
    public void setGuid(final String guid) {
        this.mGuid = guid;
    }
    /**
     * Gets Entitlement Creation date.
     * @return Entitlement Creation date.
     */
    public String getCreationDate() {
        return mCreationDate;
    }
    /**
     * Sets Entitlement Creation date.
     * @param creationDate Entitlement Creation date.
     */
    @JsonSetter("creation")
    public void setCreationDate(final String creationDate) {
        this.mCreationDate = creationDate;
    }
    /**
     * Get Number of Active Allotments.
     * @return Number of Active Allotments
     */
    public Integer getNumOfActiveAllotments() {
        return mNumOfActiveAllotments;
    }
    /**
     * Sets Number of Active Allotments.
     * @param numOfActiveAllotments Number of Active Allotments
     */
    @JsonSetter("nb_active_allotments")
    public void setNumOfActiveAllotments(final Integer numOfActiveAllotments) {
        this.mNumOfActiveAllotments = numOfActiveAllotments;
    }
    /**
     * Gets Number Of Licenses Available in an entitlement.
     * @return Number Of Licenses Available in an entitlement.
     */
    public Integer getLicensesAvailable() {
        return mLicensesAvailable;
    }
    /**
     * Sets Number Of Licenses Available in an entitlement.
     * @param licensesAvailable Number Of Licenses Available in an
     * entitlement.
     */
    @JsonSetter("licenses_available")
    public void setLicensesAvailable(final Integer licensesAvailable) {
        this.mLicensesAvailable = licensesAvailable;
    }
    /**
     * Get Number Of Licenses Used in an entitlement.
     * @return Number Of Licenses Used in an entitlement.
     */
    public Integer getLicensesUsed() {
        return mLicensesUsed;
    }
    /**
     * Sets Number Of Licenses Used in an entitlement.
     * @param licensesUsed Number Of Licenses Used in an entitlement.
     */
    @JsonSetter("licenses_used")
    public void setLicensesUsed(final Integer licensesUsed) {
        this.mLicensesUsed = licensesUsed;
    }
    /**
     * True if has overage otherwise false.
     * @return True if has overage otherwise false
     */
    public Boolean getHasOverage() {
        return mHasOverage;
    }
    /**
     * Sets True if has overage otherwise false.
     * @param hasOverage True if has overage otherwise false
     */
    @JsonSetter("has_overage")
    public void setHasOverage(final Boolean hasOverage) {
        this.mHasOverage = hasOverage;
    }
    /**
     * True if it is a default entitlement, false otherwise.
     * @return True if it is a default entitlement, false otherwise.
     */
    public Boolean getIsDefaultEntitlement() {
        return mIsDefaultEntitlement;
    }
    /**
     * Sets true if it is a default entitlement, false otherwise.
     * @param isDefaultEntitlement true if it is a default entitlement,
     * false otherwise.
     */
    @JsonSetter("is_default_entitlement")
    public void setIsDefaultEntitlement(final Boolean isDefaultEntitlement) {
        this.mIsDefaultEntitlement = isDefaultEntitlement;
    }
    /**
     * Gets entitlement suspended date.
     * @return entitlement suspended date
     */
    public String getSuspendDate() {
        return mSuspendDate;
    }
    /**
     * Sets entitlement suspended date.
     * @param suspendDate entitlement suspended date.
     */
    @JsonSetter("suspended_date")
    public void setSuspendDate(final String suspendDate) {
        this.mSuspendDate = suspendDate;
    }
}
