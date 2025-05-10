package Model;

import Model.Crafting.CraftRecipe;
import Model.Crafting.CraftType;
import Model.Enum.Skill;
import Model.Map.Coordinate;

import java.util.*;

public class Player {
    private final int id;
    private final String gender;

    private Coordinate coordinate;
    private int maxEnergy = 200;
    private int energy = 200;
    private Inventory inventory = new Inventory(12);
    private HashMap<Skill, Integer> myAbility = new HashMap<>(Map.of(Skill.Mining, 0, Skill.Foraging, 0,
            Skill.Fishing, 0, Skill.Farming, 0));

    private int count; //TODO: in chie?

    private HashMap<Skill, Integer> skillBuff = new HashMap<>(Map.of(Skill.Mining, 0, Skill.Foraging, 0,
            Skill.Fishing, 0, Skill.Farming, 0));
    private int maxEnergyBuff = 0;

    private ArrayList<CraftRecipe> craftRecipes = new ArrayList<>(List.of(CraftRecipe.Furnace,
            CraftRecipe.Scarecrow, CraftRecipe.MayonnaiseMachine));

//    private ArrayList<>

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
        return Math.min(4, Math.max(0, (this.myAbility.get(skill) - 50) / 100));
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
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

}
