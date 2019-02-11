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
@Test(suiteName = "EntitlementOrganizations")
public class EntitlementOrganizationTest {

    EntitlementOrganization entitlementOrganization;
    
    @BeforeSuite
    void creating_entitlementOrganization_InstanceSuccessfully() {
        entitlementOrganization = new EntitlementOrganization();
        Assert.assertNotNull(entitlementOrganization);
    }

    @Test(groups = { "Setters" })
    void setId_should_set_Id_to_entitlement_organization() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlementOrganization.setId("some_Id");
        Field f = entitlementOrganization.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        Assert.assertEquals("some_Id", f.get(entitlementOrganization));
    }

    @Test(groups = { "Getters" })
    void getId_should_get_Id_from_entitlement_organization()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementOrganization.getClass().getDeclaredField("mId");
        f.setAccessible(true);
        f.set(entitlementOrganization, "some_Id");
        Assert.assertEquals("some_Id", entitlementOrganization.getId());
    }

    @Test(groups = { "Setters" })
    void setName_should_set_name_to_entitlement_organization() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlementOrganization.setName("name");
        Field f = entitlementOrganization.getClass().getDeclaredField("mName");
        f.setAccessible(true);
        Assert.assertEquals("name", f.get(entitlementOrganization));
    }

    @Test(groups = { "Getters" })
    void getName_should_get_Name_from_entitlement_organization()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementOrganization.getClass().getDeclaredField("mName");
        f.setAccessible(true);
        f.set(entitlementOrganization, "name");
        Assert.assertEquals("name", entitlementOrganization.getName());
    }

    @Test(groups = { "Setters" })
    void setEntitlements_should_set_list_of_organizations_to_entitlement_organization()
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        Entitlement entitlement = new Entitlement();
        entitlement.setEntitlementId(123);
        ArrayList<Entitlement> entitlementList = new ArrayList<
                Entitlement>();
        entitlementList.add(entitlement);
        entitlementOrganization.setEntitlements(entitlementList);
        Field f = entitlementOrganization.getClass().getDeclaredField("mEntitlements");
        f.setAccessible(true);
        Assert.assertNotNull(f.get(entitlementOrganization));
    }

    @Test(groups = { "Getters" })
    void getEntitlements_should_set_list_of_organization_from_entitlement_organization()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlementOrganization.getClass().getDeclaredField("mEntitlements");
        f.setAccessible(true);
        Entitlement entitlement = new Entitlement();
        entitlement.setEntitlementId(123);
        ArrayList<Entitlement> entitlementList = new ArrayList<
                Entitlement>();
        entitlementList.add(entitlement);
        f.set(entitlementOrganization, entitlementList);
        Assert.assertEquals(123, entitlementOrganization.getEntitlements().
                get(0).getEntitlementId().intValue());
    }
}
