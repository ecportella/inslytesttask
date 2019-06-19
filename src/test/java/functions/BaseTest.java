package functions;

import java.lang.reflect.Method;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.screenshot;


public class BaseTest {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void setUp(ITestContext context) {
//		Report configuration
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/Report Insly Test Task.html");
		htmlReporter.config().setDocumentTitle("Insly Report");
		htmlReporter.config().setReportName("Insly Demo Instance Test");
		htmlReporter.config().setEncoding("ISO-8859-1");
		htmlReporter.config().setTheme(Theme.DARK);		
		
		extent = new ExtentReports();
		extent.setSystemInfo("Company", "Insly");
		extent.setSystemInfo("Project", "Insly Demo Instance");
		extent.setSystemInfo("Author", "Eugenio Portella");
		extent.attachReporter(htmlReporter);
	}
	
	@BeforeMethod
	public void beforeMethod(Method method, ITestResult result) {
	    test = extent.createTest(result.getMethod().getDescription()).assignCategory(result.getMethod().getGroups());
		
//		Tests configurations
//	    Configuration.headless = true;
//	    Configuration.holdBrowserOpen = true;
	    Configuration.startMaximized = true;
	    Configuration.timeout = 80000;
		
//		Tests on URL
		open("https://signup.int.staging.insly.training/signup");
		
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
//		Define whether the execution succeeded, failed, or was blocked
		if(result.getStatus() == ITestResult.FAILURE) {	
			test.fail(MarkupHelper.createLabel("Test "+result.getName()+" failed", ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail("Screenshot can be seen below: ").addScreenCaptureFromPath(screenshot(result.getMethod().getDescription()+ " " +result.getStartMillis()));
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
			test.pass(MarkupHelper.createLabel("Test "+result.getName()+" executed successfully", ExtentColor.GREEN));
		}
		else if(result.getStatus() == ITestResult.SKIP) {
			test.skip(MarkupHelper.createLabel("Test "+result.getName()+" blocked", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}

//		Close WebDriver
		WebDriverRunner.closeWebDriver();
		
	}
	
	@AfterSuite
	public void tearDown() {
//		Create the report
		extent.flush();
	}
	
	
//	Function defined to click one element and log it to test report
	public static void click(String element, SelenideElement selector) {
		try {
			selector.click();
			test.log(Status.PASS, element + " clicked.");
		} catch (com.codeborne.selenide.ex.ElementNotFound e) {
			test.log(Status.FAIL, "Could not click: " + element);
			throw(e);
		}
	}
	
//	Function defined to type on one element and log it to test report	
	public static void type(String element, SelenideElement selector, String content) {
		try {
			selector.sendKeys(content);
			test.log(Status.PASS, element + " filled.");
		} catch (com.codeborne.selenide.ex.ElementNotFound e) {
			test.log(Status.FAIL, "Could not fill: " + element);
			throw(e);
		}
	}


	public static void select(String element, SelenideElement selector, String content) {
		try {
			selector.selectOptionContainingText(content);
			test.log(Status.PASS, "Selection made on " + element);
		} catch (com.codeborne.selenide.ex.ElementNotFound e) {
			test.log(Status.FAIL, "Could not make selection on: " + element);
			throw(e);
		}
	}

}

