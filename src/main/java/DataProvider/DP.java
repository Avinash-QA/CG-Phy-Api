package DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DP {
	
	String filePath=System.getProperty("user.dir")+"/Requests/";
	
	
	@DataProvider(name = "CGLeadCreateValidPayload")
	public Object[] payload1() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"CGLeadCreateValidPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "CGOrderCreateValidPayload")
	public Object[] payload2() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"CGOrderCreateValidPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "PhysioLeadCreateValidPayload")
	public Object[] payload3() throws IOException{
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"PhysioLeadCreateValidPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "PhysioAvailableSlots")
	public Object[] payload4() throws IOException {
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"PhysioAvailableSlots.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "PhysioAvailabiltyPayload")
	public Object[] payload5() throws IOException {
		HashMap<String,Object> payloadData=new ObjectMapper().readValue(new File(filePath+"PhysioAvailabiltyPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
	@DataProvider(name = "PhysioOrderCreateValidPayload")
	public Object[] payload6() throws IOException {
		HashMap<String,Object>payloadData=new ObjectMapper().readValue(new File(filePath+"PhysioOrderCreateValidPayload.json"), new TypeReference<HashMap<String,Object>>() {});
		return new Object[] {payloadData};
	}
	
}

	


