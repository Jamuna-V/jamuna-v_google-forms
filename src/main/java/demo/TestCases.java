package demo;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.Action;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
//Selenium Imports
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    //Activity 1 : Search for Amazon in Google
    public void testCase01() {
        System.out.println("Start Test case: testCase01");

        driver.get("https://www.google.com");
        WebElement searchBar = driver.findElement(By.id("APjFqb"));

        Actions action = new Actions(driver);
        action.sendKeys(searchBar, "Amazon").sendKeys(Keys.ENTER).perform();

        WebDriverWait waitSearchResult = new WebDriverWait(driver, Duration.ofMillis(5000));
        waitSearchResult.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'amazon.in') or contains(@href,'amazon.com')]")));
        boolean status = driver
                .findElement(By.xpath("//a[contains(@href,'amazon.in') or contains(@href,'amazon.com')]"))
                .isDisplayed();
        System.out.println("Amazon returned in search results " + status);

        System.out.println("end Test case: testCase01");
    }

    //Activity 2 : Count hyperlinks in Bookmyshow
    public void testCase02() {
        System.out.println("Start Test case: testCase02");
        
        driver.get("https://in.bookmyshow.com/explore/home/chennai");

        List<WebElement> hyperLinks = driver.findElements(By.tagName("a"));
        int count = hyperLinks.size();

        System.out.println("Number of hyperlinks on the home page " + count);

        System.out.println("end Test case: testCase02");
    }

    //Activity 3 : Post on linkedin
    public void testCase03() {
        System.out.println("Start Test case: testCase03");
        
        driver.get("https://in.linkedin.com/ ");

        // Enter username and password
        WebElement userName = driver.findElement(By.id("session_key"));
        WebElement password = driver.findElement(By.id("session_password"));

        Actions action = new Actions(driver);
        action.sendKeys(userName, "vjamunacse@gmail.com").sendKeys(Keys.TAB).sendKeys(password, "Mithra@2016")
                .perform();

        // Click Sign In
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text() = \"Who's viewed your profile\"]/../../following-sibling::div//strong")));

        // Check the count for COnnections and Profile Viewers
        WebElement connections = driver
                .findElement(By.xpath("//span[text() = 'Connections']/../../following-sibling::div//strong"));
        System.out.println("Count of connections " + connections.getText());

        WebElement profileViews = driver.findElement(
                By.xpath("//span[text() = \"Who's viewed your profile\"]/../../following-sibling::div//strong"));
        System.out.println("Count of profile views " + profileViews.getText());

        // Start a post
        driver.findElement(By.xpath("//span[text() = 'Start a post']/../parent::button")).click();

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")));

        driver.findElement(By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")).click();

        driver.findElement(By.id("CONNECTIONS_ONLY")).click();
        driver.findElement(By.xpath("//span[text()='Done']")).click();

        // Enter text and post it
        WebElement enterText = driver
                .findElement(By.xpath("//div[@data-placeholder = 'What do you want to talk about?']"));

        enterText.sendKeys("Good Afternoon");

        driver.findElement(By.xpath("//span[text() = 'Post']")).click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Good Afternoon')]")));

        Boolean status = driver.findElement(By.xpath("//span[contains(text(),'Good Afternoon')]")).isDisplayed();

        System.out.println("Post is displayed on the timeline " + status);

        System.out.println("end Test case: testCase03");
    }

    
    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        
        int count = 0;
        driver.get("https://www.google.com");

        WebElement searchBar = driver.findElement(By.id("APjFqb"));

        Actions action = new Actions(driver);
        action.sendKeys(searchBar, "Jamuna").sendKeys(Keys.ENTER).perform();

        Thread.sleep(5000);

        List<WebElement> hyperLinks = driver.findElements(By.tagName("a"));

        for (WebElement element : hyperLinks) {
            if (element.getText().toLowerCase().contains("jamuna"))
                count++;
        }

        System.out.println("Number of hyperlinks with your name " + count);

        System.out.println("end Test case: testCase04");
    }

    public void testCase05() throws InterruptedException {
        
        System.out.println("Start Test case: testCase05");

        driver.get("https://in.bookmyshow.com/explore/home/chennai");

        List<WebElement> hyperLinks = driver
                .findElements(By.xpath("//h2[text() = 'Recommended Movies']/../../../following-sibling::div//img"));

        for (WebElement element : hyperLinks) {
            System.out.println(element.getAttribute("src"));
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(120));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//h2[text()='Premieres']/../../../following-sibling::div//a[2]/div/div[3]/div/div")));
        List<WebElement> nameLang = driver.findElements(
                By.xpath("//h2[text()='Premieres']/../../../following-sibling::div//a[2]/div/div[3]/div/div"));

        for (WebElement element : nameLang) {
            System.out.println(element.getText());
        }

        System.out.println("end Test case: testCase05");
    }

    public void testCase06() {
        System.out.println("Start Test case: testCase06");
        
        driver.get("https://in.linkedin.com/ ");

        // Enter username and password
        WebElement userName = driver.findElement(By.id("session_key"));
        WebElement password = driver.findElement(By.id("session_password"));

        Actions action = new Actions(driver);
        action.sendKeys(userName, "vjamunacse@gmail.com").sendKeys(Keys.TAB).sendKeys(password, "Mithra@2016")
                .perform();

        // Click Sign In
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text() = \"Who's viewed your profile\"]/../../following-sibling::div//strong")));

        // Start a post
        driver.findElement(By.xpath("//span[text() = 'Start a post']/../parent::button")).click();

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")));

        driver.findElement(By.xpath("//h2[@id = 'share-to-linkedin-modal__header']/button")).click();

        driver.findElement(By.id("CONNECTIONS_ONLY")).click();
        driver.findElement(By.xpath("//span[text()='Done']")).click();

        // Selecting media through sendKeys
        driver.findElement(By.xpath("//button[@class = 'share-promoted-detour-button' and @aria-label = 'Add media' ]"))
                .click();

        WebElement uploadImage = driver.findElement(By.xpath("//input[@type='file']"));
        uploadImage.sendKeys("C:\\Users\\vjamu\\Downloads\\Bear.jpg");

        driver.findElement(By.xpath("//span[text() = 'Next']")).click();

        driver.findElement(By.xpath("//span[text() = 'Post']")).click();

        boolean status = driver.findElement(By.xpath("//img[@alt='Image preview']")).isDisplayed();

        System.out.println("Image is displayed on the timeline " + status);

        System.out.println("end Test case: testCase06");
    }

    public void testCase07() throws InterruptedException {
        System.out.println("Start Test case: testCase07");
        
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        WebElement topFrame = driver.findElement(By.name("frame-top"));
        driver.switchTo().frame(topFrame);

        // Left frame
        WebElement leftFrame = driver.findElement(By.name("frame-left"));
        driver.switchTo().frame(leftFrame);
        WebElement leftFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Left frame " + leftFrameText.getText());

        driver.switchTo().parentFrame();

        // Middle frame
        WebElement middleFrame = driver.findElement(By.name("frame-middle"));
        driver.switchTo().frame(middleFrame);
        WebElement middleFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Middle frame " + middleFrameText.getText());

        driver.switchTo().parentFrame();

        // Right frame
        WebElement rightFrame = driver.findElement(By.name("frame-right"));
        driver.switchTo().frame(rightFrame);
        WebElement rightFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Right frame " + rightFrameText.getText());

        driver.switchTo().defaultContent();

        // Bottom frame
        WebElement bottomFrame = driver.findElement(By.name("frame-bottom"));
        driver.switchTo().frame(bottomFrame);
        WebElement bottomFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Botton frame " + bottomFrameText.getText());

        driver.switchTo().defaultContent();

        System.out.println("end Test case: testCase07");
    }

    //Imdb_ratings
    public void testCase08() {
        System.out.println("Start Test case: testCase08");
        
        driver.get("https://web-locators-static-site-qa.vercel.app/Alerts");

        driver.findElement(By.xpath("//p[text()='Add Remarks']")).click();

        driver.switchTo().alert().sendKeys("Adding Remarks");
        driver.switchTo().alert().accept();

        WebElement remarks = driver.findElement(By.className("Alert_remarkContent"));

        boolean status = remarks.getText().equals("Adding Remarks");

        System.out.println("Remarks is printed on the screen " + status);

        System.out.println("end Test case: testCase08");
    }

    
    public void testCase09() {
        System.out.println("Start Test case: testCase09");
        
        driver.get("https://www.imdb.com/chart/top/");

        WebElement highestRatedMovie = driver
                .findElement(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3"));
        System.out.println("Highest Rated Movie is " + highestRatedMovie.getText());

        List<WebElement> totalMovies = driver
                .findElements(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li"));
        int count = totalMovies.size();
        System.out.println("Total movies " + count);

        WebElement sortBy = driver.findElement(By.id("sort-by-selector"));

        Select sort = new Select(sortBy);
        sort.selectByVisibleText("Release date");

        driver.findElement(By.id("swap-sort-order-button")).click();

        WebElement oldestMovie = driver.findElement(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3"));
        System.out.println("Oldest Movie "+oldestMovie.getText());

        WebElement recentMovie = driver.findElement(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li["+count+"]//h3"));
        System.out.println("Recent Movie "+recentMovie.getText());

        sort.selectByVisibleText("Number of ratings");

        WebElement mostUserRatings = driver
                .findElement(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3"));
        System.out.println("Movie with most user ratings is " + mostUserRatings.getText());

        System.out.println("end Test case: testCase09");
    }

    // Window Handles
    public void testCase10() {
        System.out.println("Start Test case: testCase10");
        
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");

        String parentWindow = driver.getWindowHandle();

        WebElement frame = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(frame);

        driver.findElement(By.xpath("//button[text() = 'Try it']")).click();

        Set<String> windowHandles = driver.getWindowHandles();

        for (String window : windowHandles) {
            if (!window.equalsIgnoreCase(parentWindow)) {
                driver.switchTo().window(window);
                System.out.println("New tab URL " + driver.getCurrentUrl());
                System.out.println("New tab Page Title " + driver.getTitle());

                String fileName = "Screenshot.png";
                Screenshot scrShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                        .takeScreenshot(driver);

                try {
                    File theDir = new File("./screenshots");
                    if (!theDir.exists()) {
                        theDir.mkdirs();
                        System.out.println("Screenshot folder is createed");
                    }
                    ImageIO.write(scrShot.getImage(), "PNG", new File("screenshots/" + fileName));
                    System.out.println("Full Page Screenshot captured and saved as : " + fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                driver.close();

            }

        }
        driver.switchTo().window(parentWindow);

        System.out.println("end Test case: testCase10");
    }

}
