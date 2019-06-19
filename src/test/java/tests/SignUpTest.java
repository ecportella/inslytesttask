package tests;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Condition.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import functions.BaseTest;
import pageObjects.SignUpPage;

public class SignUpTest extends BaseTest {

	@Test(groups = "Insly Test Task", description = "Sign Up on Insly Demo Instance test")
	public void signUpTest() {
		test.log(Status.PASS, MarkupHelper.createLabel("Sign Up test started", ExtentColor.GREEN));

		//Generating Data for the test
		String generatedCompanyName = "TestCompany"+Math.round(Math.random()*1000);
		String generatedInslyAddress = generatedCompanyName.toLowerCase();
		String generatedWorkEmail = generatedCompanyName.toLowerCase()+"@test.com";
		test.log(Status.PASS, "Data for the tests generated successfully.");

		//Waiting for the title 'Sign Up and Start Using'
		SignUpPage.waitTitleSignUpAndStartUsing();

		//Testing if the components are present on the page
		SignUpPage.companyName.exists();
		SignUpPage.country.exists();
		SignUpPage.yourInslyAddress.exists();
		SignUpPage.companyProfile.exists();
		SignUpPage.numberOfEmployees.exists();
		SignUpPage.howWouldYouDescribeYourself.exists();
		SignUpPage.workEmail.exists();
		SignUpPage.accountManagerName.exists();
		SignUpPage.password.exists();
		SignUpPage.passwordRepeat.exists();
		SignUpPage.suggestSecurePassword.exists();
		SignUpPage.phone.exists();
		SignUpPage.termsAndConditionsCheckbox.exists();
		SignUpPage.termsAndConditionsLink.exists();
		SignUpPage.privacyPolicyCheckbox.exists();
		SignUpPage.privacyPolicyLink.exists();
		SignUpPage.processingAndStoragePersonalDataCheckbox.exists();
		SignUpPage.signUpBtn.exists();
		test.log(Status.PASS, "Test of presence of the components performed successfully.");

		//Filling the company section
		SignUpPage.fillCompanyName(generatedCompanyName);
		SignUpPage.selectCountry("Brazil");
		SignUpPage.selectCompanyProfile("Software Development Company");
		SignUpPage.selectNumberOfEmployees("6-10");
		SignUpPage.selectHowWouldYouDescribeYourself("I am a tech guy");

		//Testing the data filled in the company section
		SignUpPage.companyName.shouldHave(value(generatedCompanyName));
		SignUpPage.country.shouldHave(value("BR"));
		SignUpPage.yourInslyAddress.shouldHave(value(generatedInslyAddress));
		SignUpPage.companyProfile.shouldHave(value("SDC"));
		SignUpPage.numberOfEmployees.shouldHave(value("10"));
		SignUpPage.howWouldYouDescribeYourself.shouldHave(value("tech"));
		test.log(Status.PASS, "Test of the data filled in the company section performed successfully.");

		//Filling the administrator account details section
		SignUpPage.fillWorkEmail(generatedWorkEmail);
		SignUpPage.fillAccountManagerName("Test Name");
		SignUpPage.clickSuggestSecurePassword();
		String generatedPassword = SignUpPage.passwordSuggested.getText(); //Getting the suggested password
		SignUpPage.clickSuggestSecurePasswordBtnOk();
		SignUpPage.fillPhoneNumber("35988770484");

		//Testing the data filled in the administrator account details section
		SignUpPage.workEmail.shouldHave(value(generatedWorkEmail));
		SignUpPage.accountManagerName.shouldHave(value("Test Name"));
		SignUpPage.password.shouldNot(empty);
		SignUpPage.passwordRepeat.shouldNot(empty);
		SignUpPage.phone.shouldHave(value("+5535988770484"));
		test.log(Status.PASS, "Test of the data filled in the administrator account section performed successfully.");

		//Interacting with Terms and conditions section
		SignUpPage.clickTermsAndConditionsCheckbox();
		SignUpPage.clickPrivacyPolicyCheckbox();
		SignUpPage.clickProcessingAndStoragePersonalDataCheckbox();
		SignUpPage.clickTermsAndConditionsLink();
		SignUpPage.clickTermsAndConditionsAgreeBtn();
		SignUpPage.clickPrivacyPolicyLink();
		SignUpPage.scrollDownToPrivacyPolicyEnd();
		SignUpPage.clickClosePrivacyPolicyWindow();

		//Testing if the Sign Up button is enabled and then clicking on it
		SignUpPage.signUpBtn.shouldBe(enabled);
		SignUpPage.clickSignUpBtn();
		test.log(Status.PASS, "Test of the Sign Up btn performed successfully.");

		//Testing if the building system is creating my demo instance
		SignUpPage.buildingSystemMessage.exists();
		test.log(Status.PASS, "Test of the Buinding System creating the demo instance performed successfully.");

		//Filling the login form
		SignUpPage.fillEmailLogin(generatedWorkEmail);
		SignUpPage.fillPasswordLogin(generatedPassword);
		SignUpPage.clickLoginBtn();

		//Testing if the user is logged in
		SignUpPage.userInfo.shouldHave(text("Test Name"));
		test.log(Status.PASS, "Test of the User Logged In performed successfully.");

		//Testing if the current URL is the same shown on step 2 (but the training one)
		String url = WebDriverRunner.url();
		Assert.assertEquals(url, "https://"+generatedCompanyName.toLowerCase()+".int.staging.insly.training/dashboard");
		test.log(Status.PASS, "Test of the URL performed successfully.");
    }
}
