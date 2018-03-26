package BackgroundClasses;

import java.util.*;


public class CheckingOS {

    static String CheckingMyOS(){
        String CheckOS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        String OS = null;
        if(CheckOS.contains("win")){
            OS = "src\\test\\resources\\";
        } else if (CheckOS.contains("nix") || CheckOS.contains("nux")
                || CheckOS.contains("aix")){
            OS = "src/test/resources/";
        }
        return OS;
    }

    public static String GiveDriver(){
        String driverChrome;
        String MyOS = CheckingMyOS();
        if (MyOS.contains("\\")){
            driverChrome = MyOS+"chromedriver.exe";
        } else {
            driverChrome = MyOS+"chromedriver";
        }
        return driverChrome;
    }
}
