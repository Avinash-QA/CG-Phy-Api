package cgLeadAndOrder_TCs;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiVerifications.APIBasicValidation;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.FrameworkConstants;

public class TC_CGGetPackageList extends BaseTest {
	
	public static String productId;
	
	@Test(priority = 1, enabled = true)
	public void getCGPackageList()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		

	    response = RestAssured.given()
               .queryParams("service_id", FrameworkConstants.Valid_ServiceId, "city_id", FrameworkConstants.Valid_CityId)
               .when()
               .get(API_Endpoint.EndPoints.getCGPackageList);
	    
	    productId = response.jsonPath().getString("caregivers[1].items[1].id");
	    
	    test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		System.out.println(response.asPrettyString());
		
		APIBasicValidation.statusCodeValidation(response, 200);
		
        test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		
		test.log(LogStatus.INFO, "Checking Json Schema..");
        APIBasicValidation.jsonResponseSchemaValidation(response,"CGPackageListSchema.json");
        
        test.log(LogStatus.INFO, "Checking response Keys for payload...");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"id");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"name");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"description");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"price");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"mode");
        
        
        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

  

}
}
