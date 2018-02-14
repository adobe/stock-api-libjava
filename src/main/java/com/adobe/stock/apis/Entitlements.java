/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 ******************************************************************************/
package com.adobe.stock.apis;

import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;

import com.adobe.stock.config.StockConfig;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.EntitlementList;

/**
 * The class defining helper methods for entitlement apis.
 */
final class EntitlementsApiHelper {

    /**
     * Entity Reference to be passed with service token.
     */
    private static final String ENTITY_REF_PARAM = "entity_ref";

    /**
     * Default constructor.
     */
    private EntitlementsApiHelper() {

    }

    /**
     * Creates request url with end point and parameters
     * for entitlement apis.
     * @param endPoint List Entitlement EndPoint
     * @param accessToken User Access Token/ Service Token
     * @param entityReference Entity Reference
     * @return Request URL to be hit
     * @throws StockException if access token is null
     */
    static String createApiURL(final String endPoint, final String accessToken,
            final String entityReference) throws StockException {
        try {

            if (accessToken == null || accessToken.isEmpty()) {
                throw new StockException("Access token can't be"
                        + " null or empty");
            }
            URIBuilder uriBuilder = new URIBuilder(endPoint);
            if (entityReference != null && !entityReference.isEmpty()) {
                uriBuilder.setParameter(ENTITY_REF_PARAM, entityReference);
            }
            return uriBuilder.toString();
        } catch (NullPointerException | IllegalArgumentException
                | URISyntaxException e) {
            throw new StockException(
                    "Could not create the entitlement request url");
        }
    }
}

/**
 * Provides api for listing entitlements and switching accounts.
 */
public final class Entitlements {
    /**
     * Entitlement guid.
     */
    private static final String ENTITLEMENT_GUID_PARAM = "entitlement_guid";
    /**
     * Stock api configuration.
     */
    private StockConfig mConfig;

    /**
     * Constructs an api object for {@link Entitlements}.
     * @param config
     *            stock api configuration
     * @throws StockException
     *             if config is null or not initialized
     * @see StockConfig
     * @see StockException
     */
    public Entitlements(final StockConfig config) throws StockException {
        if (config == null) {
            throw new StockException("Config can't be null");
        }

        this.mConfig = new StockConfig().setApiKey(config.getApiKey())
                .setProduct(config.getProduct())
                .setTargetEnvironment(config.getTargetEnvironment())
                .setProductLocation(config.getProductLocation());
    }

    /**
     * Returns list of all entitlement for an account. Api needs either access
     * token or service token and entity refernce for the same.
     * @param accessToken
     *            User token if entity refernce is null, pass service token if
     *            entity reference is not null
     * @param entityReference so it needs to contain the full string
     * including the @ symbol
     * @return list of all entitlements
     * @throws StockException if createApiUrl throws exception
     */
    public EntitlementList listEntitlements(final String accessToken,
            final String entityReference) throws StockException {
        String requestURL = EntitlementsApiHelper.createApiURL(
                this.mConfig.getEndpoints().getEntitlementListEndPoint(),
                accessToken, entityReference);
        Map<String, String> headers = ApiUtils
                .generateCommonAPIHeaders(this.mConfig, accessToken);
        String responseString = HttpUtils.doGet(requestURL, headers);

        EntitlementList reponse = (EntitlementList) JsonUtils
                .parseJson(EntitlementList.class, responseString);
        return reponse;
    }

    /**
     * It selects entitlement from the list of avaialble entitlements
     * depending on the entitlementGuid, if entitlementGuid is not passsed,
     * personal entitlement would be selected.
     * @param accessToken User/Service Token
     * @param entitlementGuid Which entitlement needs to be selected
     * @param entityReference Used with service token
     * @throws StockException If Api helpers throws exception
     */
    public void selectEntitlement(final String accessToken,
            final String entitlementGuid, final String entityReference)
                    throws StockException {
        String jsonString = "";
        String requestURL = EntitlementsApiHelper.createApiURL(
                this.mConfig.getEndpoints().getSelectEntitlementEndpoint(),
                accessToken, entityReference);
        Map<String, String> headers = ApiUtils
                .generateCommonAPIHeaders(this.mConfig, accessToken);

        if (entitlementGuid != null && !entitlementGuid.isEmpty()) {
            jsonString = "{\"" + ENTITLEMENT_GUID_PARAM + "\":\""
                    + entitlementGuid + "\"}";
        }
        String responseString = HttpUtils.doPost(requestURL, headers,
                jsonString.getBytes(), ContentType.APPLICATION_FORM_URLENCODED);
        if (!responseString.equals(String.valueOf(HttpStatus.SC_NO_CONTENT))) {
            throw new StockException("Stock API returned with an error");
        }
    }
}
