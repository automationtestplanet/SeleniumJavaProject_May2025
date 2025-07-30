package openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    //    WebDriver driver = new ChromeDriver();
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void launchApplication(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public boolean verifyPageTitle(String pageName) {
        return driver.getTitle().trim().equalsIgnoreCase(pageName);
    }

    public boolean verifyModulePage(String moduleName) {
        return driver.findElement(By.xpath("//h2[contains(text(),'" + moduleName + "')]")).isDisplayed();
    }
}
