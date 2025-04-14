package Model;

import Model.Enum.Season;
import Model.Enum.Skill;
import Model.Enum.SecurityQuestion;
import Model.Enum.WeekDay;
import Model.Map.Coordinate;

import java.util.HashMap;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private final String gender;
    private SecurityQuestion question;

    private Game currentGame;
    private Coordinate coordinate;
    private int energy = 200;
    private Inventory inventory;
    public final HashMap<Skill, Integer> myAbility = new HashMap<>();
    //animal friendship



    User(String username, String password, String nickname, String email, String gender) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
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

    public Game getCurrentGame() {
        return currentGame;
    }

    public String getGender() {
        return gender;
    }

    public int getAbilityLevel (String ability) {
        return (100 * this.myAbility.get(Skill.valueOf(ability)) + 50);
    }

    public void handleCrash()
    {
        DateAndTime newTime = new DateAndTime (0 , 0 , WeekDay.Monday , Season.Spring);
        if (this.energy == 0)
            App.currentTime = newTime;
    }
}