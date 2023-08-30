package apiConfigs;

public class API_Endpoint {
	
	public static final class EndPoints{
		
		//post API
		public static final String createCGLead = "api/v1/lead/create/website";
		
		public static final String getCGPackageList = "api/v2/site/care/service";
		
		public static final String getCGPackageDaysList = "api/v2/site/care/product";
		
		public static final String CreateCGOrder = "api/v2/site/care/order";
		
		public static final String getPhysioServiceDaysList = "api/v2/site/care/available-days/{cityId}";
		
		public static final String getPhysioServiceSlots = "api/v2/site/care/available-slots";
		
		public static final String getPhysioList = "api/v2/site/care/available-physio";
		
		public static final String CreatePhysioOrder = "api/v2/site/care/order";
		

		
		
	}

}
