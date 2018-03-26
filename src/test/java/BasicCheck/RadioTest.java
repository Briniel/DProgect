package BasicCheck;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static BackgroundClasses.CarryFile.CreatureFile;
import static BackgroundClasses.saveSongs.Save;
import static BackgroundClasses.saveSongs.SortingSongs;
import static BasicCheck.Authorization.*;


@Feature("Test Radio")
public class RadioTest {

    private ArrayList<String> songNowSave;
    private ArrayList<String> songTopSave;

    @Attachment(value = "Список песен", type = "text/csv")
    private byte[] saveSvgAttachment(String name) throws IOException, URISyntaxException {
        return getSampleFile("song"+name+".csv");
    }

    private byte[] getSampleFile(String fileName) throws IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        return Files.readAllBytes(Paths.get(resource.toURI()));
    }

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void SearchForNewSongs() throws IOException, URISyntaxException {
        WebDriverWait wait = (new WebDriverWait(driver, 20));
        driver.get("http://www.nashe.ru/");
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[contains(text(), '10 последних песен')]")));
        driver.findElement(By.xpath("//div[@class=\"online\"]/div/a[2]")).click();
        List<WebElement> songNow = driver.findElements(By.xpath("//div[@class=\"barPlaylist\"]/ul[@class=\"inner\"]/li/div"));
        songNowSave = Save(songNow);
        CreatureFile(songNow, "Menu");
        saveSvgAttachment("Menu");
    }

    @Test(dependsOnMethods = "SearchForNewSongs", alwaysRun = true)
    public void GoToTheTop(){
        WebDriverWait wait = (new WebDriverWait(driver, 15));
        driver.findElement(By.className("menuButton")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("menu-item-8684")));
        driver.findElement(By.id("menu-item-8684")).click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h1[contains(text(), 'Чартова дюжина')]")));
    }

    @Test(dependsOnMethods = "GoToTheTop")
    @Description("Some detailed test description")
    public void GettingTopSongs() throws IOException, URISyntaxException {
        List<WebElement> songOld = driver.findElements(By.xpath("//div[@class=\"results\"]/div[2]/div/div[@class=\"artist-song\"]"));
        CreatureFile(songOld, "Top");
        saveSvgAttachment("Top");
        songTopSave = Save(songOld);
    }

    @Test(dependsOnMethods = "GettingTopSongs")
    @Description("In this test there should be an error")
    @Story("Error test")
    @Severity(SeverityLevel.MINOR)
    public void Comparison(){
        ArrayList<String> SaveTopSongs = SortingSongs(songTopSave, songNowSave);
        Assert.assertFalse(SaveTopSongs.size() == 0, "Нет совпадений в композициях");
    }
}