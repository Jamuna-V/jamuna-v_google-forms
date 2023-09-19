package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice.Enter;

public class AmazonSearch
{
    ChromeDriver driver;

    public AmazonSearch()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30));
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");

        //Enter the url https://www.google.com/  driver.get("https://www.google.com/")
        driver.get("https://www.google.com");

        //Locate the search bar Using Locator "ID"  APjFqb
        WebElement searchBar = driver.findElement(By.id("APjFqb"));

        //Enter the search keyword "Amazon" and hit enter  sendKeys("Amazon") | sendKeys(Keys.ENTER)
        Actions action = new Actions(driver);
        action.sendKeys(searchBar, "Amazon").sendKeys(Keys.ENTER).perform();

        //Wait for the search results to be displayed  5000ms
        WebDriverWait waitSearchResult = new WebDriverWait(driver, Duration.ofMillis(5000));
        waitSearchResult.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'amazon.in') or contains(@href,'amazon.com')]")));
        
        //Verify if Amazon.in or Amazon.com is returned in search results Using Locator "XPath" //a[contains(@href,"amazon.in) or contains(@href,"amazon.com) ] | isDisplayed()
        boolean status = driver
                .findElement(By.xpath("//a[contains(@href,'amazon.in') or contains(@href,'amazon.com')]"))
                .isDisplayed();
                
        System.out.println("Amazon returned in search results " + status);

        System.out.println("end Test case: testCase01");
    }
}