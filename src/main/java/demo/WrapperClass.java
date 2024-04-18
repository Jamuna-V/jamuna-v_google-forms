package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class WrapperClass {
    WebDriver driver;

    public WrapperClass(WebDriver driver) {
        this.driver = driver;
    }

    // Method to navigate to a given URL
    public void navigateToURL(String url) {
        driver.get(url);
    }

    // Wrapper method for sending keys to an element
    public static void wrap_sendKeys(WebElement e, String inputText) {
        e.clear();
        e.sendKeys(inputText);
        System.out.println("Text entered successfully: " + inputText);
    }

    // Wrapper method for clicking an element
    public static void wrap_click(WebElement e) {
        System.out.println("Trying to click " + e.getText());
        e.click();
    }

    // Wrapper method for selecting a date in the date field
    public void wrap_selectDate(WebElement e, LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String inputDate = date.format(dateFormat);
        wrap_sendKeys(e, inputDate);
    }

    // Wrapper method for selecting time in the time fields
    public void wrap_selectTime(WebElement hourElement, WebElement minuteElement, WebElement meridiemElement, LocalTime time) throws InterruptedException {
        // Convert the time in String format
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        String inputTime = time.format(timeFormat);

        // Store the hour and min in an array
        String[] timeDetails = inputTime.split(":");
        String[] minAndAmPm = timeDetails[1].split(" ");

        //Enter hour
        wrap_sendKeys(hourElement, timeDetails[0]);

        //Enter minutes
        wrap_sendKeys(minuteElement, minAndAmPm[0]);

        //Click the dropdown
        wrap_click(meridiemElement);

        // Check AM/PM and select the option from dropdown
        if (minAndAmPm[1].equalsIgnoreCase("am")) {
            WebElement anteMeridiem = driver.findElement(By.xpath("//div[@role='option']//span[text() = 'AM']"));
            wrap_click(anteMeridiem);
        } else {
            WebElement postMeridiem = driver.findElement(By.xpath("//div[@role='option']//span[text() = 'PM']"));
            wrap_click(postMeridiem);
        }
        System.out.println("Meridiem selected successfully");

    }
}
