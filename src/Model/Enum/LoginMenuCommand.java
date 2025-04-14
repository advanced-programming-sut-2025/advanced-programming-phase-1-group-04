package Model.Enum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommand {

    MenuEnter ("menu enter (?<menu_name>.+)"),
    ShowCurrentMenu (""),
    ExitMenu ("");

    private final String pattern;

    LoginMenuCommand(String pattern) {
        this.pattern = pattern;
    }

x
}
