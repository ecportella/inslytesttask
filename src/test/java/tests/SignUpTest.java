package tests;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;

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
		Faker faker = new Faker();
		String companyName = faker.company().name();
		String inslyAddress = companyName.replaceAll("[, ]", "").toLowerCase();
		String workEmail = faker.internet().emailAddress();
		String accountManagerName = faker.name().name();
		String phoneNumber = faker.phoneNumber().cellPhone();

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
		SignUpPage.fillCompanyName(companyName);
		SignUpPage.selectCountry("Brazil");
		SignUpPage.selectCompanyProfile("Software Development Company");
		SignUpPage.selectNumberOfEmployees("6-10");
		SignUpPage.selectHowWouldYouDescribeYourself("I am a tech guy");

		//Testing the data filled in the company section
		SignUpPage.companyName.shouldHave(value(companyName));
		SignUpPage.country.shouldHave(value("BR"));
		SignUpPage.yourInslyAddress.shouldHave(value(inslyAddress));
		SignUpPage.companyProfile.shouldHave(value("SDC"));
		SignUpPage.numberOfEmployees.shouldHave(value("10"));
		SignUpPage.howWouldYouDescribeYourself.shouldHave(value("tech"));
		test.log(Status.PASS, "Test of the data filled in the company section performed successfully.");

		//Filling the administrator account details section
		SignUpPage.fillWorkEmail(workEmail);
		SignUpPage.fillAccountManagerName(accountManagerName);
		SignUpPage.clickSuggestSecurePassword();
		String generatedPassword = SignUpPage.passwordSuggested.getText(); //Getting the suggested password
		SignUpPage.clickSuggestSecurePasswordBtnOk();
		SignUpPage.fillPhoneNumber(phoneNumber);

		//Testing the data filled in the administrator account details section
		SignUpPage.workEmail.shouldHave(value(workEmail));
		SignUpPage.accountManagerName.shouldHave(value(accountManagerName));
		SignUpPage.password.shouldNot(empty);
		SignUpPage.passwordRepeat.shouldNot(empty);
		SignUpPage.phone.shouldHave(value("+55"+phoneNumber));
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
		SignUpPage.fillEmailLogin(workEmail);
		SignUpPage.fillPasswordLogin(generatedPassword);
		SignUpPage.clickLoginBtn();

		//Testing if the user is logged in
		SignUpPage.userInfo.shouldHave(text(accountManagerName));
		test.log(Status.PASS, "Test of the User Logged In performed successfully.");

		//Testing if the current URL is the same shown on step 2 (but the training one)
		String url = WebDriverRunner.url();
		Assert.assertEquals(url, "https://"+inslyAddress+".int.staging.insly.training/dashboard");
		test.log(Status.PASS, "Test of the URL performed successfully.");
    }
}

