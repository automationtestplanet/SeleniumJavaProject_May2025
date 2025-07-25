package org.openmrs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenMrsTest {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//DriverFiles//chromedriver_138v.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

//        driver.navigate().to("https://o2.openmrs.org/openmrs/login.htm");
//        Thread.sleep(3000);
//        driver.navigate().back();
//        Thread.sleep(3000);
//        driver.navigate().forward();
//        Thread.sleep(3000);
//        driver.navigate().refresh();
        driver.get("https://o2.openmrs.org/openmrs/login.htm");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
//        System.out.println(driver.getPageSource());
//        driver.findElement(By.name(""));
//        driver.findElement(By.className(""));

        WebElement userNameElement =  driver.findElement(By.id("username"));
        userNameElement.sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("Admin123");
        driver.findElement(By.id("Registration Desk")).click();
        driver.findElement(By.id("loginButton")).click();
        Thread.sleep(3000);
        driver.close();

    }
}