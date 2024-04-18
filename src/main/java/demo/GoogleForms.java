package demo;

import demo.WrapperClass.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleForms {
    ChromeDriver driver;
    WrapperClass wrapper;

    public GoogleForms() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wrapper = new WrapperClass(driver);
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");

        String baseURL = "https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform";
        // Navigate the URL for the form
        wrapper.navigateToURL(baseURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@aria-labelledby='i1']"))));

        // Locate "Name"
        WebElement name = driver.findElement(By.xpath("//input[@aria-labelledby='i1']"));

        // Enter name details
        wrapper.wrap_sendKeys(name, "Jamuna");

        // Locate "Why are you practicing Automation?"
        WebElement practice = driver.findElement(By.xpath("//textarea[@aria-labelledby='i5']"));

        long epoch = System.currentTimeMillis() / 1000;
        String inputText = "I want to be the best QA Engineer! " + epoch;

        // Enter text
        wrapper.wrap_sendKeys(practice, inputText);

        // Locate an option '0 - 2' under "How much experience do you have in Automation
        // Testing?"
        WebElement radioZeroToTwo = driver.findElement(By.xpath("//span[contains(text(),'0 - 2')]"));

        // Select the option "0 - 2"
        if (!radioZeroToTwo.isSelected()) {
            wrapper.wrap_click(radioZeroToTwo);
        }

        // Locate "Java", "Selenium", and "TestNG" under "Which of the following have
        // you learned in Crio.Do for Automation Testing?"
        WebElement chkBoxJava = driver.findElement(By.xpath("//span[contains(text(),'Java')]"));
        WebElement chkBoxSelenium = driver.findElement(By.xpath("//span[contains(text(),'Selenium')]"));
        WebElement chkBoxTestNG = driver.findElement(By.xpath("//span[contains(text(),'TestNG')]"));

        // Select the options "Java", "Selenium", and "TestNG"
        wrapper.wrap_click(chkBoxJava);
        wrapper.wrap_click(chkBoxSelenium);
        wrapper.wrap_click(chkBoxTestNG);

        // Locate the dropdown "How should you be addressed?"
        WebElement dropdownTitles = driver.findElement(By.xpath("//span[text() = 'Choose']"));

        // Click the Title dropdown
        wrapper.wrap_click(dropdownTitles);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option']//span[text() = 'Mrs']")));

        // Select the option
        WebElement selectTitle = driver.findElement(By.xpath("//div[@role='option']//span[text() = 'Mrs']"));

        // Select the title "Mrs"
        wrapper.wrap_click(selectTitle);

        // Selecting date earlier than 7 days
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Subtract 7 days from the current date
        LocalDate dateSevenDaysAgo = currentDate.minusDays(7);

        // Locate the date field
        WebElement date = driver.findElement(By.xpath("//input[contains(@type, 'date')]"));

        wrapper.wrap_selectDate(date, dateSevenDaysAgo);

        // Entering hour and min in 12 hour format
        // Get the current time in 24-hour format
        LocalTime currentTime = LocalTime.now();

        // Locate hour field
        WebElement hour = driver.findElement(By.xpath("//input[contains(@aria-label, 'Hour')]"));

        // Locate min field
        WebElement minutes = driver.findElement(By.xpath("//input[contains(@aria-label, 'Minute')]"));

        // Locate the dropdown AM/PM
        WebElement meridiem =
                driver.findElement(By.xpath("//div[contains(@data-value, 'AM')]"));

        wrapper.wrap_selectTime(hour, minutes, meridiem, currentTime);

        // Change the URL of the tab to https://www.amazon.in/
        wrapper.navigateToURL("https://www.amazon.in/");

        wait.until(ExpectedConditions.alertIsPresent());

        // Select Cancel in the Alert
        driver.switchTo().alert().dismiss();
        System.out.println("Alert dismissed successfully.");

        // Locate the Submit button
        WebElement submit = driver.findElement(By.xpath("//span[text() = 'Submit']"));

        // Click Submit
        wrapper.wrap_click(submit);

        // Locate the success message
        WebElement successMessage = driver.findElement(By.className("vHW8K"));

        // Get the text of the success message
        String successText = successMessage.getText();

        // Print it to console
        System.out.println(successText);

        System.out.println("end Test case: testCase01");
    }
}