package BackgroundClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static BasicCheck.Authorization.driver;


public class ElementsScreens {

    public static WebElement Search() {
        return driver.findElement(By.id("text"));
    }

    public static WebElement ButtonSearch() {
        return driver.findElement(By.className("search2__button"));
    }

    public static WebElement ResultSearch() {
        return driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div[1]/div[1]/ul"));
    }

}
