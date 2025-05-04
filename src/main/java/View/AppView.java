package View;

import Model.App;
import Model.Command.Menu;

import java.io.IOException;
import java.util.Scanner;

public class AppView {
    Scanner scanner = new Scanner(System.in);

    public void run() throws IOException {
        while (App.getCurrentMenu() != Menu.ExitMenu) {
            App.getCurrentMenu().checkCommand(scanner);
        }
    }
}
