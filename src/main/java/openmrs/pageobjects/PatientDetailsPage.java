package openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PatientDetailsPage extends BasePage {

    public PatientDetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyPatientDetails(String name) {
        String[] nameArr = name.split(",");
        String givenName = driver.findElement(By.xpath("//em[text()='Given']//preceding-sibling::span")).getText();
        String familyName = driver.findElement(By.xpath("//em[text()='Family Name']//preceding-sibling::span")).getText();
        return givenName.contains(nameArr[0].trim()) && familyName.contains(nameArr[1].trim());
    }

    public String getPatientId() {
        return driver.findElement(By.xpath("//em[text()='Patient ID']//following-sibling::span")).getText().trim();
    }
}
