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
 * Listing of the entitlement organization for an account.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class EntitlementOrganization {
    /**
     * Organization Id.
     */
    private String mId;
    /**
     * Organization Name.
     */
    private String mName;
    /**
     * List of Entitlements.
     */
    private ArrayList<Entitlement> mEntitlements;
    /**
     * Default Constructor.
     */
    public EntitlementOrganization() {
    }
    /**
     * Get Organization Id.
     * @return Organization Id.
     */
    public String getId() {
        return mId;
    }
    /**
     * Organization Id.
     * @param id Organization Id.
     */
    @JsonSetter("id")
    public void setId(final String id) {
        this.mId = id;
    }
    /**
     * Get Organization Name.
     * @return Organization Name.
     */
    public String getName() {
        return mName;
    }
    /**
     * Sets Organization Name.
     * @param name Organization Name.
     */
    @JsonSetter("name")
    public void setName(final String name) {
        this.mName = name;
    }
    /**
     * Get List of Entitlements.
     * @return List of Entitlements
     */
    public List<Entitlement> getEntitlements() {
        return mEntitlements;
    }
    /**
     * Sets List of Entitlements.
     * @param entitlements List of Entitlements
     */
    @JsonSetter("entitlements")
    public void setEntitlements(final ArrayList<Entitlement> entitlements) {
        this.mEntitlements = entitlements;
    }
}
