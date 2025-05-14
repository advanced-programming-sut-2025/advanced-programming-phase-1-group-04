package Model.Shop;

import Model.Animals.AnimalType;
import Model.Shop.CarpentersShop.CarpenterBuildings;

public class Product {

    private ProductInterface productType;
    private int dailyLimit;
    private int price;

    public void setProductType(ProductInterface productType) {
        this.productType = productType;
    }

    public void setDailyLimit() {
        this.dailyLimit = productType.getDailyLimit();
    }

    public void setPrice() {
        this.price = productType.getPrice();
    }

    public ProductInterface getProductType() {
        return productType;
    }

    public int getDailyLimit() {
        return dailyLimit;
    }

    public int getPrice() {
        return price;
    }

    public boolean isRecipe () {
        for (RecipeType recipe : RecipeType.values()) {
            if (this.getProductType().getName().equals(recipe.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnimal() {
        for (AnimalType animal : AnimalType.values()) {
            if (this.getProductType().getName().equals(animal.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isFarmBuilding() {
        for (CarpenterBuildings building : CarpenterBuildings.values()) {
            if (this.getProductType().getName().equals(building.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isItem() {
        return !(isFarmBuilding() || isAnimal() || isRecipe());
    }
}
