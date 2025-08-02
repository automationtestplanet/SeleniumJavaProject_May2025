package openmrs.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//i[@class='icon-home small']//parent::a")
    WebElement homeIconEle;

    public WebElement getHomeIconEle() {
        return homeIconEle;
    }

    public boolean verifyModuleTile(String moduleName) {
        return driver.findElement(By.partialLinkText(moduleName)).isDisplayed();
    }

    public void clickModule(String moduleName) {
        driver.findElement(By.partialLinkText(moduleName)).click();
    }

    public void clickHomeIcon(){
        getHomeIconEle().click();
    }
}
