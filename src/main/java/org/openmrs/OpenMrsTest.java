package org.openmrs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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

        WebElement userNameElement = driver.findElement(By.id("username"));
        userNameElement.sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("Admin123");
        driver.findElement(By.id("Registration Desk")).click();
        driver.findElement(By.id("loginButton")).click();
        Thread.sleep(3000);

        if (driver.findElement(By.partialLinkText("Register a patient")).isDisplayed()) {
            driver.findElement(By.partialLinkText("Register a patient")).click();
            if (driver.findElement(By.xpath("//h2[contains(text(),'Register a patient')]")).isDisplayed()) {
                driver.findElement(By.name("givenName")).sendKeys("Test");
                driver.findElement(By.name("familyName")).sendKeys("User");
                driver.findElement(By.id("next-button")).click();

                WebElement genderDropdownEle = driver.findElement(By.id("gender-field"));
                Select genderDropDown = new Select(genderDropdownEle);
                genderDropDown.selectByVisibleText("Male");
                driver.findElement(By.id("next-button")).click();

                driver.findElement(By.id("birthdateDay-field")).sendKeys("01");
                WebElement birthMonthDropdownEle = driver.findElement(By.id("birthdateMonth-field"));
                Select birthMonthDropDown = new Select(birthMonthDropdownEle);
                birthMonthDropDown.selectByVisibleText("January");
                driver.findElement(By.id("birthdateYear-field")).sendKeys("1990");
                driver.findElement(By.id("next-button")).click();

                driver.findElement(By.id("address1")).sendKeys("New Icici Bank");
                driver.findElement(By.id("address2")).sendKeys("S R Nagar");
                driver.findElement(By.id("cityVillage")).sendKeys("Hyderabad");
                driver.findElement(By.id("stateProvince")).sendKeys("Telangana");
                driver.findElement(By.id("country")).sendKeys("India");
                driver.findElement(By.id("postalCode")).sendKeys("500038");
                driver.findElement(By.id("next-button")).click();

                driver.findElement(By.name("phoneNumber")).sendKeys("9876543210");
                driver.findElement(By.id("next-button")).click();

                WebElement relationShipDropdownEle = driver.findElement(By.id("relationship_type"));
                Select relationShipDropDown = new Select(relationShipDropdownEle);
                relationShipDropDown.selectByVisibleText("Parent");
                driver.findElement(By.cssSelector("input[placeholder='Person Name']")).sendKeys("Test User Parent");
                driver.findElement(By.id("next-button")).click();

                Thread.sleep(3000);

                String actualName = driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Name')]//parent::p")).getText();
                String actualGender = driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Gender')]//parent::p")).getText();
                String actualBirthDate = driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Birthdate')]//parent::p")).getText();
                String actualPhoneNumber = driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Phone Number')]//parent::p")).getText();

                if (actualName.contains("Test, User") && actualGender.contains("Male") && actualBirthDate.contains("01, January, 1990") && actualPhoneNumber.contains("9876543210")) {
                    driver.findElement(By.cssSelector("input[value='Confirm']")).click();
                    Thread.sleep(3000);

                    String givenName = driver.findElement(By.xpath("//em[text()='Given']//preceding-sibling::span")).getText();
                    String familyName = driver.findElement(By.xpath("//em[text()='Family Name']//preceding-sibling::span")).getText();
                    if(givenName.contains("Test") && familyName.contains("User")){
                        String patientId = driver.findElement(By.xpath("//em[text()='Patient ID']//following-sibling::span")).getText().trim();
                        System.out.println(patientId);
                    }else{
                        System.out.println("Register patient failed");
                    }
                }else{
                    System.out.println("Registered details showing as wrong");
                    driver.findElement(By.id("cancelSubmission")).click();
                }


            }


        }


//        driver.close();


    }
}