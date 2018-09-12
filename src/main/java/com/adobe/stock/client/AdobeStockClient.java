/*******************************************************************************
 * Copyright 2017 Adobe Systems Incorporated. All rights reserved.
 * This file is licensed to you under the Apache License, Version 2.0
 * (the "License") you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/
package com.adobe.stock.client;

import java.util.ArrayList;

import com.adobe.stock.apis.Entitlements;
import com.adobe.stock.apis.License;
import com.adobe.stock.apis.LicenseHistory;
import com.adobe.stock.apis.SearchCategory;
import com.adobe.stock.apis.SearchFiles;
import com.adobe.stock.config.StockConfig;
import com.adobe.stock.enums.AssetLicenseState;
import com.adobe.stock.enums.AssetPurchaseState;
import com.adobe.stock.enums.Environment;
import com.adobe.stock.enums.LicenseHistoryResultColumn;
import com.adobe.stock.enums.ResultColumn;
import com.adobe.stock.exception.StockException;
import com.adobe.stock.models.LicenseReference;
import com.adobe.stock.models.LicenseRequest;
import com.adobe.stock.models.LicenseResponse;
import com.adobe.stock.models.SearchCategoryRequest;
import com.adobe.stock.models.SearchFilesRequest;
import com.adobe.stock.models.SearchFilesResponse;
import com.adobe.stock.models.EntitlementList;
import com.adobe.stock.models.LicenseHistoryRequest;
import com.adobe.stock.models.SearchParametersLicenseHistory;
import com.adobe.stock.models.LicenseHistoryResponse;
import com.adobe.stock.models.SearchParameters;
import com.adobe.stock.models.StockFileCategory;

public class AdobeStockClient {

    public static void testSearchFiles() throws StockException {

        try {
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0")
                    .setTargetEnvironment(Environment.STAGE)
                    .setProductLocation("libraries/2.10");
            ResultColumn[] columns = { ResultColumn.ID,
                    ResultColumn.MEDIA_TYPE_ID, ResultColumn.NB_RESULTS,
                    ResultColumn.WIDTH, ResultColumn.COUNTRY_NAME, ResultColumn.IS_EDITORIAL };
            SearchParameters params = new SearchParameters().setWords("dogs")
                    .setFilterEditorial(true).setLimit(10).setOffset(10);
            SearchFilesRequest request = new SearchFilesRequest()
                    .setSearchParams(params).setResultColumns(columns);
            SearchFiles searchFile = new SearchFiles(config, null, request);
            SearchFilesResponse response = searchFile.getNextResponse();
            System.out.println("Search Files Response:");
            print("total results", response.getNbResults());
            print("id", response.getFiles().get(0).getId());
            print("asset id", response.getFiles().get(0).getAssetTypeId());
            print("width", response.getFiles().get(0).getWidth());
            print("country", response.getFiles().get(0).getCountryName());
            print("editorial", response.getFiles().get(0).getIsEditorial());
            System.out.println("");
        } catch (Exception e) {
                    System.out.println(e);

            throw new StockException("error in search files");
        }
    }

    public static void testSearchCategory() throws StockException{
        try {
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            SearchCategoryRequest request = new SearchCategoryRequest()
                    .setCategoryId(1043);
            SearchCategory searchCategory = new SearchCategory(config);
            StockFileCategory cat = searchCategory.getCategory(request);
            System.out.println("Category Response:");
            print("name", cat.getName());
            print("link", cat.getLink());
            print("id", cat.getId());
            System.out.println("");

            ArrayList<StockFileCategory> catTree = searchCategory
                    .getCategoryTree(request);
            System.out.println("\nCategoryTree Response:");
            print("name", catTree.get(0).getName());
            print("link", catTree.get(0).getLink());
            print("id", catTree.get(0).getId());
        } catch (Exception e) {
            throw new StockException("error in search category");
        }
    }

    public static void testContentInfo() throws StockException{
        try {
            String accessToken = "test";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            LicenseRequest request = new LicenseRequest()
                    .setContentId(84071201);
            License license = new License(config);
            LicenseResponse licenseResponse = license.getContentInfo(request,accessToken);
            System.out.println("Content Info Response:");
            print("Content id",licenseResponse.getContents().get(0).getContentId());
            print("Content purchase state",licenseResponse.getContents().get(0).getPurchaseDetails().getPurchaseState());
        } catch (Exception e) {
            throw new StockException("error in license api");
        }
    }

    public static void testContentLicensePost() throws StockException{
        try {
            String accessToken = "test";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            LicenseReference ref = new LicenseReference();
            ref.setLicenseReferenceId(1).setLicenseReferenceValue("Trees");
            LicenseReference refArray[] = {ref};
            LicenseRequest request = new LicenseRequest().setContentId(84071201)
                    .setLocale("en-US").setLicenseState(AssetLicenseState.EXTENDED).setLicenseReference(refArray)
                    .setPurchaseState(AssetPurchaseState.PURCHASED);
            License license = new License(config);
            LicenseResponse licenseResponse = license.getContentLicense(request,accessToken);
            System.out.println("Content Info Response:");
            print("Content id",licenseResponse.getContents().get(0).getContentId());
            print("Content purchase state",licenseResponse.getContents().get(0).getPurchaseDetails().getPurchaseState());
        } catch (Exception e) {
            throw new StockException("error in license api");
        }
    }

    public static void testMemberInfo() throws StockException{
        try{
            String accessToken = "test";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            LicenseRequest request = new LicenseRequest()
                    .setContentId(84071201).setLicenseState(AssetLicenseState.STANDARD);
            License license = new License(config);
            LicenseResponse response = license.getMemberProfile(request, accessToken);
            System.out.println("Member Info Response:");
            print("Entitlement Quota:",response.getEntitlement().getQuota() );
            print("Purchase Options Message", response.getPurchaseOptions().getMessage());
        } catch (Exception e) {
            throw new StockException("error in license api");
        }
    }

    public static void testDownloadAsset() throws StockException{
        try{
            String accessToken = "test";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            LicenseRequest request = new LicenseRequest()
                    .setContentId(117425772).setLicenseState(AssetLicenseState.STANDARD);
            License license = new License(config);
            String assetUrl = license.downloadAsset(request, accessToken);
            System.out.println("Asset Download Response:");
            print("Asset URL", assetUrl);
        } catch (Exception e) {
            throw new StockException("error in downloading");
        }
    }

    public static void testLicenseHistory() throws StockException{

        try {
            String accesstoken = "test";
            StockConfig config = new StockConfig()
                    .setApiKey("LucaIOS1")
                    .setProduct("Spark Page")
                    .setTargetEnvironment(Environment.STAGE)
                    .setProductLocation("Libraries/1.0.0 ");
            LicenseHistoryResultColumn[] columns = { LicenseHistoryResultColumn.THUMBNAIL_1000_HEIGHT,
                    LicenseHistoryResultColumn.THUMBNAIL_1000_URL,
                    LicenseHistoryResultColumn.THUMBNAIL_1000_WIDTH };
            SearchParametersLicenseHistory params = new SearchParametersLicenseHistory()
                    .setLimit(2).setOffset(0);
            LicenseHistoryRequest request = new LicenseHistoryRequest()
                    .setSearchParams(params).setResultColumns(columns);
            LicenseHistory licensehistory = new LicenseHistory(config, accesstoken, request);
            LicenseHistoryResponse response = licensehistory.getNextLicenseHistory();
            print("total results", response.getNbResults());
            print("license date", response.getFiles().get(0).getLicenseDate());
            print("license state", response.getFiles().get(0).getLicenseState());
            print("width", response.getFiles().get(0).getWidth());
            print("content type", response.getFiles().get(0).getContentType());
            print("page index",licensehistory.currentLicenseHistoryPageIndex());
            licensehistory.getNextLicenseHistory();
            licensehistory.getNextLicenseHistory();
            print("page index",licensehistory.currentLicenseHistoryPageIndex());
            System.out.println("");
        } catch (Exception e) {
            throw new StockException("error in license history");
        }
    }

    public static void testListEntitlements() throws StockException{
        try{
            String accessToken = "test";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            Entitlements api = new Entitlements(config);
            EntitlementList response = api.listEntitlements(accessToken, null);
            System.out.println("Entitlement List Response:");
            print("Entitlement Organization Id:",response.getOrganizations().get(0).getId());
            print("Entitlement Id", response.getOrganizations().get(0).getEntitlements().get(0).getEntitlementId());
        } catch (Exception e) {
            throw new StockException("error in entitlement api");
        }
    }

    public static void testSelectEntitlement() throws StockException{
        try{
            String accessToken = "test";
            StockConfig config = new StockConfig()
                    .setApiKey("AdobeStockClient1")
                    .setProduct("Adobe Stock Lib/1.0.0");
            Entitlements api = new Entitlements(config);
            EntitlementList response = api.listEntitlements(accessToken, null);
            String guid = response.getOrganizations().get(1).getEntitlements().get(0).getGuid();
            api.selectEntitlement(accessToken, guid, "");
            System.out.println("Entitlement has been selected");
        } catch (Exception e) {
            throw new StockException("Error in entitlement select api");
        }
    }
    
    public static void print(String key, Object val) {
        System.out.println(key + " : " + val.toString());
    }
    public static void main(String[] args) throws StockException {
        testSearchFiles();
    }

}
