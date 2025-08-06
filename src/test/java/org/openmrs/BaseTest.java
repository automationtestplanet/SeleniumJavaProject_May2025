package org.openmrs;

import openmrs.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class BaseTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    RegistrationPage registrationPage;
    PatientDetailsPage patientDetailsPage;
    FindPatientPage findPatientPage;
    AttachmentsPage attachmentsPage;
    Utils utils = new Utils(driver);

    @BeforeSuite(alwaysRun = true)
    public void beforeSuit() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//DriverFiles//chromedriver_138v.exe");
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        registrationPage = new RegistrationPage(driver);
        patientDetailsPage = new PatientDetailsPage(driver);
        findPatientPage = new FindPatientPage(driver);
        attachmentsPage = new AttachmentsPage(driver);
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        loginPage.launchApplication("https://o2.openmrs.org/openmrs/login.htm");
        Assert.assertTrue(loginPage.verifyPageTitle("Login"), "Login Page is not displayed");
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
        Assert.assertTrue(homePage.verifyPageTitle("Home"), "Hope Page is not displayed");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        homePage.clickLogout();
        Assert.assertTrue(loginPage.verifyPageTitle("Login"), "Login Page is not displayed");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        loginPage.closeTheBrowser();
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        driver = null;
        loginPage = null;
        homePage = null;
        registrationPage = null;
        patientDetailsPage = null;
        findPatientPage = null;
        attachmentsPage = null;
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuit() {
        System.out.println("Test Suit execution completed");
    }
}
