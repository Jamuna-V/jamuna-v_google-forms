# Project Name - _Project Author 
jamuna-v_google-forms_Jamuna Velusamy

## Project Name and Description:
The TestCases class contains methods to automate a test case for filling a Google Form and performing additional actions on a web page.

## Installation Instructions:
- GIT installed - https://github.com/git-guides/install-git
- Java Development Kit (JDK) installed - https://www.java.com/en/download/help/download_options.html
- Gradle installed - https://docs.gradle.org/current/userguide/installation.html
- Selenium WebDriver library added to the project dependencies
- WebDriverManager library added to the project dependencies
- Chrome browser installed

## Usage and Examples:

public class App {

    public void getGreeting() throws InterruptedException, MalformedURLException {
        TestCases tests = new TestCases(); 
        tests.testCase01();
        tests.endTest(); // End your test by clearning connections and closing browser
    }
    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        new App().getGreeting();
    }
}
# to run the project
clone this repo to your machine using git clone

To build the project:
 
MacOS / Linux:

./gradlew build

Windows:

gradlew.bat build

To run the project:

MacOS / Linux:

./gradlew run

Windows:

gradlew.bat run

## Important Links:
Details about useful external links
https://www.selenium.dev/documentation/webdriver/