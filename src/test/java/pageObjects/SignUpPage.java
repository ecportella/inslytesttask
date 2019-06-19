package pageObjects;

import com.codeborne.selenide.SelenideElement;

import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.*;

import functions.BaseTest;

public class SignUpPage extends BaseTest {

    //Company section elements
    public static SelenideElement titleSignUpAndStartUsing = $x("//h1[contains(text(),'Sign up and start using')]");
    public static SelenideElement companyName = $x("//input[@id='broker_name']");
    public static SelenideElement country = $x("//select[@id='broker_address_country']");
    public static SelenideElement yourInslyAddress = $x("//input[@id='broker_tag']");
    public static SelenideElement companyProfile = $x("//select[@id='prop_company_profile']");
    public static SelenideElement numberOfEmployees = $x("//select[@id='prop_company_no_employees']");
    public static SelenideElement howWouldYouDescribeYourself = $x("//select[@id='prop_company_person_description']");
    
    //Administrator account details section elements
    public static SelenideElement workEmail = $x("//input[@id='broker_admin_email']");
    public static SelenideElement accountManagerName = $x("//input[@id='broker_admin_name']");
    public static SelenideElement password = $x("//input[@id='broker_person_password']");
    public static SelenideElement passwordRepeat = $x("//input[@id='broker_person_password_repeat']");
    public static SelenideElement suggestSecurePassword = $x("//a[contains(text(),'suggest a secure password')]");
    public static SelenideElement passwordSuggested = $x("//div[@id='insly_alert']/b");
    public static SelenideElement suggestSecurePasswordBtnOk = $x("//button[contains(text(),'OK')]");
    public static SelenideElement phone = $x("//input[@id='broker_admin_phone']");

    //Terms and conditions section elements
    public static SelenideElement termsAndConditionsCheckbox = $x("//input[@id='agree_termsandconditions']/..");
    public static SelenideElement termsAndConditionsLink = $x("//a[contains(text(),'terms and conditions')]");
    public static SelenideElement termsAndConditionsAgreeBtn = $x("//button[contains(text(),'I agree')]");
    public static SelenideElement privacyPolicyCheckbox = $x("//input[@id='agree_privacypolicy']/..");
    public static SelenideElement privacyPolicyLink = $x("//a[contains(text(),'privacy policy')]");
    public static SelenideElement scrollDownToPrivacyPolicyEnd = $x("//div[contains(text(),'Revision: 1.20180525')]");
    public static SelenideElement closePrivacyPolicyWindow = $x("//span[contains(text(),'Privacy Policy')]/../a");
    public static SelenideElement processingAndStoragePersonalDataCheckbox = $x("//input[@id='agree_data_processing']/..");
    public static SelenideElement signUpBtn = $x("//button[contains(text(),'Sign Up')]");

    //Building System page element
    public static SelenideElement buildingSystemMessage = $x("//p[contains(text(),'Wait a little bit while we are building your syste')]");

    //Login Page section elements
    public static SelenideElement emailLogin = $x("//input[@id='login_username']");
    public static SelenideElement passwordLogin = $x("//input[@id='login_password']");
    public static SelenideElement loginBtn = $x("//button[@id='login_submit']");

    //Dashboard page element
    public static SelenideElement userInfo = $x("//div[@id='user-info']/strong");


    //Company section actions
    public static void waitTitleSignUpAndStartUsing() {
        click("Title: Sign Up and Start Using", titleSignUpAndStartUsing);
    }

    public static void fillCompanyName(String name) {
        type("Company Name field", companyName, name);
    }

    public static void selectCountry(String countryName) {
        select("Country field", country, countryName);
    }

    public static String getContentYourInslyAddress() {
        return yourInslyAddress.getValue();
    }

    public static void selectCompanyProfile(String profile) {
        select("Company Profile field", companyProfile, profile);
    }

    public static void selectNumberOfEmployees(String number) {
        select("Number of Employees field", numberOfEmployees, number);
    }

    public static void selectHowWouldYouDescribeYourself(String how) {
        select("How Would You Describe Yourself field", howWouldYouDescribeYourself, how);
    }


    //Administrator account details section actions
    public static void fillWorkEmail(String email) {
        type("Work Email field", workEmail, email);
    }

    public static void fillAccountManagerName(String name) {
        type("Account Manager field", accountManagerName, name);
    }

    public static void clickSuggestSecurePassword() {
        click("Suggest Secure Password option", suggestSecurePassword);
    }

    public static void clickSuggestSecurePasswordBtnOk() {
        click("Suggest Secure Password toast btn Ok", suggestSecurePasswordBtnOk);
    }

    public static void fillPhoneNumber(String phoneNumber) {
        type("Phone field", phone, phoneNumber);
    }


    //Terms and conditions section actions
    public static void clickTermsAndConditionsCheckbox() {
        termsAndConditionsCheckbox.sendKeys(Keys.SPACE);
    }

    public static void clickTermsAndConditionsLink() {
        click("Terms and Conditions link", termsAndConditionsLink);
    }

    public static void clickTermsAndConditionsAgreeBtn() {
        click("Terms and Conditions Agree btn", termsAndConditionsAgreeBtn);
    }

    public static void clickPrivacyPolicyCheckbox() {
        click("Privacy Policy checkbox", privacyPolicyCheckbox);
    }

    public static void clickPrivacyPolicyLink() {
        click("Privacy Policy link", privacyPolicyLink);
    }

    public static void scrollDownToPrivacyPolicyEnd() {
        scrollDownToPrivacyPolicyEnd.scrollIntoView(true);
    }

    public static void clickClosePrivacyPolicyWindow() {
        click("Close btn from Privacy Policy Window", closePrivacyPolicyWindow);
    }

    public static void clickProcessingAndStoragePersonalDataCheckbox() {
        click("Processing and Storage Personal Data checkbox", processingAndStoragePersonalDataCheckbox);
    }

    public static void clickSignUpBtn() {
        click("Sign Up btn", signUpBtn);
    }


    //Login Page actions
    public static void fillEmailLogin(String email) {
        type("Email Login field", emailLogin, email);
    }

    public static void fillPasswordLogin(String password) {
        type("Password Login field", passwordLogin, password);
    }

    public static void clickLoginBtn() {
        click("Login btn", loginBtn);
    }
}