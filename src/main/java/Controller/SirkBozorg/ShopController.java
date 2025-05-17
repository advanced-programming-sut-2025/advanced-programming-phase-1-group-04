package Controller.SirkBozorg;

import Controller.GameMenuController;
import Model.App;
import Model.Map.*;
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
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
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
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
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
        if (App.getCurrentGame().getCurrentPlayer().getMovesThisTurn() >= App.getCurrentGame().getCurrentPlayer().getMaxMovesInTurn()) {
            return new Result (false, "you have no more moves! enter next turn!");
        }
        GameMenuController.moveControl();
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

    public static Result sell(String type, String name, String stringCount) {
        int count = (stringCount == null ) ? -1 : Integer.parseInt(stringCount);
        Item item = PlayerController.getItemByTypeName(type, name);

        // item error
        if (count < 1 && count != -1) {
            return new Result(false, "Count must be a positive number!");
        } else if (!PlayerController.isTypeValid(type)) {
            StringBuilder validType = new StringBuilder();
            for (ItemType itemType: ItemType.values()) {
                validType.append(itemType.getName()).append(" ");
            }
            return new Result(false, "Type is invalid. Valid types: {" + validType + "}");
        } else if (item == null) {
            return new Result(false, "Name is invalid!");
        } else if (item.getPrice() == 0) {
            return new Result(false, "You can't sell this item!");
        }
        // inventory error:
        // location error:
        else if (!isShippingBinAroundMe()) {
            return new Result(false, "To sell an item, you need to be within 8 tiles of shipping bin!");
        }

        App.getCurrentGame().getCurrentPlayer().addItemToShippingBin(item, count);
        if (!App.getCurrentGame().getCurrentPlayer().getInventory().removeItem(item.getName(), count)) {
            return new Result(false, "Oh shit here we go again(you can't remove this item from your inventory)");
        }

        return new Result(true, "Now you send this item to hell(shipping bin). Tomorrow به حسابت زده میشه");
    }

    private static boolean isShippingBinAroundMe() {
        Tile[][] fullMap = App.getCurrentGame().getMap().getFullMap();

        int x = App.getCurrentGame().getCurrentPlayer().getCoordinate().getX();
        int y = App.getCurrentGame().getCurrentPlayer().getCoordinate().getY();

        int[] dx = {-1, -1, -1,  0, 0,  1, 1, 1};
        int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if ((newX < 0 || newX >= 90) || (newY < 0 || newY >= 120)) continue;

            Tile tile = fullMap[newX][newY];
            if (tile.getType().equals(TileType.Building) && tile.getBuildingType().equals(BuildingType.ShippingBin)) {
                return true;
            }
        }

        return false;
    }
}
