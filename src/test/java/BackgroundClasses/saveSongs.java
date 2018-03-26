package BackgroundClasses;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class saveSongs {
    public static ArrayList<String> Save(List<WebElement> ListSaveSong){
        ArrayList<String> SaveSong = new ArrayList<>();

        for (int i = 0; i < ListSaveSong.size(); i++){
            SaveSong.add(ListSaveSong.get(i).getText());
        }

        return SaveSong;
    }

    public static ArrayList<String> SortingSongs(ArrayList<String> Top,ArrayList<String> ManeScreen){
        ArrayList<String> SaveSong = new ArrayList<>();

        for (int i = 0; i < Top.size(); i++){
            for (int j = 0; j < ManeScreen.size(); j++){
                if (Top.get(i).equals(ManeScreen.get(j))){
                    SaveSong.add(Top.get(i));
                    Top.remove(i);
                    i=i-1;
                    break;
                }
            }
        }

        return SaveSong;
    }

}
