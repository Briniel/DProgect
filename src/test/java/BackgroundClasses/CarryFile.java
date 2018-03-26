package BackgroundClasses;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static BackgroundClasses.CheckingOS.CheckingMyOS;


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

        File sourseEnvironment = new File(sourse + "environment.properties");
        File destEnvironment = new File(dest + "environment.properties");
        FileUtils.copyFile(sourseEnvironment, destEnvironment);

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
