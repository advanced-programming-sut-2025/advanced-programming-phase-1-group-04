package Model.Map;

import Model.Animals.Animal;
import Model.Animals.AnimalProduct;
import Model.Animals.Fish;
import Model.Cooking.Food;
import Model.Crafting.Craft;
import Model.NPC.NPC;
import Model.Plants.*;


public class Tile {
    private TileType type;

    //private boolean isDoor = false; TODO: baraye in faz dar hesab nakardam
    private BuildingType buildingType = null;

    private Item item = null;
    private boolean isPavement = false;
    private NPC npc = null; // TODO: npc nadarim felan dosrost shod add mikonam

    private boolean isPlowed = false;
    private boolean isWatered = false;
    private Animal animal = null;

    public Tile(TileType type) { // For Water, Mountain, Mine
        this.type = type;
    }

    public Tile(TileType type, BuildingType buildingType) { // For Building
        this.type = type;
        this.buildingType = buildingType;
    }

    public Tile(TileType type, boolean isPavement, Item item, NPC npc){ // For Ground
        this.type = type;
        this.isPavement = isPavement;
        this.item = item;
        this.npc = npc;
    }

    public String getSymbol() {
        switch (this.type) {
            case Building:
                switch (this.buildingType) {
                    case GreenHouse:
                        return Symbols.GreenHouse.getSymbol();
                    case House:
                        return Symbols.House.getSymbol();
                    case Barn:
                        return Symbols.Barn.getSymbol();
                    case BigBarn:
                        return Symbols.BigBarn.getSymbol();
                    case DeluxeBarn:
                        return Symbols.DeluxeBarn.getSymbol();
                    case Coop:
                        return Symbols.Coop.getSymbol();
                    case BigCoop:
                        return Symbols.BigCoop.getSymbol();
                    case DeluxeCoop:
                        return Symbols.DeluxeCoop.getSymbol();
                    case Well:
                        return Symbols.Well.getSymbol();
                    case ShippingBin:
                        return Symbols.ShippingBin.getSymbol();
                    case Blacksmith:
                        return Symbols.Blacksmith.getSymbol();
                    case JojaMart:
                        return Symbols.JojaMart.getSymbol();
                    case PierresGeneralStore:
                        return Symbols.PierresGeneralStore.getSymbol();
                    case CarpentersShop:
                        return Symbols.CarpentersShop.getSymbol();
                    case FishShop:
                        return Symbols.FishShop.getSymbol();
                    case MarniesRanch:
                        return Symbols.MarniesRanch.getSymbol();
                    case TheStarDropSaloon:
                        return Symbols.TheStarDropSaloon.getSymbol();
                    default:
                        throw new IllegalArgumentException("Invalid tile");
                }
            case Ground:
                if (this.item instanceof Tree) {
                    return Symbols.Tree.getSymbol();
                } else if (this.item instanceof Sapling) {
                    return Symbols.Sapling.getSymbol();
                } else if (this.item instanceof Crop) {
                    return Symbols.Crop.getSymbol();
                } else if (this.item instanceof Fruit) {
                    return Symbols.Fruit.getSymbol();
                } else if (this.item instanceof Seed) {
                    return Symbols.Seed.getSymbol();
                } else if (this.item instanceof ForagingCrop) {
                    return Symbols.ForagingCrop.getSymbol();
                } else if (this.item instanceof ForagingMineral) {
                    return Symbols.ForagingMineral.getSymbol();
                } else if (this.item instanceof Stone) {
                    return Symbols.Stone.getSymbol();
                } else if (this.item instanceof Wood) {
                    return Symbols.Wood.getSymbol();
                } else if (this.item instanceof Craft) {
                    return Symbols.Craft.getSymbol();
                } else if (this.item instanceof Food) {
                    return Symbols.Food.getSymbol();
                } else if (this.item instanceof Fish) {
                    return Symbols.Fish.getSymbol();
                } else if (this.item instanceof AnimalProduct) {
                    return Symbols.AnimalProduct.getSymbol();
                }

                if (this.isPavement) {
                    return Symbols.Pavement.getSymbol();
                }

                if (this.npc != null) {
                    return Symbols.Pavement.getSymbol();
                }

                if (this.animal != null) {
                    return Symbols.Animal.getSymbol();
                }
            case Water:
                return Symbols.Water.getSymbol();
            case Mountain:
                return Symbols.Mountain.getSymbol();
            case Mine:
                return Symbols.Mine.getSymbol();
            default:
                throw new IllegalArgumentException("Invalid tile");
        }
    }

    public boolean isWalkable() {
        switch (this.type) {
            case Building:

            case Ground:
                if (this.item instanceof Tree) {
                    return false;
                } else if (this.item instanceof Sapling) {
                    return false;
                } else if (this.item instanceof ForagingMineral) {
                    return false;
                } else if (this.item instanceof Stone) {
                    return false;
                } else if (this.item instanceof Wood) {
                    return false;
                } else if (this.npc != null) {
                    return false;
                } else {
                    return true;
                }
            case Water:
                return false;
            case Mountain:
                return false;
            case Mine:
                return true;
            default:
                throw new IllegalArgumentException("Invalid tile");
        }
    }

}
