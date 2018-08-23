/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Response from Entitlement List Api including listing of the available
 * entitlement for an account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class EntitlementList {
    /**
     * Stock users Adobe ID GUID.
     */
    private String mGuid;
    /**
     * Entitlement Creation Date.
     */
    private String mCreationDate;
    /**
     * List of Entitlement Organizations.
     */
    private ArrayList<EntitlementOrganization> mOrganizations;
    /**
     * Default Constructor.
     */
    public EntitlementList() {
    }
    /**
     * Get Stock users AdobeId.
     * @return GUID of Stok user
     */
    public String getGuid() {
        return mGuid;
    }
    /**
     * Sets Stock user Id.
     * @param guid Stock user Id
     */
    @JsonSetter("guid")
    public void setGuid(final String guid) {
        this.mGuid = guid;
    }
    /**
     * Get Entitlement Creation Date.
     * @return Entitlement Creation Date.
     */
    public String getCreationDate() {
        return mCreationDate;
    }
    /**
     * Sets Entitlement Creation Date.
     * @param creationDate Entitlement Creation Date.
     */
    @JsonSetter("creation_date")
    public void setCreationDate(final String creationDate) {
        this.mCreationDate = creationDate;
    }
    /**
     * Get List of Entitlement Organizations.
     * @return List of Entitlement Organizations
     */
    public List<EntitlementOrganization> getOrganizations() {
        return mOrganizations;
    }
    /**
     * Sets List of Entitlement Organizations.
     * @param organizations List of Entitlement Organizations
     */
    @JsonSetter("organizations")
    public void setOrganizations(
            final ArrayList<EntitlementOrganization> organizations) {
        this.mOrganizations = organizations;
    }
}
