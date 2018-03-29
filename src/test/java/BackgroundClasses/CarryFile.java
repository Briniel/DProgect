package BackgroundClasses;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static BackgroundClasses.CheckingOS.CheckingMyOS;
import static BasicCheck.Authorization.driver;


public class CarryFile {

    public static void Carry() throws IOException {
        String MyOS = CheckingMyOS();

        String sourse;
        String dest;

        if (MyOS.contains("\\")){
            sourse = MyOS;
            dest = "target\\allure-results\\";
        } else {
            sourse = MyOS;
            dest = "target/allure-results/";
        }

        File Environment = new File(dest + "environment.properties");
        FileWriter writer = new FileWriter(Environment);
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName();
        String os = cap.getPlatform().toString();
        String v = cap.getVersion().toString();


        writer.write(
                "Browser=" + browserName + "\n" +
                        "Browser.Version=" + v +"\n" +
                        "Stand=" + os + "\n" +
                        "URL=http://www.nashe.ru/||https://yandex.ru/" + "\n" +
                        "NameProgect=DProgect");

        writer.flush();
        writer.close();


        File sourseCategories = new File(sourse + "categories.json");
        File destCategories = new File(dest+ "categories.json");
        FileUtils.copyFile(sourseCategories, destCategories);
    }

    public static void CreatureFile(List<WebElement> saveSong, String name) throws IOException {
        FileWriter fr = new FileWriter("src/test/resources/song"+name+".csv");
        fr.write("\""+"Всего песен: "+saveSong.size()+"\""+'\n');
        for (int i = 0; i < saveSong.size(); i++){
            fr.write("\""+saveSong.get(i).getText()+"\""+'\n');
        }
        fr.flush();
    }



}
