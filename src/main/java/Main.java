
import Model.Map.MakeRegionJson;
import View.AppView;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MakeRegionJson.SaveJson("Path4");
        (new AppView()).run();
    }
}
