package physioLeadAndOrder_TCs;

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
import utils.FrameworkConstants;
import utils.RestUtilities;

public class TC_ServiceAvailableDates extends BaseTest {
	
	String date;
	String city_Id;
	String WebLeadId;
	String lead_Id;
	String id;
	String extra_Id;
	String extra_slot_Id;
	
	@Test(dataProvider = "PhysioLeadCreateValidPayload", dataProviderClass = DP.class, priority = 1, enabled = true)
	public void getPhysioServiceDaysList(HashMap<String, Object>payload) throws JsonProcessingException 
	{
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		String PhysioLeadCreateDataInJsonString = utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);

		response = RestUtilities.PostRequest(PhysioLeadCreateDataInJsonString, API_Endpoint.EndPoints.createCGLead);
		
		city_Id = (String) APIBasicValidation.extractValueFromResponse(response, "city_id");
		lead_Id = (String) APIBasicValidation.extractValueFromResponse(response, "lead_id");
		WebLeadId = (String) APIBasicValidation.extractValueFromResponse(response, "web_lead_id");
		
		response = RestUtilities.GetRequest(API_Endpoint.EndPoints.getPhysioServiceDaysList, "cityId", city_Id, "lead_Id", lead_Id );
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		System.out.println(response.asPrettyString());
		
		date = (String) APIBasicValidation.extractValueFromResponse(response, "dates", 1);
		System.out.println(date);
		System.out.println(WebLeadId);
		
		APIBasicValidation.statusCodeValidation(response, 200);
			
	    test.log(LogStatus.INFO,"Checking Response Headers..");
	    APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
	    APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
	    APIBasicValidation.getKey(new JSONObject(response.asString()),"dates");
	    
        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");
 

	}
	
	@Test(dataProvider = "PhysioAvailableSlots", dataProviderClass = DP.class, priority = 2, enabled = true)
	public void getPhysioAvailableSlots(HashMap<String,Object>payload) throws JsonProcessingException
	{
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		payload.put("date", date );
		payload.put("city_id", city_Id);
		payload.put("lead_id", WebLeadId);
		
		String getAvailableSlotsDataInJsonString = new ObjectMapper().writeValueAsString(payload);
		
		response = RestUtilities.PostRequest(getAvailableSlotsDataInJsonString, API_Endpoint.EndPoints.getPhysioServiceSlots);
		
		id = response.jsonPath().getString("slots.find { it.availability == true }.id");
		System.out.println("ID is- "+id);
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		System.out.println(response.asPrettyString());
		
        APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
	    APIBasicValidation.getKey(new JSONObject(response.asString()),"id");
	    APIBasicValidation.getKey(new JSONObject(response.asString()),"availability");

		
	}
	
	@Test(dataProvider = "PhysioAvailabiltyPayload", dataProviderClass = DP.class, priority = 3, enabled = true)
	public void getAvailablePhysioList(HashMap<String,Object>payload) throws JsonProcessingException {
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		payload.put("city_Id", city_Id );
		payload.put("lead_id", WebLeadId);
		payload.put("date", date);
		payload.put("slot_id", id);
		
		String PhysioListDataInJsonString = new ObjectMapper().writeValueAsString(payload);
		
		response = RestUtilities.PostRequest(PhysioListDataInJsonString, API_Endpoint.EndPoints.getPhysioList);
		
		extra_Id = (String) APIBasicValidation.extractValueFromResponse(response, "physiotherapist.id");
		extra_slot_Id = (String) APIBasicValidation.extractValueFromResponse(response, "physiotherapist.slot_id");
		System.out.println(extra_Id+" "+ extra_slot_Id);
		
		APIBasicValidation.statusCodeValidation(response, 200);

		
	}
	
	@Test(dataProvider = "PhysioOrderCreateValidPayload", dataProviderClass = DP.class, priority = 4, enabled = true)
	public void CreatePhysioOrderWithValidPayload(HashMap<String,Object>payload) throws JsonProcessingException {
		
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		payload.put("city_id", city_Id );
		payload.put("lead_id", WebLeadId );
		@SuppressWarnings("unchecked")
		Map<String, Object> extra = (Map<String, Object>) payload.get("extra");
		extra.put("date", date );
		extra.put("master_slot_id", id );
		extra.put("id", extra_Id);
		extra.put("slot_id", extra_slot_Id);
		
		 // Update the modified "extra" object back into the payload
        payload.put("extra", extra);
		
		String PhysioOrderCreateDataInJsonString = new ObjectMapper().writeValueAsString(payload);
		
		response = RestUtilities.PostRequest(PhysioOrderCreateDataInJsonString, API_Endpoint.EndPoints.CreatePhysioOrder);
		System.out.println(response);
		
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_ContentType_JSON_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		
		test.log(LogStatus.INFO, "Checking response Keys for payload...");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"id");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"type");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"date");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"payment_gateway");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"display");


        
        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");


		
	}
	

}
