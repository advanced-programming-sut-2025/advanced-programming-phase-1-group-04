package Controller.SirkBozorg;

import Model.App;
import Model.Map.BuildingType;
import Model.Result;
import Model.Shop.ShopType;

public class AnimalController {
    public static Result buyAnimal (String animal, String name) {
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() != BuildingType.MarniesRanch) {
            return new Result(false, "you must be in Marnie's Ranch to be able to buy an animal!");
        }
        return App.getCurrentGame().getShop(ShopType.MarniesRanch).buy(animal, 1, name);
    }
}
