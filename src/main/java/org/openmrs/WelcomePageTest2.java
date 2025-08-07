package org.openmrs;

import openmrs.pageobjects.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WelcomePageTest2 {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//DriverFiles//chromedriver_138v.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(System.getProperty("user.dir")+"//HTML_Pages/Welcome.html");
        Utils utils = new Utils(driver);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
//        jse.executeScript("window.scroll(0,document.body.scrollHeight)");
        WebElement facebookElement = driver.findElement(By.id("password"));
        jse.executeScript("arguments[0].scrollIntoView(true);", facebookElement);
        String passValue= "Password123";
        jse.executeScript("arguments[0].value=arguments[1]", facebookElement,passValue);

        WebElement element = driver.findElement(By.cssSelector("input[value=\"Facebook\"]"));
        jse.executeScript("arguments[0].click()", element);

        WebElement disabledElement = driver.findElement(By.id("email"));
        String email= "test123@gmail.com";
        jse.executeScript("arguments[0].value=arguments[1]", disabledElement,email);

        Utils.captureScreenshot();

    }

}
