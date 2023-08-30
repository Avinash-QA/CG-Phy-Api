package utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;


public class RestUtilities extends ExtentReportListener {

    public static String generatePayloadString(String fileName) {
        String filePath = "src/main/resources/payloads/" + fileName;
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Response GetRequest(String uRI) {
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        Response response = requestSpecification.get(uRI);
        System.out.println("End point is: " + uRI);
        test.log(LogStatus.INFO, "End point is: " + uRI);
        requestSpecification.get(uRI).then().and().time(lessThan(1000L));
        System.out.println("Response time is: " + requestSpecification.get(uRI).then().extract().time());
        test.log(LogStatus.INFO, "Response time is: " + requestSpecification.get(uRI).then().extract().time());
        return response;
    }

    public static Response GetRequest(String uRI, String Authorization) {
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.header("Authorization", Authorization);
        System.out.println("Authorization is: " + Authorization);
        test.log(LogStatus.INFO, "Authorization is: " + Authorization);
        Response response = requestSpecification.get(uRI);
        System.out.println("End point is: " + uRI);
        test.log(LogStatus.INFO, "End point is: " + uRI);
        requestSpecification.get(uRI).then().and().time(lessThan(20000L));
        System.out.println("Response time is: " + requestSpecification.get(uRI).then().extract().time());
        test.log(LogStatus.INFO, "Response time is: " + requestSpecification.get(uRI).then().extract().time());
        return response;
    }

    public static Response PostRequest(Map<String, String> payload, String uRI) {
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
     //   System.out.println("Payload is: " + payload);
      //  test.log(LogStatus.INFO, "Payload is: " + payload);
        Response response = requestSpecification.post(uRI);
        System.out.println("End point is: " + uRI);
        test.log(LogStatus.INFO, "End point is: " + uRI);
        requestSpecification.get(uRI).then().and().time(lessThan(20000L));
        System.out.println("Response time is: " + requestSpecification.get(uRI).then().extract().time());
        test.log(LogStatus.INFO, "Response time is: " + requestSpecification.get(uRI).then().extract().time());
        return response;
    }
    


    public static Response PostRequest(String uRI, Map<String, String> payload, String Authorization) {
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        System.out.println("Payload is: " + payload);
        test.log(LogStatus.INFO, "Payload is: " + payload);
        requestSpecification.header("Authorization", Authorization);
        System.out.println("Authorization is: " + Authorization);
        test.log(LogStatus.INFO, "Authorization is: " + Authorization);
        Response response = requestSpecification.post(uRI);
        System.out.println("End point is: " + uRI);
        test.log(LogStatus.INFO, "End point is: " + uRI);
        requestSpecification.get(uRI).then().and().time(lessThan(10000L));
        System.out.println("Response time is: " + requestSpecification.get(uRI).then().extract().time());
        test.log(LogStatus.INFO, "Response time is: " + requestSpecification.get(uRI).then().extract().time());
        return response;
    }

   /* public static Response PostRequest(String uRI, String payload, String Authorization) {
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload);
        System.out.println("Payload is: " + payload);
        test.log(LogStatus.INFO, "Payload is: " + payload);
        requestSpecification.header("Authorization", Authorization);
        System.out.println("Authorization is: " + Authorization);
        test.log(LogStatus.INFO, "Authorization is: " + Authorization);
        Response response = requestSpecification.post(uRI);
        System.out.println("End point is: " + uRI);
        test.log(LogStatus.INFO, "End point is: " + uRI);
        requestSpecification.get(uRI).then().and().time(lessThan(20000L));
        System.out.println("Response time is: " + requestSpecification.get(uRI).then().extract().time());
        test.log(LogStatus.INFO, "Response time is: " + requestSpecification.get(uRI).then().extract().time());
        return response;
    }*/

    public static Response PostRequestwithFile(String uRI, String payload, String Authorization) {
        File file = new File("C:/Users/admin/Downloads/sample.pdf");

        RequestSpecification requestSpecification = given();
        requestSpecification.multiPart("file", file, "multipart/form-data");
//        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body("{\"question_id\": 1, \"answer\": \"" + file + "15\"}");
        System.out.println("Payload is: " + payload);
        test.log(LogStatus.INFO, "Payload is: " + payload);
        requestSpecification.header("Authorization", Authorization);
        System.out.println("Authorization is: " + Authorization);
        test.log(LogStatus.INFO, "Authorization is: " + Authorization);
        Response response = requestSpecification.post(uRI);
        System.out.println("End point is: " + uRI);
        test.log(LogStatus.INFO, "End point is: " + uRI);
        requestSpecification.get(uRI).then().and().time(lessThan(10000L));
        System.out.println("Response time is: " + requestSpecification.get(uRI).then().extract().time());
        test.log(LogStatus.INFO, "Response time is: " + requestSpecification.get(uRI).then().extract().time());
        return response;
    }

    public static Response GetRequest(String uRI, String Authorization, int Params) {
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.pathParam("order_id", Params);
        System.out.println("order_id is : " + Params);
        requestSpecification.header("Authorization", Authorization);
        System.out.println("Authorization is : " + Authorization);
        test.log(LogStatus.INFO, "Authorization is: " + Authorization);
        Response response = requestSpecification.get(uRI);
        System.out.println("End point is: " + uRI);
        test.log(LogStatus.INFO, "End point is: " + uRI);
        requestSpecification.get(uRI).then().and().time(lessThan(10000L));
        System.out.println("Response time is: " + requestSpecification.get(uRI).then().extract().time());
        test.log(LogStatus.INFO, "Response time is: " + requestSpecification.get(uRI).then().extract().time());
        return response;

    }
    
    
    public static Response PostRequest(String payload, String uRI) {
        RequestSpecification requestSpecification = given();
        requestSpecification.contentType(ContentType.JSON)
        .header("Cache-Control", "no-store")  // Set no-store cache control header
        .header("Set-Cookie", "")  
        .body(payload);
        
        System.out.println("Payload is: " + payload);
        test.log(LogStatus.INFO, "Payload is: " + payload);
        
        Response response = requestSpecification.post(uRI);
        
        System.out.println("End point is: " + uRI);
        test.log(LogStatus.INFO, "End point is: " + uRI);
        
        // Capture response time from the Response object
        long responseTime = response.time();
        
        System.out.println("Response time is: " + responseTime);
        test.log(LogStatus.INFO, "Response time is: " + responseTime);
        
        return response;
    }
    
    public static Response GetRequest(String baseUrl, String pathParamKey,String pathParamValue, String queryParamKey, String queryParamValue) {
        Response response = RestAssured.given()
                .pathParam(pathParamKey, pathParamValue)
                .queryParam(queryParamKey, queryParamValue)
                .when()
                .get(baseUrl);

        return response;
    }
}

