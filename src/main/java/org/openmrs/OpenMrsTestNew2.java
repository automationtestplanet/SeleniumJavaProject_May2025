package org.openmrs;

import openmrs.pageobjects.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenMrsTestNew2 {

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//DriverFiles//chromedriver_138v.exe");
        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        PatientDetailsPage patientDetailsPage = new PatientDetailsPage(driver);
        loginPage.launchApplication("https://o2.openmrs.org/openmrs/login.htm");

        if (loginPage.verifyPageTitle("Login")) {
            loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
            Thread.sleep(2000);
            if (homePage.verifyPageTitle("Home")) {
                if (homePage.verifyModuleTile("Register a patient")) {
                    homePage.clickModule("Register a patient");
                    if (registrationPage.verifyModulePage("Register a patient")) {
                        registrationPage.enterPatientDetails("Test, User", "Male", "01, January, 1990", "New Icici Bank, S R Nagar, Hyderabad, Telangana, India, 500038", "9876543210", "Parent,Test User Parent");
                        if (registrationPage.verifyEnteredDetails("Test, User", "Male", "01, January, 1990", "9876543210")) {
                            registrationPage.clickConfirmButton();
                            Thread.sleep(5000);
                            if (patientDetailsPage.verifyPatientDetails("Test, User")) {
                                String patientId = patientDetailsPage.getPatientId();
                                System.out.println(patientId);
                            } else {
                                System.out.println("Register patient failed");
                            }
                        } else {
                            System.out.println("Registered details showing as wrong");
                            registrationPage.clickCancelButton();
                        }
                    } else {
                        System.out.println("Registration Page not displayed");
                    }
                } else {
                    System.out.println("Registration module not displayed");
                }
            } else {
                System.out.println("Login Page is not displayed");
            }
        } else {
            System.out.println("Login Page is not displayed");
        }

////        driver.close();
    }
}
