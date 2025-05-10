package Model.Command;

public enum GameMenuCommand implements Command {
    // Game Controller: Nafiseh
    NewGame("game new -u <username_1> <username_2> <username_3>"),
    ChooseMap("game map <map_number>"),
    LoadGame("load game"),
    ExitGame("exit game"),
    DeleteGame("delete game"),
    NextTurn("next turn"),

    // Time Controller: Nafiseh
    Time("time|date|datetime|day of the week"),
    CheatTime("cheat advance time <X>h"),
    CheatDate("cheat advance date <X>d"),

    // Time Controller: Nafiseh
    Weather("season|weather|weather forecast"),
    CheatThor("cheat Thor -l <x , y>"), // todo
    cheatWeather("cheat weather set <Type>"),

    // Map Controller: Nafiseh
    BuildGreenhouse("greenhouse build"), // todo

    Walk("walk -l <x, y>"),
    PrintMap("print map -l <x, y> -s <size>"),
    HelpMap("help reading map"),

    // Player Controller: Aynaz
    ShowEnergy("show energy"),
    CheatEnergy("energy set -v <value>"),
    CheatUnlimitedEnergy("energy unlimited"),

    // Player Controller: Aynaz
    ShowInventory("show inventory"),
    InventoryTrash("inventory trash -i <item’s name> -n <number>"),
    ShowAbility("show ability"),

    // Tool Controller: Aynaz
    EquipTool("tools equip <tool_name>"),
    ShowCurrentTool("tools show current"),
    ShowAvailableTool("tools show available"),
    UpgradeTool("tools upgrade <tool_name>"),
    UseTool("tools use -d <direction>"), // todo

    // Plant Controller: Aynaz
    CropInfo("crop info -n <craft_name>"),
    Plant("plant -s <seed> -d <direction>"),
    ShowPlant("showplant -l <x, y>"),
    Fertilize("fertilize -f <fertilizer> -d <direction>"),
    ShowWater("how much water"),

    // Craft Controller: Aynaz
    ShowCraftingRecipes("crafting show recipes"),
    Crafting("crafting craft <item_name>"),
    PlaceItem("place item -n <item_name> -d <direction>"), // todo
    CheatCrafting("cheat add item -n <item_name> -c <count>"),

    // Food Controller: Aynaz
    Refrigerator("cooking refrigerator [put/pick] <item>"),
    ShowCookingRecipes("cooking show recipes"),
    Cooking("cooking prepare <recipe_name>"),
    Eat("eat <food_name>"),

    // Map Controller: Nafiseh
    Build("build -a <building_name> -l <x , y>"),

    // Animal Controller: Parsa
    BuyAnimal("buy animal -a <animal> -n <name>"),
    Pet("pet -n <name>"),
    ShowAnimalInfo("animals"),
    CheatFriendShipAnimal("cheat set friendship -n <animal name> -c <amount>"),
    ShepherdAnimal("shepherd animals -n <animal name> -l <x , y>"), // todo
    FeedAnimal("feed hay -n <animal name>"),
    ShowAnimalProduceInfo("produces"),
    CollectAnimalProduce("collect produce -n <name>"),
    SellAnimal("sell animal -n <name>"),

    Fishing("fishing -p <fishing pole>"), // todo

    // Artisan Controller: Aynaz (optional)
    ArtisanUse("artisan use <artisan_name> <item1_name>"),
    ArtisanGet("artisan get <artisan_name>"),

    // Shop Controller: Parsa -> Nafiseh, Aynaz
    ShowShopProduct("show all products"),
    ShowShopAvailableProduct("show all available products"),
    Purchase("purchase <product_name> -n <count>"),
    CheatAddCount("cheat add <count> dollars"),
    SellProduct("sell <product_name> -n <count>"),


    FriendsShipPlayerList("friendships"),
    Talk("talk -u <username> -m <message>"),
    TalkHistory("talk history -u <username>"),
    Gift("gift -u <username> -i <item> -a <amount>"),
    GiftList("gift list"),
    GiftRate("gift rate -i <gift-number> -r <rate>"),
    GiftHistory("gift history -u <username>"),
    Hug("hug -u <username>"),
    Flower("flower -u <username>"),

    AskMarriage("ask marriage -u <username> -r <ring>"),
    RespondMarriage("respond (–accept | –reject) -u <username>"),

    TradeMenu("start trade"),
    Trade("trade -u <username> -t <type> -i <item> -a <amount> [-p <price>] [-ti <targetItem> -ta <targetAmount>]"),
    TradeLIst("trade list"),
    TradeRespond("trade response (–accept | –reject) -i <id>"),
    TradeHistory("trade history"),

    MeetNPC("meet NPC <npc_name>"),
    GiftNPC("gift NPC <npc_name> -i <item>"),
    FriendShipNPCList("friendship NPC list"),
    QuestList("quests list"),
    QuestFinish("quests finish -i <index>"),

    CurrentMenu ("show current menu");

    private final String pattern;

    GameMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
