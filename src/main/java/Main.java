import Model.User;
import View.AppView;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        (new AppView()).run();
    }
}