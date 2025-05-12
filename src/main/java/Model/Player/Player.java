package Model.Player;

import Model.Crafting.CraftRecipe;
import Model.Crafting.CraftType;
import Model.Map.*;

import Model.Map.Coordinate;
import Model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map;

public class Player {
    private final int id;

    private Coordinate coordinate;
    //private final Coordinate houseCoordinate;

    private int maxEnergy = 200;
    private int energy = 200;
    private int maxMovesInTurn = 20; //TODO: ????????????????????????????
    private int movesThisTurn = 0;

    private Inventory inventory = new Inventory(12);
    private HashMap<Skill, Integer> myAbility = new HashMap<>(Map.of(Skill.Mining, 0, Skill.Foraging, 0,
            Skill.Fishing, 0, Skill.Farming, 0));

    private int count; //TODO: in chie?

    private HashMap<Skill, Integer> skillBuff = new HashMap<>(Map.of(Skill.Mining, 0, Skill.Foraging, 0,
            Skill.Fishing, 0, Skill.Farming, 0));
    private int maxEnergyBuff = 0;

    private ArrayList<CraftRecipe> craftRecipes = new ArrayList<>(List.of(CraftRecipe.Furnace,
            CraftRecipe.Scarecrow, CraftRecipe.MayonnaiseMachine));

    //List Recipe
    //List Tool

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

    public Player(int id) {
        this.id = id;
    }

    public int getAbilityLevel (Skill skill) {
        return Math.min(4, Math.max(0, (this.myAbility.get(skill) - 50) / 100));
    }

    public int getId() {
        return id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getEnergy() {
        return energy;
    }

    public void addEnergy(int energy) {
        this.energy += energy;
        if (this.energy > maxEnergy) {
            this.energy = maxEnergy;
        }
        else if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean addItemToInventory (Item item, int quantity) {
        return inventory.addItem(item, quantity);
    }

    public boolean removeItemFromInventory (Item item, int quantity) {
        return inventory.removeItem(item, quantity);
    }

    public void setInventoryCapacity(double capacity) {
        inventory.setCapacity(capacity);
    }

    public double getInventoryCapacity () {
        return inventory.getCapacity();
    }

    public String getUsername() {
        // TODO: وقت شد درستش کن نره همه رو بگرده هر دفعه:/
        File usersFolder = new File("users");
        File[] userFiles = usersFolder.listFiles((dir, name) -> name.endsWith(".json"));

        if (userFiles != null) {
            Gson gson = new Gson();
            for (File userFile : userFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                    User user = gson.fromJson(reader, User.class);
                    if (user.getId() == this.id)
                        return user.getUsername();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void addAbility (Skill skill, int value) {
        int currentValue = myAbility.get(skill);
        currentValue += value;
        myAbility.put(skill, Math.max(currentValue, 0));
    }

    public void addSkillBuff (Skill skill, int value) {
        for (Skill s : skillBuff.keySet()) {
            skillBuff.put(s, 0);
        }
        maxEnergy = 200;
        myAbility.put(skill, value);
    }

    public void reduceBuff (int value) {
        for (Skill s : skillBuff.keySet()) {
            int currentValue = skillBuff.get(s);
            currentValue -= value;
            skillBuff.put(s, Math.max(currentValue, 0));
        }
        maxEnergyBuff -= value;
        if (maxEnergyBuff <= 0) {
            maxEnergy = 200;
            maxEnergyBuff = 0;
        }
    }

    public void addMaxEnergyBuff (int time, int value){
        maxEnergyBuff += time;
        maxEnergy += value;
    }

    public String getName() {
        //TODO: for bezan roo user ha........................................................
        return "";
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxMovesInTurn() {
        return maxMovesInTurn;
    }

    public int getMovesThisTurn() {
        return movesThisTurn;
    }

    public void setMaxMovesInTurn(int maxMovesInTurn) {
        this.maxMovesInTurn = maxMovesInTurn;
    }

    public void addMovesThisTurn() {
        this.movesThisTurn ++;
    }
}
