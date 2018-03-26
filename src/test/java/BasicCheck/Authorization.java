package BasicCheck;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static BackgroundClasses.CarryFile.Carry;
import static BackgroundClasses.CheckingOS.GiveDriver;


public class Authorization {

    public static WebDriver driver;

    @BeforeSuite
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", GiveDriver());
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterSuite(alwaysRun = true)
    public static void tearDown() throws IOException {
        Carry();
        driver.quit();
    }
}
