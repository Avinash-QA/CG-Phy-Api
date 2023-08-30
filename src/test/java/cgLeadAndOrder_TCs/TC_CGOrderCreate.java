package cgLeadAndOrder_TCs;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relevantcodes.extentreports.LogStatus;

import DataProvider.DP;
import apiConfigs.API_Endpoint;
import apiVerifications.APIBasicValidation;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_CGOrderCreate extends BaseTest{
	
	String Web_lead_id;
	String city_id;
	String Service_daysId;
	String Start_Date;
	
	@Test(dataProvider = "CGLeadCreateValidPayload", dataProviderClass = DP.class, priority = 1, enabled = true)
	public void CreateCGLeadWithValidPayload(HashMap<String, Object>payload) throws JsonProcessingException
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
	    String CGLeadCreateDataInJsonString = utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);
	    System.out.println(CGLeadCreateDataInJsonString);

		response = RestAssured.given().
				contentType("application/json").body(CGLeadCreateDataInJsonString).
				when().
				post(API_Endpoint.EndPoints.createCGLead);
		
		System.out.println(response.asPrettyString());
		
		Web_lead_id = (String) APIBasicValidation.extractValueFromResponse(response, "web_lead_id");
	    city_id = (String) APIBasicValidation.extractValueFromResponse(response, "city_id");
		
		
        response = RestAssured.given()
                .queryParams("service_id", FrameworkConstants.Valid_ServiceId, "city_id", city_id, "product_id", FrameworkConstants.Valid_ProductId)
                .when()
                .get(API_Endpoint.EndPoints.getCGPackageDaysList);
        
        Service_daysId = (String) APIBasicValidation.extractValueFromResponse(response, "packages[0].id");
        Start_Date = (String) APIBasicValidation.extractValueFromResponse(response, "start_date");
        
	}
		
    @Test(dataProvider = "CGOrderCreateValidPayload", dataProviderClass = DP.class, priority = 2, enabled = true)
    public void CreateCGOrderWithValidPayload(HashMap<String, Object>payload) throws JsonProcessingException
    { 
    	test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
    		
        payload.put("lead_id", Web_lead_id);
        payload.put("city_id", city_id);
        @SuppressWarnings("unchecked")
		Map<String, Object> extra = (Map<String, Object>) payload.get("extra");
        extra.put("id", Service_daysId);
        extra.put("date", Start_Date);

        // Update the modified "extra" object back into the payload
        payload.put("extra", extra);
        
		String CGOrderCreateDataInJsonString = new ObjectMapper().writeValueAsString(payload);
		System.out.println(CGOrderCreateDataInJsonString);
        
		response = RestAssured.given().
				contentType("application/json").body(CGOrderCreateDataInJsonString).
				when().
				post(API_Endpoint.EndPoints.CreateCGOrder);
		
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		System.out.println(response.asPrettyString());
		
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		
		test.log(LogStatus.INFO, "Checking Json Schema..");
        APIBasicValidation.jsonResponseSchemaValidation(response,"CGOrderCreateSchema.json");
		
        test.log(LogStatus.INFO, "Checking response Keys for payload...");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"id");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"type");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"date");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"payment_gateway");

        
        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

		
	}

}
