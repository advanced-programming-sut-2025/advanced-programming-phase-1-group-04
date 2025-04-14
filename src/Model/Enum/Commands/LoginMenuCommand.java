package Model.Enum.Commands;

public enum LoginMenuCommand implements Command {
    CurrentMenu ("show current menu"),
    Register ("register -u <username> -p <password> <password_confirm> -n <nickname> -e <email> -g <gender>"),
    PickQuestion("pick question -q <question_number> -a <answer> -c <answer_confirm>"),
    Login ("login -u <username> -p <password> –stay-logged-in"),
    ForgetPassword ("forget password -u <username>"),
    Answer ("answer -a <answer>"),
    ExitMenu ("");

    private final String pattern;

    LoginMenuCommand (String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
