package com.qa.api.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

public class GETAPICall {

    @Test
    public void getUserApiTest() throws IOException {

        Playwright playwright = Playwright.create();
        APIRequest request = playwright.request();
        APIRequestContext requestContext = request.newContext();
        APIResponse apiResponse = requestContext.get("https://gorest.co.in/public/v2/users");

//        Catch status code
        int statusCode = apiResponse.status();
        System.out.println("response status code:" + statusCode);
        Assert.assertEquals(statusCode, 200);

//        Catch status text
        String statusText = apiResponse.statusText();
        System.out.println(statusText);

//        Catch body
        System.out.println("---print api response----");
        apiResponse.body();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResponse = objectMapper.readTree(apiResponse.body());
        String jsonPrettyResponse = jsonResponse.toPrettyString();
        System.out.println(jsonPrettyResponse);

        System.out.println("---print api url----");
        System.out.println(apiResponse.url());

        System.out.println("---print api headers----");
        Map<String, String> headersMap = apiResponse.headers();
        System.out.println(headersMap);
        Assert.assertEquals(headersMap.get("content-type"), "application/json; charset=utf-8");



    }

}
