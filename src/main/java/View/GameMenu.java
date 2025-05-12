package View;

import Controller.GameMenuController;

import Controller.SirkBozorg.PlayerController;
import Controller.SirkBozorg.MapController;
import Controller.SirkBozorg.TimeController;
import Controller.SirkBozorg.ToolController;
import Model.App;

import Model.Command.GameMenuCommand;
import Model.Player.Player;
import Model.Result;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Matcher matcher;

        if (GameMenuCommand.CurrentMenu.getMatcher(input) != null) {
            System.out.println(GameMenuController.currentMenu());
        }
        else if ((matcher = GameMenuCommand.NewGame.getMatcher(input)) != null) {
            Result result = GameMenuController.newGame(matcher.group("username1"), matcher.group("username2"), matcher.group("username3"));
            System.out.println(result);

            if (result.isSuccessful()) {
                ArrayList<Player> players = App.getCurrentGame().getPlayers();

                for (int i = 0; i < players.size(); i++) {
                    Player player = players.get(i);
                    System.out.println("Player " + player.getUsername() + ", choose your farm number (1-2):");

                    while (true) {
                        input = scanner.nextLine().trim();
                        if ((matcher = GameMenuCommand.ChooseMap.getMatcher(input)) != null) {
                            result = GameMenuController.chooseMap(i ,Integer.parseInt(matcher.group("mapNumber")));
                            System.out.println(result);
                            if (result.isSuccessful()) break;
                        } else {
                            System.out.println("Use this format: game map <1|2>");
                        }
                    }
                }
                System.out.println(GameMenuController.loadNewGame());
            }
        }
        else if (GameMenuCommand.LoadGame.getMatcher(input) != null) {
            System.out.println(GameMenuController.loadGame());
        }
        else if (GameMenuCommand.ExitGame.getMatcher(input) != null) {
            System.out.println(GameMenuController.exitGame());
        }
        else if (GameMenuCommand.DeleteGame.getMatcher(input) != null) {
            System.out.println(GameMenuController.deleteGame());
        }
        else if (GameMenuCommand.NextTurn.getMatcher(input) != null) {
            System.out.println(GameMenuController.nextTurn());
        }
        else if (GameMenuCommand.Time.getMatcher(input) != null) {
            System.out.println(TimeController.time());
        }
        else if ((matcher = GameMenuCommand.CheatTime.getMatcher(input)) != null) {
            System.out.println(TimeController.cheatTime(matcher.group("time")));
        }
        else if ((matcher = GameMenuCommand.CheatDate.getMatcher(input)) != null) {
            System.out.println(TimeController.cheatTime(matcher.group("date")));
        }
        else if (GameMenuCommand.Season.getMatcher(input) != null) {
            System.out.println(TimeController.season());
        }
        else if (GameMenuCommand.Weather.getMatcher(input) != null) {
            System.out.println(TimeController.weather());
        }
        else if (GameMenuCommand.WeatherForecast.getMatcher(input) != null) {
            System.out.println(TimeController.weather());
        }
        else if ((matcher = GameMenuCommand.CheatThor.getMatcher(input)) != null) {
            System.out.println(TimeController.cheatThor(matcher.group("x"), matcher.group("y")));
        }
        else if ((matcher = GameMenuCommand.CheatWeather.getMatcher(input)) != null) {
            System.out.println(TimeController.cheatWeather(matcher.group("type")));
        }
        else if (GameMenuCommand.BuildGreenhouse.getMatcher(input) != null) {
            //TODO Nafiseh
        }
        else if ((matcher = GameMenuCommand.Walk.getMatcher(input)) != null) {
            //TODO Nafiseh
        }
        else if (GameMenuCommand.PrintAllMap.isMatch(input)) {
            System.out.println(MapController.printAllMap());
        }
        else if ((matcher = GameMenuCommand.PrintMap.getMatcher(input)) != null) {
            //TODO Nafiseh
        }
        else if ((matcher = GameMenuCommand.HelpMap.getMatcher(input)) != null) {
            //TODO Nafiseh
        }

        else if (GameMenuCommand.ShowEnergy.isMatch(input)) {
            System.out.println(PlayerController.showEnergy());
        }
        else if ((matcher = GameMenuCommand.CheatEnergy.getMatcher(input)) != null) {
            System.out.println(PlayerController.cheatEnergy(matcher.group("value")));
        }
        else if (GameMenuCommand.CheatUnlimitedEnergy.isMatch(input)) {
            System.out.println(PlayerController.unlimitedEnergy());
        }
        else if (GameMenuCommand.ShowInventory.isMatch(input)) {
            System.out.println(PlayerController.showInventory());
        }
        else if ((matcher = GameMenuCommand.InventoryTrash.getMatcher(input)) != null) {
            System.out.println(PlayerController.inventoryTrash(matcher.group("itemName"), matcher.group("number")));
        }
        else if (GameMenuCommand.ShowAbility.isMatch(input)) {
            System.out.println(PlayerController.showAbility());
        }
        else if ((matcher = GameMenuCommand.EquipTool.getMatcher(input)) != null) {
            System.out.println(ToolController.equip(matcher.group("toolName")));
        }
        else if (GameMenuCommand.ShowCurrentTool.isMatch(input)) {
            System.out.println(ToolController.showCurrentTool());
        }
        else if (GameMenuCommand.ShowAvailableTool.isMatch(input)) {
            System.out.println(ToolController.showAvailableTools());
        }
        else if ((matcher = GameMenuCommand.UpgradeTool.getMatcher(input)) != null) {
            System.out.println(ToolController.upgradeTool(matcher.group("toolName")));
        }
        else if ((matcher = GameMenuCommand.UseTool.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.CropInfo.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.Plant.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.ShowPlant.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.Fertilize.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if (GameMenuCommand.ShowWater.getMatcher(input) != null) {
            //TODO Aynaz
        }
        else if (GameMenuCommand.ShowCraftingRecipes.getMatcher(input) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.Crafting.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.PlaceItem.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.CheatCrafting.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.PutRefrigerator.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.PickRefrigerator.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if (GameMenuCommand.ShowCookingRecipes.getMatcher(input) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.Cooking.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.Eat.getMatcher(input)) != null) {
            //TODO Aynaz
        }

        else if ((matcher = GameMenuCommand.Build.getMatcher(input)) != null) {
            //TODO Nafiseh
        }

        else if ((matcher = GameMenuCommand.BuyAnimal.getMatcher(input)) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.Pet.getMatcher(input)) != null) {
            //TODO Parsa
        }
        else if (GameMenuCommand.ShowAnimalsInfo.getMatcher(input) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.CheatFriendShipAnimal.getMatcher(input)) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.ShepherdAnimal.getMatcher(input)) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.FeedAnimal.getMatcher(input)) != null) {
            //TODO Parsa
        }
        else if (GameMenuCommand.ShowAnimalProduceInfo.getMatcher(input) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.CollectAnimalProduce.getMatcher(input)) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.SellAnimal.getMatcher(input)) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.Fishing.getMatcher(input)) != null) {
            //TODO Parsa
        }


        else if ((matcher = GameMenuCommand.ArtisanUse.getMatcher(input)) != null) {
            //TODO Aynaz
        }
        else if ((matcher = GameMenuCommand.ArtisanGet.getMatcher(input)) != null) {
            //TODO Aynaz
        }


        else if (GameMenuCommand.ShowShopProduct.getMatcher(input) != null) {
            //TODO Parsa
        }
        else if (GameMenuCommand.ShowShopAvailableProduct.getMatcher(input) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.Purchase.getMatcher(input)) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.CheatAddCount.getMatcher(input)) != null) {
            //TODO Parsa
        }
        else if ((matcher = GameMenuCommand.SellProduct.getMatcher(input)) != null) {
            //TODO Parsa
        }

        else if (GameMenuCommand.FriendsShipPlayerList.getMatcher(input) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.Talk.getMatcher(input)) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.TalkHistory.getMatcher(input)) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.Gift.getMatcher(input)) != null) {
            //TODO
        }
        else if (GameMenuCommand.GiftList.getMatcher(input) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.GiftRate.getMatcher(input)) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.GiftHistory.getMatcher(input)) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.Hug.getMatcher(input)) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.Flower.getMatcher(input)) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.AskMarriage.getMatcher(input)) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.RespondMarriage.getMatcher(input)) != null) {
            //TODO
        }

        else if (GameMenuCommand.TradeMenu.getMatcher(input) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.Trade.getMatcher(input)) != null) {
            //TODO
        }
        else if (GameMenuCommand.TradeLIst.getMatcher(input) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.TradeRespond.getMatcher(input)) != null) {
            //TODO
        }
        else if (GameMenuCommand.TradeHistory.getMatcher(input) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.MeetNPC.getMatcher(input)) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.GiftNPC.getMatcher(input)) != null) {
            //TODO
        }
        else if (GameMenuCommand.FriendShipNPCList.getMatcher(input) != null) {
            //TODO
        }
        else if (GameMenuCommand.QuestList.getMatcher(input) != null) {
            //TODO
        }
        else if ((matcher = GameMenuCommand.QuestFinish.getMatcher(input)) != null) {
            //TODO
        }

        else {
            System.out.println("invalid command");
        }
    }
}
