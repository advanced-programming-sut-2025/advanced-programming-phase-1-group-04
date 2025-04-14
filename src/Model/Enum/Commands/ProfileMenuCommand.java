package Model.Enum.Commands;

public enum ProfileMenuCommand implements Command {
    CurrentMenu ("show current menu"),
    ChangeUsername ("change username -u <username>"),
    ChangeNickname ("change nickname -u <nickname>"),
    ChangeEmail ("change email -e <email>"),
    ChangePassword ("change password -p <new_password> -o <old_password>"),
    Info ("user info");

    private final String pattern;

    ProfileMenuCommand (String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
