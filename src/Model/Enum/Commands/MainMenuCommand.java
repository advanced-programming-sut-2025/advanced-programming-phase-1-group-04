package Model.Enum.Commands;

public enum MainMenuCommand implements Command {
    CurrentMenu ("show current menu"),
    Logout ("user logout"),
    EnterMenu ("menu enter (?<menuName>profile|game|login)");

    private final String pattern;

    MainMenuCommand (String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
