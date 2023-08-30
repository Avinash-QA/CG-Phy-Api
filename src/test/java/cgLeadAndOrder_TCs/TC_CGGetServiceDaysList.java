package cgLeadAndOrder_TCs;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import apiConfigs.API_Endpoint;
import apiVerifications.APIBasicValidation;
import baseTest.BaseTest;
import io.restassured.RestAssured;
import utils.FrameworkConstants;

public class TC_CGGetServiceDaysList extends BaseTest {
	
	@Test(priority = 1, enabled = true)
	public void GetCGServiceDaysList()
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
         response = RestAssured.given()
                .queryParams("service_id", FrameworkConstants.Valid_ServiceId, "city_id", FrameworkConstants.Valid_CityId)
                .when()
                .get(API_Endpoint.EndPoints.getCGPackageList);
         
		String product_Id = (String) APIBasicValidation.extractValueFromResponse(response, "caregivers[1].items[1].id");
		
//		 response = RestAssured.given()
//	               .queryParams("service_id", FrameworkConstants.Valid_ServiceId, "city_id", FrameworkConstants.Valid_CityId, "product_id", TC_CGGetPackageList.productId)	   
//	               .when()
//	               .get(API_Endpoint.EndPoints.getCGPackageDaysList);// need to check
		
		 response = RestAssured.given()
	               .queryParams("service_id", FrameworkConstants.Valid_ServiceId, "city_id", FrameworkConstants.Valid_CityId, "product_id", product_Id)	   
	               .when()
	               .get(API_Endpoint.EndPoints.getCGPackageDaysList);
		
		 
		 
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		System.out.println(response.asPrettyString());
		
		
        APIBasicValidation.statusCodeValidation(response, 200);
		
        test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		
		
		test.log(LogStatus.INFO, "Checking Json Schema..");
        APIBasicValidation.jsonResponseSchemaValidation(response,"CGServiceDaysSchema.json");
        
        
        test.log(LogStatus.INFO, "Checking response Keys for payload...");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"id");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"days");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"price_per_day");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"main_price");
        
        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

    
		
	}

}
