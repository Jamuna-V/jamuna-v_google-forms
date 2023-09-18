package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImageUrlsBookMyShow {
    
    ChromeDriver driver;

    public ImageUrlsBookMyShow()
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

    public void testCase01(){
        
        System.out.println("Start Test case: testCase01");

        //Navigate to https://in.bookmyshow.com/explore/home/chennai
        driver.get("https://in.bookmyshow.com/explore/home/chennai");

        //Find the image URLs for all the Recommended Movies
        List<WebElement> hyperLinks = driver
                .findElements(By.xpath("//h2[text() = 'Recommended Movies']/../../../following-sibling::div//img"));

        //Print the URLs
        for (WebElement element : hyperLinks) {
            System.out.println(element.getAttribute("src"));
        }

        try{

            JavascriptExecutor js = (JavascriptExecutor)driver;

            //Scroll through the page for the content to load
            for(int i=0; i<10; i++) {
                js.executeScript("window.scrollBy(0, 300);");
                Thread.sleep(3000);
            }

            //Scroll to the Premiere section
            js.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//img[@alt = 'Tvod Offer']")));
            
            //Wait till the content of the 2nd item under Premiere section is loaded
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2[text()='Premieres']/../../../following-sibling::div//a[2]/div/div[3]/div/div")));
            
            //Find the name and language of the 2nd item in the Premiere section
            List<WebElement> nameLang = driver.findElements(
                    By.xpath("//h2[text()='Premieres']/../../../following-sibling::div//a[2]/div/div[3]/div/div"));

            //Print the name and language of the 2nd item in the Premiere section
            for (WebElement element : nameLang) {
                System.out.println(element.getText());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("end Test case: testCase01");
    }
}
