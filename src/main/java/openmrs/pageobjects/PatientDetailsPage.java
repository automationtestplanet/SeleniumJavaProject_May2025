package openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class PatientDetailsPage extends BasePage {

    public PatientDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//em[text()='Given']//preceding-sibling::span")
    WebElement givenNameEle;

    @FindBy(xpath = "//em[text()='Family Name']//preceding-sibling::span")
    WebElement familyNameEle;

    @FindBy(xpath = "//em[text()='Patient ID']//following-sibling::span")
    WebElement patientIdEle;

    public WebElement getGivenNameEle() {
        return givenNameEle;
    }

    public WebElement getFamilyNameEle() {
        return familyNameEle;
    }

    public WebElement getPatientIdEle() {
        return patientIdEle;
    }

    public String getGivenName() {
        return waitForElementToBeVisible(getGivenNameEle()).getText().trim();
    }

    public String getFamilyName() {
        return waitForElementToBeVisible(getFamilyNameEle()).getText().trim();
    }

    public String getPatientId() {
        return waitForElementToBeVisible(getPatientIdEle()).getText().trim();
    }

    public boolean verifyPatientDetails(String name) {
        String[] nameArr = name.split(",");
        return getGivenName().contains(nameArr[0].trim()) && getFamilyName().contains(nameArr[1].trim());
    }
}
