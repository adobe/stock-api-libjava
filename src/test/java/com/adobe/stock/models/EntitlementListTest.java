/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "EntitlementList")
public class EntitlementListTest {

    EntitlementList entitlementList;
    
    @BeforeSuite
    void creating_EntitlementList_InstanceSuccessfully() {
        entitlementList = new EntitlementList();
        Assert.assertNotNull(entitlementList);
    }

    @Test(groups = { "Setters" })
    void setGuid_should_set_guid_to_entitlement_list() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlementList.setGuid("some_guid");
        Field f = entitlementList.getClass().getDeclaredField("mGuid");
        f.setAccessible(true);
        Assert.assertEquals("some_guid", f.get(entitlementList));
    }

    @Test(groups = { "Getters" })
    void getGuid_should_get_guid_from_entitlement_list()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementList.getClass().getDeclaredField("mGuid");
        f.setAccessible(true);
        f.set(entitlementList, "some_guid");
        Assert.assertEquals("some_guid", entitlementList.getGuid());
    }

    @Test(groups = { "Setters" })
    void setCreationDate_should_set_creation_date_to_entitlement_list() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlementList.setCreationDate("creation_date");
        Field f = entitlementList.getClass().getDeclaredField("mCreationDate");
        f.setAccessible(true);
        Assert.assertEquals("creation_date", f.get(entitlementList));
    }

    @Test(groups = { "Getters" })
    void getCreationDate_should_get_CreationDate_from_entitlement_list()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementList.getClass().getDeclaredField("mCreationDate");
        f.setAccessible(true);
        f.set(entitlementList, "creation_date");
        Assert.assertEquals("creation_date", entitlementList.getCreationDate());
    }

    @Test(groups = { "Setters" })
    void setOrganizations_should_set_list_of_organizations_to_entitlement_list()
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        EntitlementOrganization organization = new EntitlementOrganization();
        organization.setId("id");
        ArrayList<EntitlementOrganization> organizationList = new ArrayList<
                EntitlementOrganization>();
        organizationList.add(organization);
        entitlementList.setOrganizations(organizationList);
        Field f = entitlementList.getClass().getDeclaredField("mOrganizations");
        f.setAccessible(true);
        Assert.assertNotNull(f.get(entitlementList));
    }

    @Test(groups = { "Getters" })
    void getOrganizations_should_set_list_of_organizationfrom_entitlement_list()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementList.getClass().getDeclaredField("mOrganizations");
        f.setAccessible(true);
        EntitlementOrganization organization = new EntitlementOrganization();
        organization.setId("id");
        ArrayList<EntitlementOrganization> organizationList = new ArrayList<
                EntitlementOrganization>();
        organizationList.add(organization);
        f.set(entitlementList, organizationList);
        Assert.assertEquals("id", entitlementList.getOrganizations().
                get(0).getId());
    }
}
