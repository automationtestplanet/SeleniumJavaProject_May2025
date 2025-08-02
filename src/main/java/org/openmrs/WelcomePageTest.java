package org.openmrs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class WelcomePageTest {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//DriverFiles//chromedriver_138v.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(System.getProperty("user.dir")+"//HTML_Pages/Welcome.html");

        WebElement userNameFiled = driver.findElement(By.id("userName"));
        userNameFiled.sendKeys("Abc123");
        System.out.println(userNameFiled.getAttribute("id"));
        System.out.println(userNameFiled.getAttribute("minlength"));
        System.out.println(userNameFiled.getAttribute("maxlength"));
        System.out.println(userNameFiled.getAttribute("placeholder"));
        System.out.println(userNameFiled.getAttribute("name"));
        System.out.println(userNameFiled.getAttribute("value"));
        System.out.println(userNameFiled.isDisplayed());
        System.out.println(userNameFiled.isEnabled());


        driver.findElement(By.name("Password")).sendKeys("Password123");
        Thread.sleep(3000);
        driver.findElement(By.name("Password")).clear();

        driver.findElement(By.className("passwordclass")).sendKeys("123Pass");

        driver.findElement(By.tagName("textarea")).sendKeys("This is selenium Test");

        System.out.println(driver.findElement(By.tagName("p")).getText());


//        driver.findElement(By.linkText("OpenMRS in Same Window")).click();

//        driver.findElement(By.linkText("Raju Chelle hkahfkhskfhkahfkflahfhakfhkalsfh")).click();
//        driver.findElement(By.partialLinkText("Raju Chelle")).click();

        WebElement countriesDropdownElement = driver.findElement(By.id("countries"));

        Select countriesDropdown = new Select(countriesDropdownElement);
        countriesDropdown.selectByVisibleText("China");
        Thread.sleep(3000);
        countriesDropdown.selectByIndex(1);
        Thread.sleep(3000);
        countriesDropdown.selectByValue("UK");

        driver.findElement(By.cssSelector("[value='Register']")).click();
        driver.findElement(By.cssSelector("input[value='Register']")).click();

    }

}
