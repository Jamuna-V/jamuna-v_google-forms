package demo;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PlayStore {
    WebDriver driver;

    public PlayStore() throws InterruptedException {
        System.out.println("Constructor: TestCases");
        // WebDriverManager.chromedriver().clearDriverCache().setup();
        System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Java Projects_VSCode\\browserDrivers\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();
    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");

        driver.get("https://play.google.com/store/games");

        driver.findElement(By.xpath("//button[@aria-label = 'Search']")).click();

        WebElement search = driver.findElement(By.xpath("//input[@aria-label = 'Search Google Play']"));

        Actions action = new Actions(driver);
        action.sendKeys(search, "facebook").sendKeys(Keys.ENTER).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='XUIuZ']//a")));

        boolean status = driver.findElement(By.xpath("//div[@class='XUIuZ']//a")).isDisplayed();

        System.out.println("Facebook returned in search results " + status);

        System.out.println("end Test case: testCase01");
    }

}
