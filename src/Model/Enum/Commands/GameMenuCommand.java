package Model.Enum.Commands;

public enum GameMenuCommand implements Command {
    CurrentMenu ("show current menu"),
    NewGame ("game new -u <username_1> <username_2> <username_3>"),
    LoadGame ("load game"),
    ExitGame ("exit game"),
    DeleteGame ("delete game"),
    NextTurn ("next turn");

    // Time

    private final String pattern;

    GameMenuCommand (String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
