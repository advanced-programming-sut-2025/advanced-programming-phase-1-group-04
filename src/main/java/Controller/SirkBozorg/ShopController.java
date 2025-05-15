package Controller.SirkBozorg;

import Controller.GameMenuController;
import Model.App;
import Model.Map.BuildingType;
import Model.Map.TileType;
import Model.Result;
import Model.Shop.ShopType;

public class ShopController {
    public static Result cheatAddCount(String stringCount) {
        int count = Integer.parseInt(stringCount);
        if (count < 0) {
            return new Result(false, "Count should be positive!");
        }
        App.getCurrentGame().getCurrentPlayer().addCount(count);
        return new Result(true, "Now your account has been updated to: "  + App.getCurrentGame().getCurrentPlayer().getCount());
    }

    public static Result showAllProducts () {
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getType() != TileType.Building) {
            return new Result (false, "you must be in a shop to use this command!");
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.Blacksmith) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.Blacksmith).showAllProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.CarpentersShop) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.CarpentersShop).showAllProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.FishShop) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.FishShop).showAllProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.JojaMart) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.JojaMart).showAllProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.MarniesRanch) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.MarniesRanch).showAllProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.PierresGeneralStore) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.PierresGeneralStore).showAllProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.TheStarDropSaloon) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.TheStarDropSaloon).showAllProducts());
        }
        return new Result (false, "you must be in a shop to use this command!");
    }

    public static Result showAvailableProducts() {
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getType() != TileType.Building) {
            return new Result (false, "you must be in a shop to use this command!");
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.Blacksmith) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.Blacksmith).showAvailableProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.CarpentersShop) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.CarpentersShop).showAvailableProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.FishShop) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.FishShop).showAvailableProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.JojaMart) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.JojaMart).showAvailableProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.MarniesRanch) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.MarniesRanch).showAvailableProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.PierresGeneralStore) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.PierresGeneralStore).showAvailableProducts());
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.TheStarDropSaloon) {
            return new Result(true, App.getCurrentGame().getShop(ShopType.TheStarDropSaloon).showAvailableProducts());
        }
        return new Result (false, "you must be in a shop to use this command!");
    }

    public static Result purchaseWithNumber (String productName, String count) {
        int number;
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getType() != TileType.Building) {
            return new Result (false, "you must be in a shop to use this command!");
        }
        if ((number = Integer.parseInt(count)) <= 0) {
            return new Result(false, "\"count\" must be a positive number!");
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.Blacksmith) {
            return App.getCurrentGame().getShop(ShopType.Blacksmith).buy(productName, number, null);
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.CarpentersShop) {
            return App.getCurrentGame().getShop(ShopType.CarpentersShop).buy(productName, number, null);
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.FishShop) {
            return App.getCurrentGame().getShop(ShopType.FishShop).buy(productName, number, null);
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.JojaMart) {
            return App.getCurrentGame().getShop(ShopType.JojaMart).buy(productName, number, null);
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.MarniesRanch) {
            return App.getCurrentGame().getShop(ShopType.MarniesRanch).buy(productName, number, null);
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.PierresGeneralStore) {
            return App.getCurrentGame().getShop(ShopType.PierresGeneralStore).buy(productName, number, null);
        }
        if (App.getCurrentGame().getTile(App.getCurrentGame().getCurrentPlayer().getCoordinate()).getBuildingType() == BuildingType.TheStarDropSaloon) {
            return App.getCurrentGame().getShop(ShopType.TheStarDropSaloon).buy(productName, number, null);
        }
        return new Result (false, "you must be in a shop to use this command!");
    }
}
