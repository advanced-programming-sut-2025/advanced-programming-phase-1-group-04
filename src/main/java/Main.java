
import Model.Map.MakeRegionJson;
import View.AppView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MakeRegionJson.SaveJson("Farming2");
        (new AppView()).run();
    }
}
