package utils;

public interface FrameworkConstants {
	
	public static final String CONFIG_FILE_PATH = "./Test_Configuration/Config.properties";
	public static final String POSTRequest_AUTH_DEFAULT_REQUEST = "./Test_Data/PostRequest_Auth.txt";
	public static final String DATA_FILE_PATH = "./Test_Data/TestData.xlsx";
	public static final String Valid_Patient_DATA_FILE_PATH ="\\src\\test\\java\\CustomerApp_Package\\Requests_Folder\\patientTestData_AllValidData.json";
	public static final String Valid_Patient2_DATA_FILE_PATH ="\\src\\test\\java\\CustomerApp_Package\\Requests_Folder\\patientTestData_AllValidData2.json";
	public static final String Invalid_Patient_DATA_FILE_PATH ="\\src\\test\\java\\CustomerApp_Package\\Requests_Folder\\patientTestData_All_InvalidData.json";
	
	public static final String Valid_States_File_Path ="\\src\\test\\java\\CustomerApp_Package\\Requests_Folder\\all_Valid_States.json";
	
	public static final String Invalid_States_File_Path ="\\src\\test\\java\\CustomerApp_Package\\Requests_Folder\\invalid_States.json";
	
	public static final String Patient_Registration_Valid_All_Fields_DATA_FILE_PATH ="\\src\\test\\java\\CustomerApp_Package\\Requests_Folder\\patient_registration_Valid_All_Fields.json";
	//Status CODES...
	public static final int StatusCode_422_Unprocessable_Entity = 422;
	public static final int StatusCode_200_OK = 200;
	public static final int StatusCode_201_Create_Entity = 201;
	public static final int StatusCode_500_Internal_Server_Error = 500;
	public static final int StatusCode_401_UnAuthorized_Server_Error = 401;
	
	
	
	// Validation or Error Messages...
	public static final String Mobile_Field_Required_Message ="The mobile field is required.";
	public static final String OTP_Field_Required_Message ="The otp field is required.";
	public static final String Validation_Missing_Message ="Validations are missing.";
	public static final String RequestTypePost ="Post";
	public static final String RequestTypeGet ="Get";
	public static final String Validation_Error_Message ="Sorry! Something went wrong.";
	public static final String Patient_Program_UnAuth_Error_Message="User Not Authorized";
	
	public static final String VerifyOTP_InvalidOTP_Error_Message="Invalid OTP";
	
	// Input Data...
	public static final int Valid_CityId = 343;
	public static final int Valid_ServiceId = 6;
	public static final int Valid_ProductId = 51;
	public static final String Valid_Mobile_Info ="9818625875";
	public static final String Valid_Mobile_Info_ILP = "7417199116";
	public static final String Invalid_Mobile_Info_Empty ="";
	public static final String Invalid_Mobile_Info ="666666";
	public static final String Valid_Mobile_OTP ="456456";
	public static final String Invalid_Mobile_OTP ="212212";
	public static final String Invalid_Empty_OTP ="";
	
	public static final String Valid_Patient_Firstname_Info ="Jatin";
	public static final String Invalid_Patient_Firstname_Info ="";
	public static final String Valid_Patient_Lastname_Info ="Kochhar";
	public static final String Invalid_Patient_Lastname_Info ="";
	public static final String Valid_Patient_Gender_Info ="Male";
	public static final String Invalid_Patient_Gender_Info ="";

	public static final String Base_url ="https://cas-app-qa.hcah.in/";


	// Headers Information...
	
	public static final String Header_Text_HTML_Info ="text/html; charset=UTF-8";
	public static final String Header_Connection_Info ="keep-alive";
	public static final String Header_Server_Apache_Info ="Apache";
	public static final String Header_ContentType_JSON_Info ="application/json";
	public static final String Header_Close_Connection_Info="close";
	public static final String Header_Content_Encoding_Info="gzip";
	public static final String Header_Vary_Info ="Authorization";
	public static final String Header_Vary_Accept_Encoding_Info ="Accept-Encoding";
	
	public static final String Valid_Authorization="Bearer 8254|fRG2dPuGS7AxDtdCDQbjYPGqpvR8VvM0fsdZEN9x";
	public static final String Invalid_Authorization="Bearer wrongauth";

	public static final String Valid_ILP_Authorization = "Bearer 8524|YvdfVfeQnhrrjZ7fbWfkC1WQwjA1cNMpkHptvmTn";


	
}


