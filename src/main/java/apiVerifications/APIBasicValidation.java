package apiVerifications;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.relevantcodes.extentreports.LogStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.ExtentReportListener;

public class APIBasicValidation extends ExtentReportListener {

    public static void statusCodeValidation(Response response, int statusCode) {

        try {
//			test.log(LogStatus.INFO,"Checking Status Code...");
            Assert.assertEquals(statusCode, response.getStatusCode());
            System.out.println("Status code is : " + statusCode);
            test.log(LogStatus.PASS,
                    "Status Code :: " + response.getStatusCode() + " is Validated Successfully!!");
        } catch (AssertionError e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
            test.log(LogStatus.FAIL,
                    "Expected status code is :: " + statusCode + " , instead of getting :: " + response.getStatusCode());
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    public static void headerValidation(Response response, String headerName, String expectedHeaderValue) {

        try {
            Assert.assertEquals(expectedHeaderValue, response.header(headerName));
            test.log(LogStatus.PASS,
                    "Response Header :: " + response.header(headerName) + " is Validated Successfully!!");
        } catch (AssertionError e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
            test.log(LogStatus.FAIL,
                    "Expected Header value is :: " + expectedHeaderValue + " , instead of getting :: " + response.header(headerName));
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    // This JSON Schema file should be saved in test/resources/ here
    public static void jsonResponseSchemaValidation(Response response, String jsonSchemaFileName) {

        try {
            test.log(LogStatus.INFO, "Checking Response Schema and its Types...");
            response.then().assertThat().body(matchesJsonSchemaInClasspath(jsonSchemaFileName));
            test.log(LogStatus.PASS, "Successfully validated Response Tags and its Types");
        } catch (AssertionError e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    public static Object responseKeyValidationfromArray(Response response, String key) {
        try {
            JSONObject obj = null;
            JSONArray array = new JSONArray(response.getBody().asString());
            for (int i = 0; i < array.length(); i++) {
                obj = array.getJSONObject(i);

            }
            test.log(LogStatus.PASS, "Validated values of " + key);
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
        return response;
    }


    public static void responseKeyValidationFromJsonObject(Response response, String key) {
        try {
            JSONObject json = new JSONObject(response.getBody().asString());
            if (json.has(key) && json.get(key) != null) {
                test.log(LogStatus.PASS, "Sucessfully validated value of " + key + " It is " + json.get(key));
            } else {
                test.log(LogStatus.FAIL, "Key is not availble");
            }
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }


    public static void responseTimeValidation(Response response) {
        try {
            long time = response.time();
            test.log(LogStatus.INFO, "Api response time is :: " + time);
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    public static void assertingSingleElementValue(Response response, String jsonPathOfValue, Object expectedValue) {

        try {
            response.then().assertThat().body(jsonPathOfValue, equalTo(expectedValue));
            test.log(LogStatus.PASS, "Response for expected value '" + expectedValue + "' is validated sucessfully!!");
        } catch (AssertionError e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }


    }

    public static void parseObject(JSONObject json, String key) {
        // System.out.println(json.has(key));
//		System.out.println(json.get(key));

        try {
            if (json.has(key) && json.get(key) != null) {
                test.log(LogStatus.PASS, "Sucessfully validated value of '" + key + "', It is :" + json.get(key));
            } else {
                test.log(LogStatus.FAIL, key + " this Key is not available");
            }
        } catch (Exception e) {
            test.log(LogStatus.FAIL, e.fillInStackTrace());
        }
    }

    public static String getKey(JSONObject json, String key) {

        boolean exists = json.has(key);
        Iterator<?> keys;
        String nextKeys;
        String jsonarrayString = "";
        if (!exists) {
            keys = json.keys();
            while (keys.hasNext()) {
                nextKeys = (String) keys.next();
                try {

                    if (json.get(nextKeys) instanceof JSONObject) {


                            getKey(json.getJSONObject(nextKeys), key);


                    } else if (json.get(nextKeys) instanceof JSONArray) {
                        JSONArray jsonarray = json.getJSONArray(nextKeys);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            jsonarrayString = jsonarray.get(i).toString();
                            JSONObject innerJSOn = new JSONObject(jsonarrayString);


                                getKey(innerJSOn, key);


                        }

                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }

            }

        } else {
            parseObject(json, key);
        }

        return jsonarrayString;
    }

    public void response_equals(Response response, Map<String, String> responseFields) {
        // JsonObject json=response.then();
        System.out.println("JsonBody: " + response.then().extract().body().asString());

        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if (StringUtils.isNumeric(field.getValue())) {
                response.then().body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            } else {
                response.then().body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }
    
    
    public static Object extractValueFromResponse(Response response, String jsonPathExpression) {
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getString(jsonPathExpression);
    }
    
    public static Object extractValueFromResponse(Response response, String jsonPathExpression, int index) {
        List<String> values = response.jsonPath().getList(jsonPathExpression);
        if (index >= 0 && index < values.size()) {
            return values.get(index);
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }
    	

    	
    }





