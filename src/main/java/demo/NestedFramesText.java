package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NestedFramesText {

    ChromeDriver driver;

    public NestedFramesText() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().clearDriverCache().setup();
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

        // Navigate to https://the-internet.herokuapp.com/nested_frames
        // driver.get("https://the-internet.herokuapp.com/nested_frames")
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // Switch to top frame driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-top");

        // Switch to the left frame driver.switchTo().frame("frame-left");
        driver.switchTo().frame("frame-left");

        // Get the text in the Left frame Using Locator "Tag Name" body | getText()
        WebElement leftFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Left frame " + leftFrameText.getText());

        // Switch to parent frame driver.switchTo().parentFrame();
        driver.switchTo().parentFrame();

        // Switch to the middle frame driver.switchTo().frame("frame-middle");
        driver.switchTo().frame("frame-middle");

        // Get the text in the Middle frame Using Locator "Tag Name" body | getText()
        WebElement middleFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Middle frame " + middleFrameText.getText());

        // Switch to parent frame driver.switchTo().parentFrame();
        // It navigates up one level in the frame hierarchy. If you need to navigate up
        // the frame hierarchy, use parentFrame().
        driver.switchTo().parentFrame();
        // It resets the focus to the top-level frame or window.If you need to return to
        // the main content of the page, use defaultContent().
        // driver.switchTo().defaultContent();

        // Switch to the right frame driver.switchTo().frame("frame-right");
        driver.switchTo().frame("frame-right");

        // Get the text in the Right frame Using Locator "Tag Name" body | getText()
        WebElement rightFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Right frame " + rightFrameText.getText());

        // Switch to default content driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();

        // Switch to the botton frame driver.switchTo().frame("frame-bottom");
        driver.switchTo().frame("frame-bottom");

        // Get the text in the Botton frame Using Locator "Tag Name" body | getText()
        WebElement bottomFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Botton frame " + bottomFrameText.getText());

        // Switch to default content driver.switchTo().defaultContent();
        driver.switchTo().defaultContent();

        System.out.println("end Test case: testCase01");
    }
}
