package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CountHyperlinks
{
    ChromeDriver driver;

    public CountHyperlinks()
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

        //Navigate to https://in.bookmyshow.com/explore/home/chennai  driver.get("https://in.bookmyshow.com/explore/home/chennai")
        driver.get("https://in.bookmyshow.com/explore/home/chennai");

        //Count all the hyperlinks present in the page Using Locator "Tag Name" a
        List<WebElement> hyperLinks = driver.findElements(By.tagName("a"));
        int count = hyperLinks.size();

        //Print the count of hyperlinks  
        System.out.println("Number of hyperlinks on the home page " + count);

        System.out.println("end Test case: testCase01");
    }
}