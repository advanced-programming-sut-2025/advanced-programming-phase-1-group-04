package Model;

import Model.Enum.Skill;
import Model.Map.Coordinate;

import java.util.HashMap;

public class Player {
    private final int id;
    private final String gender;

    private Coordinate coordinate;
    private int energy = 200;
    private Inventory inventory;
    public final HashMap<Skill, Integer> myAbility = new HashMap<>();
    //animal friendship

    public Player(int id, String gender) {
        this.id = id;
        this.gender = gender;
    }

    public int getAbilityLevel (String ability) {
        return (100 * this.myAbility.get(Skill.valueOf(ability)) + 50);
    }
}
