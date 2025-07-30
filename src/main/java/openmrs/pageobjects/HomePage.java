package openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyModuleTile(String moduleName) {
        return driver.findElement(By.partialLinkText(moduleName)).isDisplayed();
    }

    public void clickModule(String moduleName) {
        driver.findElement(By.partialLinkText(moduleName)).click();
    }
}
