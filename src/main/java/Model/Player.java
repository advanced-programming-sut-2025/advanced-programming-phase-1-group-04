package Model;

import Model.Enum.Skill;
import Model.Map.Coordinate;

import java.util.HashMap;

public class Player {
    private final int id;
    private final String gender;

    private Coordinate coordinate;
    private int energy = 200;
    private Inventory inventory = new Inventory(12);
    public final HashMap<Skill, Integer> myAbility = new HashMap<>();

    private int count;

    //animal friendship
    //player friendship
    //NPC friendship

    //gift list
    //ask marriage list?
    //trade list
    //quest list

    //gift history
    //talk history
    //trade history

    public Player(int id, String gender) {
        this.id = id;
        this.gender = gender;
    }

    public int getAbilityLevel (Skill skill) {
        return (100 * this.myAbility.get(skill) + 50);
    }
}
