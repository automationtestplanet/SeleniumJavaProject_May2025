package org.openmrs;

import openmrs.pageobjects.HomePage;
import openmrs.pageobjects.LoginPage;
import openmrs.pageobjects.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Set;

public class WelcomePageTest2 {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//DriverFiles//chromedriver_138v.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(System.getProperty("user.dir") + "//HTML_Pages/Welcome.html");
        Utils utils = new Utils(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.alert('Click Ok to Continue...........')");
        Thread.sleep(2000);
//        Alert alert= driver.switchTo().alert();
//        alert.accept();
//        alert.dismiss();
        driver.switchTo().alert().dismiss();
//        jse.executeScript("window.scroll(0,document.body.scrollHeight)");
        WebElement facebookElement = driver.findElement(By.id("password"));
        jse.executeScript("arguments[0].scrollIntoView(true);", facebookElement);
        String passValue = "Password123";
        jse.executeScript("arguments[0].value=arguments[1]", facebookElement, passValue);

//        WebElement element = driver.findElement(By.cssSelector("input[value=\"Facebook\"]"));
//        jse.executeScript("arguments[0].click()", element);

        WebElement disabledElement = driver.findElement(By.id("email"));
        String email = "test123@gmail.com";
        jse.executeScript("arguments[0].value=arguments[1]", disabledElement, email);
        Utils.captureScreenshot();

        String defaultWindowId = driver.getWindowHandle();
        System.out.println("Default Window Id: " + defaultWindowId);

        System.out.println("-------------Handling Multiple Tabs-------------");
        driver.findElement(By.linkText("OpenMRS in New Tab")).click();
        Set<String> allTabIds = driver.getWindowHandles();
        System.out.println(allTabIds);
        for (String eachTabId : allTabIds) {
            if (!eachTabId.equals(defaultWindowId)) {
                driver.switchTo().window(eachTabId);
                loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
                Thread.sleep(3000);
                homePage.clickLogout();
                Thread.sleep(3000);
                driver.close();
            }
        }

        driver.switchTo().window(defaultWindowId);

        System.out.println("-------------Handling Multiple Windows-------------");
        driver.findElement(By.linkText("OpenMRS in Seperate Window")).click();
        Set<String> allWinIds = driver.getWindowHandles();
        System.out.println(allWinIds);
        for (String eachWindowId : allWinIds) {
            if (!eachWindowId.equals(defaultWindowId)) {
                driver.switchTo().window(eachWindowId);
                driver.manage().window().maximize();
                loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
                Thread.sleep(3000);
                homePage.clickLogout();
                Thread.sleep(3000);
                driver.close();
            }
        }

        driver.switchTo().window(defaultWindowId);

        System.out.println("-------------Handling IFrames-------------");
        WebElement frameEle = driver.findElement(By.name("frame1"));
        jse.executeScript("arguments[0].scrollIntoView(true);", frameEle);
        driver.switchTo().frame("frame1");
        loginPage.setUserName("Admin");
        loginPage.setPassword("Admin123");
        loginPage.selectModule("Registration Desk");
        jse.executeScript("arguments[0].click()", loginPage.getLoginButton());

        Thread.sleep(2000);
        driver.switchTo().defaultContent();  // to come out of frame

        System.out.println("-------------Mouse and Keyboard Actions suing  Actions class -------------");
        WebElement fileUploadEle = driver.findElement(By.cssSelector("input[type=\"file\"]"));
        jse.executeScript("arguments[0].scrollIntoView(true);", fileUploadEle);
//        fileUploadEle.click();
//        jse.executeScript("arguments[0].click()", fileUploadEle);
        Actions actions = new Actions(driver);
        actions.moveToElement(fileUploadEle).click(fileUploadEle).build().perform();

    }

}
