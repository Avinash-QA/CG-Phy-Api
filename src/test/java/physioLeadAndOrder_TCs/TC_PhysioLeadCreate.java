package physioLeadAndOrder_TCs;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import DataProvider.DP;
import apiConfigs.API_Endpoint;
import apiVerifications.APIBasicValidation;
import baseTest.BaseTest;
import utils.FrameworkConstants;
import utils.RestUtilities;

public class TC_PhysioLeadCreate extends BaseTest {
	
	@Test(dataProvider = "PhysioLeadCreateValidPayload", dataProviderClass = DP.class, priority = 1, enabled = true)
	public void VerifyPhysioLeadCreateWithValidPayload(HashMap<String, Object>payload) throws JsonProcessingException
	{
		test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Started... <<< ");
		
		String PhysioLeadCreateDataInJsonString = utils.ParseDynamicJson.convertObjectArraytoJSONString(payload);
		
		response = RestUtilities.PostRequest(PhysioLeadCreateDataInJsonString, API_Endpoint.EndPoints.createCGLead);
		
		test.log(LogStatus.INFO,"Response Value :: "+ response.asPrettyString());
		System.out.println(response.asPrettyString());
		
		APIBasicValidation.statusCodeValidation(response, 200);
		
		test.log(LogStatus.INFO,"Checking Response Headers..");
		APIBasicValidation.headerValidation(response,"Content-Type",FrameworkConstants.Header_Text_HTML_Info);
		APIBasicValidation.headerValidation(response,"Server",FrameworkConstants.Header_Server_Apache_Info);
		APIBasicValidation.headerValidation(response,"Connection",FrameworkConstants.Header_Connection_Info);
		
		test.log(LogStatus.INFO, "Checking Json Schema..");
        APIBasicValidation.jsonResponseSchemaValidation(response,"PhysioLeadCreateSchema.json");
        
        test.log(LogStatus.INFO, "Checking response Keys for payload...");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"city_id");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"lead_id");
        APIBasicValidation.getKey(new JSONObject(response.asString()),"web_lead_id");
        
        test.log(LogStatus.INFO, " >>> TC-"+Thread.currentThread().getStackTrace()[1] .getMethodName()+" Ended... <<< ");

        

		
	}
	

}
