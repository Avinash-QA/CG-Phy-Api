package baseTest;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.observer.entity.ReportEntity;

import io.reactivex.rxjava3.subjects.PublishSubject;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.ExtentReportListener;
import utils.FileAndEnv;

@Listeners(ExtentReportListener.class)
public class BaseTest extends ExtentReportListener{
	
	protected static RequestSpecification httpRequest;
	protected static ResponseSpecification responseSpec;
	protected static Response response;
	protected static ExtentTest testClass;
	protected static String validAuthorization;
	
	@BeforeSuite
	public void baseTest()
	{
		//System.out.println(FileAndEnv.readConfigurationFile());
		RestAssured.registerParser("text/html", Parser.JSON);
		RestAssured.baseURI = FileAndEnv.readConfigurationFile().get("CAS_Base_URI");
	}

}
