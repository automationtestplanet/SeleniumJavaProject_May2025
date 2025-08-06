package org.openmrs;

import openmrs.pageobjects.Utils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class OpenMrsTestCases extends BaseTest {

    @Test
    public void registerPatientTest() {
        Assert.assertTrue(homePage.verifyModuleTile("Register a patient"), "Register a Patient tile is not displayed");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
        homePage.clickModule("Register a patient");
        Assert.assertTrue(registrationPage.verifyModulePage("Register a patient"), "Register patient page is not displayed");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
        registrationPage.enterPatientDetails("Test, User", "Male", "01, January, 1990", "New Icici Bank, S R Nagar, Hyderabad, Telangana, India, 500038", "9876543210", "Parent,Test User Parent");
        Assert.assertTrue(registrationPage.verifyEnteredDetails("Test, User", "Male", "01, January, 1990", "9876543210"), "Registered Details are showing incorrect");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
        registrationPage.clickConfirmButton();
        Assert.assertTrue(patientDetailsPage.verifyPatientDetails("Test, User"), "Patient Name is not matching");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
        System.out.println(patientDetailsPage.getPatientId());
    }

    @Test
    public void findPatientTest() {
        Assert.assertTrue(homePage.verifyModuleTile("Find Patient Record"), "Find Patient Record tile ins not displayed");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
        homePage.clickModule("Find Patient Record");
        Assert.assertTrue(registrationPage.verifyModulePage("Find Patient Record"), "Find Patient Record page is not displayed");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
        findPatientPage.searchPatient("Test User");
        Assert.assertTrue(findPatientPage.verifySearchPatientRecord("Name", "Test User"), "Filtered record not matching");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
        findPatientPage.clickSearchPatientTableFirstRecord();
        Assert.assertTrue(patientDetailsPage.verifyPatientDetails("Test, User"), "Patient Name is not matching");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
    }

    @Test
    public void activateVisitsAndAddAttachmentsTest() {
        homePage.clickModule("Find Patient Record");
        findPatientPage.searchPatient("Test User");
        findPatientPage.clickSearchPatientTableFirstRecord();
        patientDetailsPage.startVisits();
        Assert.assertTrue(patientDetailsPage.verifyStartVisit(), "Start Visits failed");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
        patientDetailsPage.clickAttachmentsButton();
        Assert.assertTrue(attachmentsPage.verifyAttachmentsPage(), "Attachments Page is not displayed");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
        String uploadFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\UploadFiles\\UploadImage.png";
        attachmentsPage.addAttachments(uploadFilePath, "TestCaption");
        Assert.assertTrue(attachmentsPage.verifyUploadFileCaption("TestCaption"), "File Upload is not successful");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
    }

    @Test
    public void deletePatientTest() {
        homePage.clickModule("Find Patient Record");
        findPatientPage.searchPatient("Test User");
        findPatientPage.clickSearchPatientTableFirstRecord();
        patientDetailsPage.deletePatient("Other");
        findPatientPage.searchPatient("Test User");
        Assert.assertTrue(findPatientPage.verifyNoRecordsFoundMessage(), "Patient record is not deleted");
        Reporter.log("<img src=\""+Utils.captureScreenshot()+"\" />");
    }

}
