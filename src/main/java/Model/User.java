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
}