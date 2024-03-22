package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ImdbRatings {
    
    ChromeDriver driver;

    public ImdbRatings()
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

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        
        //Navigate to "https://www.imdb.com/chart/top/"  driver.get("https://www.imdb.com/chart/top/")
        driver.get("https://www.imdb.com/chart/top/");

        //Find the highest rated movie Using Locator "XPath" //div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3
        WebElement highestRatedMovie = driver
                .findElement(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3"));
        
        //Get the text of the highest rated movie  getText()
        System.out.println("Highest Rated Movie is " + highestRatedMovie.getText());

       //Find the total movies on the table Using Locator "XPath" //div[@data-testid = 'chart-layout-main-column']/ul/li
        List<WebElement> totalMovies = driver
                .findElements(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li"));
        
        //Get the count of the movies  size()     
        int count = totalMovies.size();
        System.out.println("Total movies " + count);

        //Locate the Sort by dropdown Using Locator "ID" sort-by-selector
        WebElement sortBy = driver.findElement(By.id("sort-by-selector"));

        //Select the "Release date" from dropdown  Select sort = new Select(<Sort by WebElement>) | selectByVisibleText("Release date")
        Select sort = new Select(sortBy);
        sort.selectByVisibleText("Release date");

        //Click on the Swap button next to Sort by dropdown to get the oldest movie on the top of the table Using Locator "ID" swap-sort-order-button
        driver.findElement(By.id("swap-sort-order-button")).click();

        //Wait for the sorting to happen  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3")));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3")));
        
        //Find the oldest movie Using Locator "XPath" //div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3
        WebElement oldestMovie = driver.findElement(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3"));

        //Get the text of the oldest movie  getText()
        System.out.println("Oldest Movie "+oldestMovie.getText());

        //Find the recent movie  Using Locator "XPath" //div[@data-testid = 'chart-layout-main-column']/ul/li["+count+"]//h3
        WebElement recentMovie = driver.findElement(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li["+count+"]//h3"));

        //Get the text of the recent movie  getText()
        System.out.println("Recent Movie "+recentMovie.getText());

        //Select the "Number of ratings" from dropdown  selectByVisibleText("Number of ratings")
        sort.selectByVisibleText("Number of ratings");

        //Wait for the sorting to happen  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3")));

        //Find the movie with the most user ratings which is listed on the top of the table Using Locator "XPath" //div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3
        WebElement mostUserRatings = driver
                .findElement(By.xpath("//div[@data-testid = 'chart-layout-main-column']/ul/li[1]//h3"));
        
        //Get the text of the movie with most user ratings  getText()
        System.out.println("Movie with most user ratings is " + mostUserRatings.getText());

        System.out.println("end Test case: testCase01");
    }
}
