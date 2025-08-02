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
        FindPatientPage findPatientPage = new FindPatientPage(driver);
        AttachmentsPage attachmentsPage = new AttachmentsPage(driver);

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
                            if (patientDetailsPage.verifyPatientDetails("Test, User")) {
                                String patientId = patientDetailsPage.getPatientId();
                                System.out.println(patientId);
                                homePage.clickHomeIcon();
                                if (homePage.verifyModuleTile("Find Patient Record")) {
                                    homePage.clickModule("Find Patient Record");
                                    if (registrationPage.verifyModulePage("Find Patient Record")) {
                                        findPatientPage.searchPatient("Test User");
                                        if (findPatientPage.verifySearchPatientRecord("Name", "Test User")) {
                                            findPatientPage.clickSearchPatientTableFirstRecord();
                                            if (patientDetailsPage.verifyPatientDetails("Test, User")) {
                                                System.out.println("Find Patient details displayed");
                                                patientDetailsPage.startVisits();
                                                if (patientDetailsPage.verifyStartVisit()) {
                                                    patientDetailsPage.clickAttachmentsButton();
                                                    if (attachmentsPage.verifyAttachmentsPage()) {
                                                        String uploadFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\UploadFiles\\UploadImage.png";
                                                        attachmentsPage.addAttachments(uploadFilePath, "TestCaption");
                                                        attachmentsPage.verifyUploadFileCaption("TestCaption");
                                                        System.out.println("Upload file successful");
                                                        homePage.clickHomeIcon();
                                                        homePage.clickModule("Find Patient Record");
                                                        findPatientPage.searchPatient("Test User");
                                                        findPatientPage.clickSearchPatientTableFirstRecord();
                                                        patientDetailsPage.deletePatient("Other");
                                                        findPatientPage.searchPatient("Test User");
                                                        if (findPatientPage.verifyNoRecordsFoundMessage()) {
                                                            System.out.println("Delete Patient successful");
                                                        } else {
                                                            System.out.println("Delete Patient failed");
                                                        }
                                                    } else {
                                                        System.out.println("Attachments Page is not displayed");
                                                    }
                                                } else {
                                                    System.out.println("Start Visits filed");
                                                }
                                            } else {
                                                System.out.println("Patient details not displayed properly");
                                            }
                                        } else {
                                            System.out.println("Patient record is not displayed");
                                        }
                                    } else {
                                        System.out.println("Find Patient Page is not displayed");
                                    }
                                } else {
                                    System.out.println("Find Patient Record module not displayed");
                                }
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
