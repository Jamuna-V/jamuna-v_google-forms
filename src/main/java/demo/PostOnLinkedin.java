package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PostOnLinkedin {

        ChromeDriver driver;

        public PostOnLinkedin() {
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

        public void testCase01() {
                System.out.println("Start Test case: testCase01");

                // Navigate to https://www.linkedin.com/ driver.get("https://www.linkedin.com/")
                driver.get("https://in.linkedin.com/ ");

                // Enter the username Using Locator "ID" session_key | sendKeys("<username>")
                driver.findElement(By.id("session_key")).sendKeys("username");
                ;

                // Enter the password Using Locator "ID" session_password |
                // sendKeys("<password>")
                driver.findElement(By.id("session_password")).sendKeys("password");
                ;

                // Click Sign In Using Locator "XPath" //button[contains(text(),'Sign in')]
                driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();

                // Wait for the page to load 10000
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//span[text() = \"Who's viewed your profile\"]/../../following-sibling::div//strong")));

                // Verify if Connections and its count is visible Using Locator "XPath"
                // //span[text() = 'Connections']/../../following-sibling::div//strong
                WebElement connections = driver
                                .findElement(By.xpath(
                                                "//span[text() = 'Connections']/../../following-sibling::div//strong"));

                // Print the count getText()
                System.out.println("Count of connections " + connections.getText());

                // Verify if Who's viewed your profile and its count is visible //span[text() =
                // \"Who's viewed your profile\"]/../../following-sibling::div//strong
                WebElement profileViews = driver.findElement(
                                By.xpath("//span[text() = \"Who's viewed your profile\"]/../../following-sibling::div//strong"));

                // Print the count getText()
                System.out.println("Count of profile views " + profileViews.getText());

                // Click the Start a post button Using Locator "XPath" //span[text() = 'Start a
                // post']/../parent::button
                driver.findElement(By.xpath("//span[text() = 'Start a post']/../parent::button")).click();

                // Wait for the popup to appear 10000
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")));

                // Click on the button at the top left corner Using Locator "XPath" //h2[@id =
                // 'share-to-linkedin-modal__header']/button
                driver.findElement(By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")).click();

                // Click on "Connections only" Using Locator "ID" CONNECTIONS_ONLY
                driver.findElement(By.id("CONNECTIONS_ONLY")).click();

                // Click "Done" button Using Locator "XPath" //span[text()='Done']
                driver.findElement(By.xpath("//span[text()='Done']")).click();

                // Click inside "What do you want to talk about?" Using Locator "XPath"
                // //div[@data-placeholder = 'What do you want to talk about?']
                WebElement enterText = driver
                                .findElement(By.xpath("//div[@data-placeholder = 'What do you want to talk about?']"));

                // Enter the data sendKeys("Good Afternoon")
                enterText.sendKeys("Good Afternoon");

                // Click "Post" button Using Locator "XPath" //span[text() = 'Post']
                driver.findElement(By.xpath("//span[text() = 'Post']")).click();

                // Wait for the post to appear in timeline 10000
                wait.until(
                                ExpectedConditions.visibilityOfElementLocated(
                                                By.xpath("//span[contains(text(),'Good Afternoon')]")));

                // Verify if the post has appeared on the timeline //span[contains(text(),'Good
                // Afternoon')] | isDisplayed()
                Boolean status = driver.findElement(By.xpath("//span[contains(text(),'Good Afternoon')]"))
                                .isDisplayed();

                System.out.println("Post is displayed on the timeline " + status);

                System.out.println("end Test case: testCase01");
        }

}
