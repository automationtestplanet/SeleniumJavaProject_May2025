package org.openmrs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OpenMrsTestNew {
    public static WebDriver driver;

    public static void launchApplication(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void loginToOpenMrs(String userName, String password, String moduleName) {
        try {
            WebElement userNameElement = driver.findElement(By.id("username"));
            userNameElement.sendKeys(userName);
            driver.findElement(By.name("password")).sendKeys(password);
            driver.findElement(By.id(moduleName)).click();
            driver.findElement(By.id("loginButton")).click();
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Exception occurred while login to OpenMrs Application: " + e.getMessage());
        }
    }

    public static boolean verifyModuleTile(String moduleName) {
        return driver.findElement(By.partialLinkText(moduleName)).isDisplayed();
    }

    public static void clickModule(String moduleName) {
        driver.findElement(By.partialLinkText(moduleName)).click();
    }

    public static boolean verifyModulePage(String moduleName) {
        return driver.findElement(By.xpath("//h2[contains(text(),'" + moduleName + "')]")).isDisplayed();
    }

    public static void setName(String name) {
        String[] nameArr = name.split(",");
        driver.findElement(By.name("givenName")).sendKeys(nameArr[0].trim());
        driver.findElement(By.name("familyName")).sendKeys(nameArr[1].trim());
    }

    public static void clickNextButton() {
        driver.findElement(By.id("next-button")).click();
    }

    public static void selectGender(String gender) {
        WebElement genderDropdownEle = driver.findElement(By.id("gender-field"));
        Select genderDropDown = new Select(genderDropdownEle);
        genderDropDown.selectByVisibleText(gender);
    }

    public static void setDateOfBirth(String dateOfBirth) {
        String[] dateOfBirthArr = dateOfBirth.split(",");
        driver.findElement(By.id("birthdateDay-field")).sendKeys(dateOfBirthArr[0].trim());
        WebElement birthMonthDropdownEle = driver.findElement(By.id("birthdateMonth-field"));
        Select birthMonthDropDown = new Select(birthMonthDropdownEle);
        birthMonthDropDown.selectByVisibleText(dateOfBirthArr[1].trim());
        driver.findElement(By.id("birthdateYear-field")).sendKeys(dateOfBirthArr[2].trim());
    }

    public static void setAddress(String address) {
        String[] addressArr = address.split(",");
        driver.findElement(By.id("address1")).sendKeys(addressArr[0].trim());
        driver.findElement(By.id("address2")).sendKeys(addressArr[1].trim());
        driver.findElement(By.id("cityVillage")).sendKeys(addressArr[2].trim());
        driver.findElement(By.id("stateProvince")).sendKeys(addressArr[3].trim());
        driver.findElement(By.id("country")).sendKeys(addressArr[4].trim());
        driver.findElement(By.id("postalCode")).sendKeys(addressArr[5].trim());
    }

    public static void setPhoneNumber(String phoneNumber) {
        driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
    }

    public static void setRelatives(String relationType, String relativeName) {
        WebElement relationShipDropdownEle = driver.findElement(By.id("relationship_type"));
        Select relationShipDropDown = new Select(relationShipDropdownEle);
        relationShipDropDown.selectByVisibleText(relationType);
        driver.findElement(By.cssSelector("input[placeholder='Person Name']")).sendKeys(relativeName);
    }

    public static boolean verifyEnteredDetails(String expectedName, String expectedGender, String expectedDateOfBirth, String expectedPhoneNumber) {
        String actualName = driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Name')]//parent::p")).getText();
        String actualGender = driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Gender')]//parent::p")).getText();
        String actualBirthDate = driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Birthdate')]//parent::p")).getText();
        String actualPhoneNumber = driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Phone Number')]//parent::p")).getText();
        return actualName.contains(expectedName) && actualGender.contains(expectedGender) && actualBirthDate.contains(expectedDateOfBirth) && actualPhoneNumber.contains(expectedPhoneNumber);
    }

    public static void clickConfirmButton() {
        driver.findElement(By.cssSelector("input[value='Confirm']")).click();
    }

    public static void clickCancelButton() {
        driver.findElement(By.id("cancelSubmission")).click();
    }

    public static boolean verifyRegisteredPatientDetails(String name) {
        String[] nameArr = name.split(",");
        String givenName = driver.findElement(By.xpath("//em[text()='Given']//preceding-sibling::span")).getText();
        String familyName = driver.findElement(By.xpath("//em[text()='Family Name']//preceding-sibling::span")).getText();
        return givenName.contains(nameArr[0].trim()) && familyName.contains(nameArr[1].trim());
    }

    public static String getPatientId() {
        return driver.findElement(By.xpath("//em[text()='Patient ID']//following-sibling::span")).getText().trim();
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//DriverFiles//chromedriver_138v.exe");
        driver = new ChromeDriver();
        launchApplication("https://o2.openmrs.org/openmrs/login.htm");
        loginToOpenMrs("Admin", "Admin123", "Registration Desk");
        if (verifyModuleTile("Register a patient")) {
            clickModule("Register a patient");
            if (verifyModulePage("Register a patient")) {
                setName("Test, User");
                clickNextButton();
                selectGender("Male");
                clickNextButton();
                setDateOfBirth("01, January, 1990");
                clickNextButton();
                setAddress("New Icici Bank, S R Nagar, Hyderabad, Telangana, India, 500038");
                clickNextButton();
                setPhoneNumber("9876543210");
                clickNextButton();
                setRelatives("Parent", "Test User Parent");
                clickNextButton();
                Thread.sleep(3000);
                if (verifyEnteredDetails("Test, User", "Male", "01, January, 1990", "9876543210")) {
                    clickConfirmButton();
                    Thread.sleep(3000);
                    if (verifyRegisteredPatientDetails("Test, User")) {
                        String patientId = getPatientId();
                        System.out.println(patientId);
                    } else {
                        System.out.println("Register patient failed");
                    }
                } else {
                    System.out.println("Registered details showing as wrong");
                    clickCancelButton();
                }
            }
        }
//        driver.close();
    }
}
