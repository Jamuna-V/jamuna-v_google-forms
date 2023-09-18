package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NestedFramesText {

    ChromeDriver driver;

    public NestedFramesText()
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
        
        //Navigate to https://the-internet.herokuapp.com/nested_frames
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        //Switch to top frame
        WebElement topFrame = driver.findElement(By.name("frame-top"));
        driver.switchTo().frame(topFrame);

        //Switch to the left frame
        WebElement leftFrame = driver.findElement(By.name("frame-left"));
        driver.switchTo().frame(leftFrame);

        //Get the text in the Left frame
        WebElement leftFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Left frame " + leftFrameText.getText());

        //Switch to parent frame
        driver.switchTo().parentFrame();

        //Switch to the middle frame
        WebElement middleFrame = driver.findElement(By.name("frame-middle"));
        driver.switchTo().frame(middleFrame);

        //Get the text in the Middle frame
        WebElement middleFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Middle frame " + middleFrameText.getText());

        //Switch to parent frame
        driver.switchTo().parentFrame();

        //Switch to the right frame
        WebElement rightFrame = driver.findElement(By.name("frame-right"));
        driver.switchTo().frame(rightFrame);

        //Get the text in the Right frame
        WebElement rightFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Right frame " + rightFrameText.getText());

        //Switch to default content
        driver.switchTo().defaultContent();

        //Switch to the botton frame
        WebElement bottomFrame = driver.findElement(By.name("frame-bottom"));
        driver.switchTo().frame(bottomFrame);

        //Get the text in the Botton frame
        WebElement bottomFrameText = driver.findElement(By.tagName("body"));
        System.out.println("Text in Botton frame " + bottomFrameText.getText());

        //Switch to default content
        driver.switchTo().defaultContent();

        System.out.println("end Test case: testCase01");
    }
    
}
