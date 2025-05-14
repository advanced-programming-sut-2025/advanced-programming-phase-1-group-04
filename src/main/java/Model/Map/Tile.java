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
                        return Symbols.GreenHouse.getColoredSymbol();
                    case GreenHouserBuild:
                        return Symbols.GreenHouserBuild.getColoredSymbol();
                    case House:
                        return Symbols.House.getColoredSymbol();
                    case Barn:
                        return Symbols.Barn.getColoredSymbol();
                    case BigBarn:
                        return Symbols.BigBarn.getColoredSymbol();
                    case DeluxeBarn:
                        return Symbols.DeluxeBarn.getColoredSymbol();
                    case Coop:
                        return Symbols.Coop.getColoredSymbol();
                    case BigCoop:
                        return Symbols.BigCoop.getColoredSymbol();
                    case DeluxeCoop:
                        return Symbols.DeluxeCoop.getColoredSymbol();
                    case Well:
                        return Symbols.Well.getColoredSymbol();
                    case ShippingBin:
                        return Symbols.ShippingBin.getColoredSymbol();
                    case Blacksmith:
                        return Symbols.Blacksmith.getColoredSymbol();
                    case JojaMart:
                        return Symbols.JojaMart.getColoredSymbol();
                    case PierresGeneralStore:
                        return Symbols.PierresGeneralStore.getColoredSymbol();
                    case CarpentersShop:
                        return Symbols.CarpentersShop.getColoredSymbol();
                    case FishShop:
                        return Symbols.FishShop.getColoredSymbol();
                    case MarniesRanch:
                        return Symbols.MarniesRanch.getColoredSymbol();
                    case TheStarDropSaloon:
                        return Symbols.TheStarDropSaloon.getColoredSymbol();
                    default:
                        throw new IllegalArgumentException("Invalid tile");
                }
            case Ground:
                if (this.item instanceof Tree) {
                    return Symbols.Tree.getColoredSymbol();
                } else if (this.item instanceof Sapling) {
                    return Symbols.Sapling.getColoredSymbol();
                } else if (this.item instanceof Crop) {
                    return Symbols.Crop.getColoredSymbol();
                } else if (this.item instanceof Fruit) {
                    return Symbols.Fruit.getColoredSymbol();
                } else if (this.item instanceof Seed) {
                    return Symbols.Seed.getColoredSymbol();
                } else if (this.item instanceof ForagingCrop) {
                    return Symbols.ForagingCrop.getColoredSymbol();
                } else if (this.item instanceof ForagingMineral) {
                    return Symbols.ForagingMineral.getColoredSymbol();
                } else if (this.item instanceof Stone) {
                    return Symbols.Stone.getColoredSymbol();
                } else if (this.item instanceof Wood) {
                    return Symbols.Wood.getColoredSymbol();
                } else if (this.item instanceof Craft) {
                    return Symbols.Craft.getColoredSymbol();
                } else if (this.item instanceof Food) {
                    return Symbols.Food.getColoredSymbol();
                } else if (this.item instanceof Fish) {
                    return Symbols.Fish.getColoredSymbol();
                } else if (this.item instanceof AnimalProduct) {
                    return Symbols.AnimalProduct.getColoredSymbol();
                }

                if (this.npc != null) {
                    return Symbols.Pavement.getColoredSymbol();
                }

                if (this.animal != null) {
                    return Symbols.Animal.getColoredSymbol();
                }

                if (this.isPavement) {
                    return Symbols.Pavement.getColoredSymbol();
                }

                if (this.item == null) {
                    return Symbols.Ground.getColoredSymbol();
                }
            case Water:
                return Symbols.Water.getColoredSymbol();
            case Mountain:
                return Symbols.Mountain.getColoredSymbol();
            case Mine:
                return Symbols.Mine.getColoredSymbol();
            default:
                throw new IllegalArgumentException("Invalid tile");
        }
    }

    public boolean isWalkable() {
        switch (this.type) {
            case Building:
                if (this.buildingType.equals(BuildingType.GreenHouse)) return false;
                return true;
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
            case Water, Mountain:
                return false;
            case Mine:
                return true;
            default:
                throw new IllegalArgumentException("Invalid tile");
        }
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public void setPlowed(boolean plowed) {
        isPlowed = plowed;
    }

    public TileType getType() {
        return type;
    }

    public void setWatered(boolean watered) {
        isWatered = watered;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isPlowed() {
        return isPlowed;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }
}
