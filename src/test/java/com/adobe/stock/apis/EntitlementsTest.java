/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.apis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockObjectFactory;
import org.testng.Assert;
import org.testng.IObjectFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import com.adobe.stock.config.Endpoints;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.EntitlementList;

@PowerMockIgnore({ "javax.management.*", "javax.xml.parsers.*",
    "com.sun.org.apache.xerces.internal.jaxp.*", "ch.qos.logback.*",
    "org.slf4j.*", "javax.net.ssl.*" })
@Listeners({ com.adobe.stock.logger.TestCustomLogger.class,
    com.adobe.stock.logger.TestSuiteLogger.class })
@PrepareForTest({ StockConfig.class, Endpoints.class, HttpUtils.class})
@Test(suiteName = "Entitlements")
public class EntitlementsTest {
    private static final String TEST_LIST_ENTITLEMENT_RESPONSE = "{\"guid\": \"94AF20885964C4740A494210@AdobeID\",\"creation_date\": \"2017-07-11 14:25:37\",\"organizations\": [{\"id\": null,\"name\": \"Personal\",\"entitlements\": [{\"entitlement_id\": 1654960,\"label\": \"Personal\",\"guid\": null,\"creation\": \"2017-07-11 14:25:37\",\"nb_active_allotments\": 12,\"licenses_available\": 25,\"licenses_used\": 67,\"has_overage\": true,\"is_default_entitlement\": false,\"suspended_date\": null}]}]}";
    private StockConfig config;

    @ObjectFactory
    public IObjectFactory getObjectFactory() {
        return new PowerMockObjectFactory();
    }

    @BeforeMethod
    public void beforeEachMethod() {
        try {
            PowerMockito.mockStatic(HttpUtils.class);
            config = new StockConfig().setApiKey("TestApiKey")
                    .setProduct("TestProduct");
        } catch (Exception e) {
            Assert.fail("Didn't expect the Exception!", e);
        }
    }

    @Test(groups = "Entitlements.constructor", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Config can't be null")
    public void Entitlements_should_throw_stockexception_since_config_parameter_is_null()
            throws StockException {
        new Entitlements(null);
    }

    @Test(groups = "Entitlements.constructor")
    public void Entitlements_should_throw_stockexception_since_config_not_initialized_properly()
            throws StockException {
        StockConfig config = new StockConfig();
        try {
            new Entitlements(config);
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Api Key configuration can't be null!");
        }
        config.setApiKey("testKey");
        try {
            new Entitlements(config);
        } catch (StockException e) {
            Assert.assertEquals(e.getMessage(),
                    "Product configuration can't be null!");
        }
    }

    @Test(groups = "Entitlements.constructor")
    public void Entitlements_should_return_new_object_of_Entitlements_class() {
        try {
            Entitlements api = new Entitlements(config);
            Assert.assertNotNull(api);
            Field fConfig = api.getClass().getDeclaredField("mConfig");
            fConfig.setAccessible(true);
            StockConfig mConfig = (StockConfig) fConfig.get(api);
            Assert.assertNotNull(mConfig);
            Assert.assertEquals(mConfig.getApiKey(), config.getApiKey());
            Assert.assertEquals(mConfig.getProduct(), config.getProduct());
            Assert.assertEquals(mConfig.getTargetEnvironment(),
                    config.getTargetEnvironment());
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }

    @Test(groups = "Entitlements.listEntitlements", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Access token can't be null or empty")
    public void getContentInfo_should_throw_stockexception_if_accessToken_is_null()
            throws StockException {
        Entitlements api = new Entitlements(config);
        api.listEntitlements(null, null);
    }

    @Test(groups = "Entitlements.listEntitlements", expectedExceptions = {
            StockException.class }, expectedExceptionsMessageRegExp = "Access token can't be null or empty")
    public void getContentInfo_should_throw_stockexception_if_accessToken_is_empty()
            throws StockException {
        Entitlements api = new Entitlements(config);
        api.listEntitlements("", "");
    }

    @Test(groups = "Entitlements.listEntitlements")
    public void listEntitlements_should_return_valid_response() {
        try {
            PowerMockito
                    .when(HttpUtils.doGet(Mockito.anyString(),
                            Matchers.<Map<String, String>> any()))
                    .thenReturn(TEST_LIST_ENTITLEMENT_RESPONSE);
            Entitlements api = new Entitlements(config);
            EntitlementList response = api.listEntitlements("accessToken", "entityReference");
            Assert.assertTrue(response.getOrganizations().get(0).getName().equals("Personal"));
            Assert.assertTrue(response.getOrganizations().get(0).getEntitlements().get(0).getHasOverage());
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
        
    }
    @Test(groups = "Entitlements.selectEntitlements")
    public void selectEntitlements_should_return_valid_response() {
        try {
            PowerMockito
            .when(HttpUtils.doPost(Mockito.anyString(),
                    Matchers.<Map<String, String>> any(),
                    Matchers.any(byte[].class), (ContentType)
                    Matchers.any())).thenReturn(String.valueOf(HttpStatus.SC_NO_CONTENT));
            Entitlements api = new Entitlements(config);
            api.selectEntitlement("accessToken", "guid", "");
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }
    @Test(groups = "Entitlements.selectEntitlements")
    public void selectEntitlements_should_return_valid_response_if_guid_is_null() {
        try {
            PowerMockito
            .when(HttpUtils.doPost(Mockito.anyString(),
                    Matchers.<Map<String, String>> any(),
                    Matchers.any(byte[].class), (ContentType)
                    Matchers.any())).thenReturn(String.valueOf(HttpStatus.SC_NO_CONTENT));
            Entitlements api = new Entitlements(config);
            api.selectEntitlement("accessToken", null, null);
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }
    @Test(groups = "Entitlements.selectEntitlements")
    public void selectEntitlements_should_return_valid_response_if_guid_is_empty() {
        try {
            PowerMockito
            .when(HttpUtils.doPost(Mockito.anyString(),
                    Matchers.<Map<String, String>> any(),
                    Matchers.any(byte[].class), (ContentType)
                    Matchers.any())).thenReturn(String.valueOf(HttpStatus.SC_NO_CONTENT));
            Entitlements api = new Entitlements(config);
            api.selectEntitlement("accessToken", "", "");
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail("Didn't expect the exception here!", e);
        }
    }
    @Test(groups = "Entitlements.selectEntitlements",
            expectedExceptions = { StockException.class })
    public void selectEntitlements_should_throw_exception_if_guid_is_invalid() throws StockException {
                PowerMockito
                .when(HttpUtils.doPost(Mockito.anyString(),
                        Matchers.<Map<String, String>> any(),
                        Matchers.any(byte[].class), (ContentType)
                        Matchers.any())).thenThrow(new StockException("Guid Not Found"));

                Entitlements api = new Entitlements(config);
                api.selectEntitlement("accessToken", "Invalid GUID", "");
    }
    @Test(groups = "Entitlements",
            expectedExceptions = { StockException.class })
    public void EntitlementsApiHelper_should_throw_exception_if_url_contains_special_character() throws Exception {
        String uri = EntitlementsApiHelper.createApiURL("|", "accessToken", "");
    }
    @Test
    public void EntitlementsAPIHelpers_instance_should_be_created_using_reflection()
            throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Constructor<EntitlementsApiHelper> constructor = EntitlementsApiHelper.class
                .getDeclaredConstructor();
        constructor.setAccessible(true);
        EntitlementsApiHelper instance = constructor.newInstance();
        Assert.assertNotNull(instance);
    }
}
