package Model.Command;

public enum LoginMenuCommand implements Command {
    Register("register -u (?<username>\\S+) -p (?<password>\\S+)(?: (?<rePassword>\\S+))? -n (?<nickname>\\S+) -e (?<email>\\S+) -g (?<gender>\\S+)"),
        Name ("^[a-zA-Z0-9_]+$"),
        Password("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]+$"),
        Email("^[A-Za-z0-9][A-Za-z0-9_.-]*[A-Za-z0-9]@[a-z](?![a-z]*[-.][a-z]*[-.][a-z]*[a-z]\\.)[a-z.\\-]{1,5}[a-z]\\.(org|com|net|edu)$"),
        Gender("^male|female$"),
        SecurityQuestion("pick question -q (?<questionNumber>[0-9]+) -a (?<answer>.+?) -c (?<reAnswer>.+)"),

    Login("login -u (?<username>\\S+) -p (?<password>\\S+)(?: (?<loggedIn>-stay-logged-in))?"),
    ForgetPassword("forget password -u (?<username>\\S+)"),
        Answer("answer -a (?<answer>.+)"),

    GoMenu("go to (?<menu>main|login|game|profile) menu"),
    CurrentMenu("show current menu"),
    Exit("menu exit");

    private final String pattern;

    LoginMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
