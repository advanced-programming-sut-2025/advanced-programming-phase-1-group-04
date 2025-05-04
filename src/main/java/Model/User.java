package Model;

import Model.Enum.SecurityQuestion;

public class User {
    private final int id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private final String gender;

    private SecurityQuestion question;
    private String answer;

    private int games = 0;
    private int mostCoinsEarned = 0;


    public User(String username, String password, String nickname, String email, String gender) {
        this.id = App.getNumberOfUsers() + 1;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
    }

    public SecurityQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SecurityQuestion question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void setMostCoinsEarned(int mostCoinsEarned) {
        this.mostCoinsEarned = mostCoinsEarned;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("User Information:\n").append("______________________________\n");
        result.append("Username: ").append(this.username).append("\n")
                .append("Nickname: ").append(this.nickname).append("\n")
                .append("Most coins earned: ").append(this.mostCoinsEarned).append("\n")
                .append("Number of games played: ").append(this.games).append("\n");
        return result.toString();
    }
}