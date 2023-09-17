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

public class PostOnLinkedin {

    ChromeDriver driver;

    public PostOnLinkedin()
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
        
        //Navigate to https://www.linkedin.com/
        driver.get("https://in.linkedin.com/ ");

        //Enter the username and password
        WebElement userName = driver.findElement(By.id("session_key"));

        WebElement password = driver.findElement(By.id("session_password"));


        Actions action = new Actions(driver);
        action.sendKeys(userName, "username").sendKeys(Keys.TAB).sendKeys(password, "password")
                .perform();

        //Click Sign In
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();

        //Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text() = \"Who's viewed your profile\"]/../../following-sibling::div//strong")));

        //Verify if Connections and its count is visible
        WebElement connections = driver
                .findElement(By.xpath("//span[text() = 'Connections']/../../following-sibling::div//strong"));
        
        //Print the count
        System.out.println("Count of connections " + connections.getText());

        //Verify if Who's viewed your profile and its count is visible
        WebElement profileViews = driver.findElement(
                By.xpath("//span[text() = \"Who's viewed your profile\"]/../../following-sibling::div//strong"));
        
        //Print the count
        System.out.println("Count of profile views " + profileViews.getText());

        //Click the Start a post button
        driver.findElement(By.xpath("//span[text() = 'Start a post']/../parent::button")).click();

        //Wait for the popup to appear
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")));

        //Click on the button at the top left corner
        driver.findElement(By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")).click();

        //Click on "Connections only"
        driver.findElement(By.id("CONNECTIONS_ONLY")).click();

        //Click "Done" button
        driver.findElement(By.xpath("//span[text()='Done']")).click();

        //Click inside "What do you want to talk about?"
        WebElement enterText = driver
                .findElement(By.xpath("//div[@data-placeholder = 'What do you want to talk about?']"));

        //Enter the data
        enterText.sendKeys("Good Afternoon");

        //Click "Post" button
        driver.findElement(By.xpath("//span[text() = 'Post']")).click();

        //Wait for the post to appear in timeline
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Good Afternoon')]")));

        //Verify if the post has appeared on the timeline
        Boolean status = driver.findElement(By.xpath("//span[contains(text(),'Good Afternoon')]")).isDisplayed();

        System.out.println("Post is displayed on the timeline " + status);

        System.out.println("end Test case: testCase01");
    }
    
}
