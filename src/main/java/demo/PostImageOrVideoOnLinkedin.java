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

public class PostImageOrVideoOnLinkedin {

    ChromeDriver driver;

    public PostImageOrVideoOnLinkedin()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        
        //Navigate to https://www.linkedin.com/  driver.get("https://www.linkedin.com/")
        driver.get("https://in.linkedin.com/ ");

        //Enter the username Using Locator "ID" session_key | sendKeys("<username>")
        driver.findElement(By.id("session_key")).sendKeys("vjamunacse@gmail.com");

        //Enter the password Using Locator "ID" session_password | sendKeys("<password>")
        driver.findElement(By.id("session_password")).sendKeys("Mithra@2016");

        //Click Sign In Using Locator "XPath" //button[contains(text(),'Sign in')]
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();

        //Wait for the page to load  10000
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'feed-identity-widget-item')]")));

        //Click the Start a post button Using Locator "XPath" //span[text() = 'Start a post']/../parent::button
        driver.findElement(By.xpath("//div[@class='share-box-feed-entry__top-bar']/button")).click();

        //Wait for the popup to appear  10000
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")));

        //Click on the button at the top left corner Using Locator "XPath" //h2[@id = 'share-to-linkedin-modal__header']/button
        driver.findElement(By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")).click();

        //Click on "Connections only" Using Locator "ID" CONNECTIONS_ONLY
        driver.findElement(By.id("CONNECTIONS_ONLY")).click();

        //Click "Done" button Using Locator "XPath" //span[text()='Done']
        driver.findElement(By.xpath("//span[text()='Done']")).click();

        //Click the Add Media button Using Locator "XPath" //button[@class = 'share-promoted-detour-button' and @aria-label = 'Add media' ]
        driver.findElement(By.xpath("//button[@class = 'share-promoted-detour-button' and @aria-label = 'Add media' ]"))
                .click();


        //Upload the picture Using Locator "XPath" //input[@type='file'] | sendKeys("<file path>")
        WebElement uploadImage = driver.findElement(By.xpath("//input[@type='file']"));
        uploadImage.sendKeys("C:\\Users\\vjamu\\Downloads\\Bear.jpg");

        //Click Next button Using Locator "XPath" //span[text() = 'Next']
        driver.findElement(By.xpath("//span[text() = 'Next']")).click();

        //Click Post button Using Locator "XPath" //span[text() = 'Post']
        driver.findElement(By.xpath("//span[text() = 'Post']")).click();

        //Verfiy if the image has been posted in timeline successfully Using Locator "XPath" //img[@alt='Image preview'] |isDisplayed()
        boolean status = driver.findElement(By.xpath("//img[@alt='Image preview']")).isDisplayed();

        System.out.println("Image is displayed on the timeline " + status);

        System.out.println("end Test case: testCase01");
    }
}
