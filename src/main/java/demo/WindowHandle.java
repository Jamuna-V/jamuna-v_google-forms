package demo;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class WindowHandle {

    ChromeDriver driver;

    public WindowHandle()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30));
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        
        //Navigate to "https://www.w3schools.com/jsref/tryit.aspfilename=tryjsref_win_open"  driver.get("https://www.w3schools.com/jsref/tryit.aspfilename=tryjsref_win_open"
        driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");

        //Get the parent window handle  driver.getWindowHandle()
        String parentWindow = driver.getWindowHandle();

        //Identify the iframe Using Locator "ID" iframeResult
        WebElement frame = driver.findElement(By.id("iframeResult"));

        //Switch to the iframe  driver.switchTo().frame(<WebElement>);
        driver.switchTo().frame(frame);

        //Click the try it button Using Locator "XPath" //button[text() = 'Try it']
        driver.findElement(By.xpath("//button[text() = 'Try it']")).click();

        //Get all the window handles  driver.getWindowHandles()
        Set<String> windowHandles = driver.getWindowHandles();


        for (String window : windowHandles) {
            if (!window.equalsIgnoreCase(parentWindow)) {
                //Switch to the newly opened tab  driver.switchTo().window(window);
                driver.switchTo().window(window);
                
                //Get the current url  getCurrentUrl()
                System.out.println("New tab URL: " + driver.getCurrentUrl());

                //Get the current title  getTitle()
                System.out.println("New tab Page Title: " + driver.getTitle());
                
                //Take the full page screenshot  new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                //.takeScreenshot(driver); |scrShot.getImage()
                String fileName = "Screenshot.png";
                Screenshot scrShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                        .takeScreenshot(driver);

                try {
                    File theDir = new File("./screenshots");
                    if (!theDir.exists()) {
                        theDir.mkdirs();
                        System.out.println("Screenshot folder is createed");
                    }
                    
                    //Store the screenshot in a destination file  ImageIO.write(scrShot.getImage(), "PNG", new File("screenshots/" + <fileName>));
                    ImageIO.write(scrShot.getImage(), "PNG", new File("screenshots/" + fileName));
                    System.out.println("Full Page Screenshot captured and saved as : " + fileName);
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
                finally
                {
                    //Close the new window  driver.close();
                    driver.close();
                }
            
            }

        }
        //Switch back to the original window   driver.switchTo().window(<parentWindow>);
        driver.switchTo().window(parentWindow);

        System.out.println("end Test case: testCase01");
        
    }
    
}
