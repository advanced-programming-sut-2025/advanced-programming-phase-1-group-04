package Model.Player;

import Controller.GameMenuController;
import Model.Animals.Animal;
import Model.App;
import Model.Cooking.FoodRecipe;
import Model.Crafting.CraftRecipe;
import Model.Map.*;

import Model.Map.Coordinate;
import Model.Tool.Tool;
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
    private final int farm; // Farm id 1-4

    private Coordinate coordinate;
    private final Coordinate houseCoordinate;

    private ShippingBin shippingBin = new ShippingBin();

    private final List<Animal> myAnimals = new ArrayList<>();
    private List<FarmBuilding> myFarmBuildings = new ArrayList<>();

    private int maxEnergy = 200;
    private int energy = 200;
    private int maxMovesInTurn = 15;
    private int movesThisTurn = 0;

    private Inventory inventory = new Inventory(12, 1);
    private HashMap<Skill, Integer> myAbility = new HashMap<>(Map.of(Skill.Mining, 0, Skill.Foraging, 0,
            Skill.Fishing, 0, Skill.Farming, 0));

    private int count;

    private Inventory refrigerator = new Inventory(Integer.MAX_VALUE, 1);

    private HashMap<Skill, Integer> skillBuff = new HashMap<>(Map.of(Skill.Mining, 0, Skill.Foraging, 0,
            Skill.Fishing, 0, Skill.Farming, 0));
    private int maxEnergyBuff = 0;

    private ArrayList<CraftRecipe> craftRecipes = new ArrayList<>(List.of(CraftRecipe.Furnace,
            CraftRecipe.Scarecrow, CraftRecipe.MayonnaiseMachine));

    private ArrayList<FoodRecipe> foodRecipes = new ArrayList<>(List.of(FoodRecipe.FriedEgg,
            FoodRecipe.BakedFish, FoodRecipe.Salad));


    private Tool currentTool;

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

    public Player(int id, int farm) {
        this.id = id;
        this.farm = farm;
        switch (farm) {
            case 1:
                this.houseCoordinate = new Coordinate(4, 31);
                break;
            case 2:
                this.houseCoordinate = new Coordinate(4, 31 + 80);
                break;
            case 3:
                this.houseCoordinate = new Coordinate(4 + 60, 31);
                break;
            case 4:
                this.houseCoordinate = new Coordinate(4 + 60, 31 + 80);
                break;
            default:
                throw new IllegalArgumentException("Invalid player farm");
        }

        this.coordinate = this.houseCoordinate;
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

    public Coordinate getHouseCoordinate() {
        return houseCoordinate;
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


    public boolean removeItemFromInventory(String itemName, int quantity) {
        return inventory.removeItem(itemName, quantity);
    }

    public boolean removeItemFromRefrigerator(String itemName, int quantity) {
        return refrigerator.removeItem(itemName, quantity);
    }

    public boolean moveItemFromInventoryToTrash(String itemName, int quantity) {
        int price = 0;
        Inventory.ItemStack stack = inventory.getItems().get(itemName.toLowerCase());

        if (stack != null) {
            if (quantity != -1) {
                int removableCount = Math.min(quantity, stack.getCount());
                for (int i = 0; i < removableCount; i++) {
                    Item item = stack.getItems().get(stack.getItems().size() - 1 - i);
                    price += item.getPrice();
                }
            } else {
                for (Item item : stack.getAll()) {
                    price += item.getPrice();
                }
            }

            price = price * (inventory.getTrashCanLevel() - 1) * 15 / 100;
        }

        boolean result = inventory.removeItem(itemName, quantity);
        if (result) {
            addCount(price);
        }
        return result;
    }


    public void setInventoryCapacity(int capacity) {
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

    public List<Animal> getMyAnimals() {
        return myAnimals;
    }

    public int getFarm() {
        return farm;
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
        skillBuff.put(skill, value);
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

    public boolean isBuffed (Skill s) {
        return (skillBuff.get(s) > 0);
    }

    public void addMaxEnergyBuff (int time, int value){
        maxEnergyBuff += time;
        maxEnergy += value;
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

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    public void resetMovesThisTurn() {
        this.movesThisTurn = 0;
    }

    public Tool getCurrentTool() {
        return currentTool;
    }

    public int getCount() {
        return count;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public boolean isMyFarm(Coordinate coordinate) {
        return switch (farm) {
            case 1 -> coordinate.getX() < 30 && coordinate.getY() < 40
                    && coordinate.getX() >= 0 && coordinate.getY() >= 0;
            case 2 -> coordinate.getX() < 30 && coordinate.getY() < 120
                    && coordinate.getX() >= 0 && coordinate.getY() >= 80;
            case 3 -> coordinate.getX() < 90 && coordinate.getY() < 120
                    && coordinate.getX() >= 60 && coordinate.getY() >= 80;
            case 4 -> coordinate.getX() < 90 && coordinate.getY() < 40
                    && coordinate.getX() >= 60 && coordinate.getY() >= 0;
            default -> throw new IllegalArgumentException("Invalid player farm");
        };
    }

    public void addAnimal(Animal animal) {
        this.myAnimals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        this.myAnimals.remove(animal);
    }

    @Override
    public String toString() {
        return "Player Info:\n" +
            "________________________________\n" +
            "Username: " + getUsername() + "\n" +
            "Id: " + this.id + "\n" +
            "Farm id: " + this.farm + "\n" +
            "Coordinate: (" + this.coordinate.getX() + ", " + this.coordinate.getY() + ")\n" +

            //"Max moves in this turn: " + this.maxMovesInTurn + "\n" +
            "Moves in this Turn: " + this.movesThisTurn + "\n" +

            "Count: " + this.count + "\n";
    }

    public ArrayList<CraftRecipe> getCraftRecipes() {
        return craftRecipes;
    }

    public boolean addToCraftRecipes(CraftRecipe recipe) {
        if (craftRecipes.contains(recipe)) {
            return false;
        }
        craftRecipes.add(recipe);
        return true;
    }

    public List<FarmBuilding> getMyFarmBuildings() {
        return myFarmBuildings;
    }

    public void addToFarmBuildings (FarmBuilding building) {
        for (FarmBuilding b : myFarmBuildings) {
            if (b.getType().getType() == building.getType().getType()) {
                myFarmBuildings.remove(b);
                myFarmBuildings.add(building);
                return;
            }
        }
        myFarmBuildings.add(building);
    }

    public int getFarmBuildingLevel (BuildingType type) {
        int max = 0;
        for (FarmBuilding b : myFarmBuildings) {
            if (b.getType().getType() == type) {
                if (b.getCapacity() > max) {
                    max = b.getCapacity();
                }
            }
        }
        return max;
    }

    public int getFarmBuildingCapacity (BuildingType type) {
        int animalNumber = 0;
        for (Animal a : myAnimals) {
            if (a.getType().getFarmBuildingType().getType() == type) {
                animalNumber++;
            }
        }
        return Math.max(0, getFarmBuildingLevel(type) * 4 - animalNumber);
    }

    public ArrayList<FoodRecipe> getFoodRecipes() {
        return foodRecipes;
    }

    public boolean addToFoodRecipes (FoodRecipe recipe) {
        if (foodRecipes.contains(recipe)) {
            return false;
        }
        foodRecipes.add(recipe);
        return true;
    }

    public void addItemToShippingBin(Item item, int count) {
        this.shippingBin.add(item, count);
    }

    public ShippingBin getShippingBin() {
        return shippingBin;
    }

    public int getInventoryItemCount (Item item) {
        return inventory.getItemQuantity(item);
        // if isn't available returns -1
    }

    public Inventory getRefrigerator() {
        return refrigerator;
    }

    public ArrayList<Item> getAndRemoveItemsFromInventory (int n, String itemName) {
        return inventory.getAndRemoveItems(n, itemName);
    }
}