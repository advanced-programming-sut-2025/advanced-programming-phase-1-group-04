package Model.Command;

public enum GameMenuCommand implements Command {
    GoMenu ("go to (?<menu>main|login|game|profile) menu"),
    CurrentMenu ("show current menu"),
    Exit ("menu exit");

    private final String pattern;

    GameMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
