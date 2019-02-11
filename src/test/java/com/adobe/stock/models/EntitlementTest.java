/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.models;

import java.lang.reflect.Field;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@Test(suiteName = "Entitlement")
public class EntitlementTest {

    Entitlement entitlement;
    
    @BeforeSuite
    void creating_entitlement_InstanceSuccessfully() {
        entitlement = new Entitlement();
        Assert.assertNotNull(entitlement);
    }

    @Test(groups = { "Setters" })
    void setEntitlementId_should_set_Id_to_entitlement() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlement.setEntitlementId(123);
        Field f = entitlement.getClass().getDeclaredField("mEntitlementId");
        f.setAccessible(true);
        Assert.assertEquals(123, f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getEntitlementId_should_get_Id_from_entitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mEntitlementId");
        f.setAccessible(true);
        f.set(entitlement, 123);
        Assert.assertEquals(123, entitlement.getEntitlementId().intValue());
    }

    @Test(groups = { "Setters" })
    void setEntitlementLabel_should_set_EntitlementLabel_to_entitlement() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlement.setEntitlementLabel("EntitlementLabel");
        Field f = entitlement.getClass().getDeclaredField("mEntitlementLabel");
        f.setAccessible(true);
        Assert.assertEquals("EntitlementLabel", f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getEntitlementLabel_should_get_EntitlementLabel_from_entitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mEntitlementLabel");
        f.setAccessible(true);
        f.set(entitlement, "EntitlementLabel");
        Assert.assertEquals("EntitlementLabel", entitlement.getEntitlementLabel());
    }
    @Test(groups = { "Setters" })
    void setSuspendDate_should_set_SuspendDate_to_entitlement() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlement.setSuspendDate("SuspendDate");
        Field f = entitlement.getClass().getDeclaredField("mSuspendDate");
        f.setAccessible(true);
        Assert.assertEquals("SuspendDate", f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getSuspendDate_should_get_SuspendDate_from_entitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mSuspendDate");
        f.setAccessible(true);
        f.set(entitlement, "SuspendDate");
        Assert.assertEquals("SuspendDate", entitlement.getSuspendDate());
    }

    @Test(groups = { "Setters" })
    void setGuid_should_set_guid_to_entitlement() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlement.setGuid("some_guid");
        Field f = entitlement.getClass().getDeclaredField("mGuid");
        f.setAccessible(true);
        Assert.assertEquals("some_guid", f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getGuid_should_get_guid_from_entitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mGuid");
        f.setAccessible(true);
        f.set(entitlement, "some_guid");
        Assert.assertEquals("some_guid", entitlement.getGuid());
    }

    @Test(groups = { "Setters" })
    void setLicensesAvailable_should_set_available_licenses_to_entitlement()
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        entitlement.setLicensesAvailable(123);
        Field f = entitlement.getClass().getDeclaredField(
                "mLicensesAvailable");
        f.setAccessible(true);
        Assert.assertEquals(123, f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getLicensesAvailable_should_get_available_licenses_to_entitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField(
                "mLicensesAvailable");
        f.setAccessible(true);
        f.set(entitlement, 123);
        Assert.assertEquals(123, entitlement.getLicensesAvailable().
                intValue());
    }

    @Test(groups = { "Getters" })
    void getLicensesUsed_should_get_Used_licenses_to_entitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField(
                "mLicensesUsed");
        f.setAccessible(true);
        f.set(entitlement, 123);
        Assert.assertEquals(123, entitlement.getLicensesUsed().
                intValue());
    }
    
    @Test(groups = { "Setters" })
    void setLicensesUsed_should_set_Used_licenses_to_entitlement()
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        entitlement.setLicensesUsed(123);
        Field f = entitlement.getClass().getDeclaredField(
                "mLicensesUsed");
        f.setAccessible(true);
        Assert.assertEquals(123, f.get(entitlement));
    }
    @Test(groups = { "Getters" })
    void getNumOfActiveAllotments_should_get_Active_Allotments_to_entitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField(
                "mNumOfActiveAllotments");
        f.setAccessible(true);
        f.set(entitlement, 123);
        Assert.assertEquals(123, entitlement.getNumOfActiveAllotments().
                intValue());
    }
    @Test(groups = { "Setters" })
    void setNumOfActiveAllotments_should_set_Active_Allotments_to_entitlement()
            throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        entitlement.setNumOfActiveAllotments(123);
        Field f = entitlement.getClass().getDeclaredField(
                "mNumOfActiveAllotments");
        f.setAccessible(true);
        Assert.assertEquals(123, f.get(entitlement));
    }

    @Test(groups = { "Setters" })
    void setCreationDate_should_set_creation_date_to_entitlement() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlement.setCreationDate("creation_date");
        Field f = entitlement.getClass().getDeclaredField("mCreationDate");
        f.setAccessible(true);
        Assert.assertEquals("creation_date", f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getCreationDate_should_get_CreationDate_from_entitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mCreationDate");
        f.setAccessible(true);
        f.set(entitlement, "creation_date");
        Assert.assertEquals("creation_date", entitlement.getCreationDate());
    }
    @Test(groups = { "Setters" })
    void setHasOverage_should_set_has_overage_flag_to_entitlement() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlement.setHasOverage(true);
        Field f = entitlement.getClass().getDeclaredField("mHasOverage");
        f.setAccessible(true);
        Assert.assertTrue((boolean) f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getHasOverage_should_set_has_overage_flag_from_entitlement()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField("mHasOverage");
        f.setAccessible(true);
        f.set(entitlement, true);
        Assert.assertTrue(entitlement.getHasOverage());
    }

    @Test(groups = { "Setters" })
    void setIsDefaultEntitlement_should_set_default_entitlement_flag() throws
        NoSuchFieldException, SecurityException, IllegalArgumentException,
            IllegalAccessException {
        entitlement.setIsDefaultEntitlement(true);
        Field f = entitlement.getClass().getDeclaredField(
                "mIsDefaultEntitlement");
        f.setAccessible(true);
        Assert.assertTrue((boolean) f.get(entitlement));
    }

    @Test(groups = { "Getters" })
    void getIsDefaultEntitlement_should_get_default_entitlement_flag()
            throws NoSuchFieldException, IllegalAccessException {
        Field f = entitlement.getClass().getDeclaredField(
                "mIsDefaultEntitlement");
        f.setAccessible(true);
        f.set(entitlement, true);
        Assert.assertTrue(entitlement.getIsDefaultEntitlement());
    }
}
