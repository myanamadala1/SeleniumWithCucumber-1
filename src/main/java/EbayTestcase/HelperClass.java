package EbayTestcase;

import com.sun.javafx.geom.IllegalPathStateException;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.io.FileUtils.copyFile;

/**
 * Created by edibi on 8.2.2017.
 * Helper class
 */

public class HelperClass {

    protected WebDriver driver = null;
    protected String chrome = "CHROME";
    protected String internetExplorer = "IE";
    protected String firefox = "FIREFOX";

    String cucumberExercise1png = "C:\\Users\\edibi\\Desktop\\selenium tests\\CucumberExercise1\\resources\\screenshotforcucumber1.png";
    protected String CucumberExerciseWithDataTablespng = "C:\\Users\\edibi\\Desktop\\selenium tests\\CucumberExercise1\\resources\\screenshotforsecondtest.png";

    /**
     * Browser selector
     *
     * @param selector
     * @return browser selection
     * @throws Throwable if the user do not select any of the given browsers
     */

    protected String browserSelector(String selector) throws Throwable {

        if (selector.equalsIgnoreCase("CHROME")) {
            ChromeOptions ops = new ChromeOptions(); //for disabling hotification
            ops.addArguments("--disable-notifications"); //for disabling notification
            ops.addArguments("--start-maximized"); // maximaze windows
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\edibi\\Desktop\\selenium tests\\CucumberExercise1\\resources\\chromedriver.exe"); // change path to folder where you put driver
            driver = new ChromeDriver(ops);
            return selector;
        } else if (selector.equalsIgnoreCase("FIREFOX")) {
            FirefoxOptions ops = new FirefoxOptions();
            ops.addArguments("--disable-notifications"); //for disabling notification
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\edibi\\Desktop\\selenium tests\\CucumberExercise1\\resources\\geckodriver.exe"); // change path to folder where you put driver
            driver = new FirefoxDriver();
            return selector;
        } else if (selector.equalsIgnoreCase("IE")) {
            System.setProperty("webdriver.ie.driver", "C:\\Users\\edibi\\Desktop\\selenium tests\\CucumberExercise1\\resources\\IEDriverServer.exe"); // change path to folder where you put driver
            driver = new InternetExplorerDriver();
            return selector;
        }
        throw new IllegalCharsetNameException("Select one of the three browsers CHROME , Firefox or IE");
    }


    /**
     * Method for image capturing
     *
     * @param imgCapture
     * @return selected image
     * @throws IOException if image is not saved throw a Exception
     */

    public String imageCapture(String imgCapture) throws IOException {
        File screenshotValidator = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        if (imgCapture.equalsIgnoreCase(cucumberExercise1png)) {
            copyFile(screenshotValidator, new File(cucumberExercise1png));
            return imgCapture;
        } else if (imgCapture.equalsIgnoreCase(CucumberExerciseWithDataTablespng)) {
            copyFile(screenshotValidator, new File(CucumberExerciseWithDataTablespng));
            return imgCapture;
        }
        throw new IllegalPathStateException("Image has not been saved");
    }


    /**
     * Method for scrolling until some value (see param and add value to scroll to)
     *
     * @param idToscrollTo
     */
    protected void ScrollingMethod(String idToscrollTo) {

        WebElement element = driver.findElement(By.id(idToscrollTo));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public WebElement SelectCountry(String country) throws Throwable {
        List<WebElement> cntry = driver.findElements(By.xpath("//*[@id=\"ListViewInner\"]"));
        for (WebElement div : cntry) {
            List<WebElement> cntr = div.findElements(By.cssSelector("ul.lvdetails.left.space-zero.full-width"));
            for (WebElement cn : cntr) {

                String countrySwitch = String.valueOf(cn.getText());
                if (countrySwitch.contains(country)) {
                    WebElement parent = cn.findElement(By.xpath(".."));
                    return parent;


                }

            }

        }

        throw new IllegalCharsetNameException("Please enter a valid country name in format eg From France");
    }
}

