package Model.Command;

public enum GameMenuCommand implements Command {
    // Game Controller: Nafiseh
    NewGame("new\\s+game\\s+-u(?:\\s+(?<username1>.+?))?(?:\\s+(?<username2>.+?))?(?:\\s+(?<username3>.+?))?"),
    ChooseMap("game\\s+map\\s+(?<mapNumber>[-+]?\\d+)"),
    LoadGame("load\\s+game"),
    ExitGame("exit\\s+game"),
    DeleteGame("delete\\s+game"),
    NextTurn("next\\s+turn"),

    // Time Controller: Nafiseh
    Time("time"),
    Date("date"),
    DateTime("datetime"),
    DayOfTheWeek ("day\\s+of\\s+the\\s+week"),
    CheatTime("cheat\\s+advance\\s+time\\s+(?<X>[-+]?\\d+)h"),
    CheatDate("cheat\\s+advance\\s+date\\s+(?<X>[-+]?\\d+)d"),

    // Time Controller: Nafiseh
    Season("season"),
    Weather("weather"),
    WeatherForecast("weather\\s+forecast"),
    CheatThor("cheat\\s+Thor\\s+-l\\s+<(?<x>\\d+)\\s*,\\s*(?<y>\\d+)>"), // todo
    CheatWeather("cheat\\s+weather\\s+set\\s+(?<Type>\\S+)"),

    // Map Controller: Nafiseh
    BuildGreenhouse("greenhouse\\s+build"), // todo

    Walk("walk\\s+-l\\s+<(?<x>\\d+)\\s*,\\s*(?<y>\\d+)>"),
    PrintAllMap("print\\s+all\\s+map"),
    PrintMap("print\\s+map\\s+-l\\s+<(?<x>\\d+)\\s*,\\s*(?<y>\\d+)>\\s+-s\\s+(?<size>\\d+)"),
    HelpMap("help\\s+reading\\s+map"),

    // Player Controller: Aynaz
    ShowEnergy("energy\\s+show"),
    CheatEnergy("energy\\s+set\\s+-v\\s+(?<value>\\d+)"),
    CheatUnlimitedEnergy("energy\\s+unlimited"),

    // Player Controller: Aynaz
    ShowInventory("inventory\\s+show"),
    InventoryTrash("inventory\\s+trash\\s+-i\\s+(?<itemName>.+?)\\s+-n\\s+(?<number>\\d+)"),
    ShowAbility("show\\s+ability"),

    // Tool Controller: Aynaz
    EquipTool("tools\\s+equip\\s+(?<toolName>.+)"),
    ShowCurrentTool("tools\\s+show\\s+current"),
    ShowAvailableTool("tools\\s+show\\s+available"),
    UpgradeTool("tools\\s+upgrade\\s+(?<toolName>.+)"),
    UseTool("tools\\s+use\\s+-d\\s+(?<direction>.+)"),

    // Plant Controller: Aynaz
    CropInfo("crop\\s+info\\s+-n\\s+(?<craftName>.+?)"),
    Plant("plant\\s+-s\\s+(?<seed>.+?)\\s+-d\\s+(?<direction>\\S+)"),
    ShowPlant("showplant\\s+-l\\s+<(?<x>\\d+)\\s*,\\s*(?<y>\\d+)>"),
    Fertilize("fertilize\\s+-f\\s+(?<fertilizer>.+?)\\s+-d\\s+(?<direction>\\S+)"),
    ShowWater("how\\s+much\\s+water"),

    // Craft Controller: Aynaz
    ShowCraftingRecipes("crafting\\s+show\\s+recipes"),
    Crafting("crafting\\s+craft\\s+(?<itemName>.+)"),
    PlaceItem("place\\s+item\\s+-n\\s+(?<itemName>.+?)\\s+-d\\s+(?<direction>\\S+)"), // todo
    CheatCrafting("cheat\\s+add\\s+item\\s+-n\\s+(?<itemName>.+?)\\s+-c\\s+(?<count>\\d+)"),

    // Food Controller: Aynaz
    PutRefrigerator("cooking\\s+refrigerator\\s+put\\s+(?<item>.+)"),
    PickRefrigerator("cooking\\s+refrigerator\\s+pick\\s+(?<item>.+)"),
    ShowCookingRecipes("cooking\\s+show\\s+recipes"),
    Cooking("cooking\\s+prepare\\s+(?<recipeName>.+)"),
    Eat("eat\\s+(?<foodName>.+)"),

    // Map Controller: Nafiseh
    Build("build\\s+-a\\s+(?<buildingName>.+?)\\s+-l\\s+<(?<x>\\d+)\\s*,\\s*(?<y>\\d+)>"),

    // Animal Controller: Parsa
    BuyAnimal("buy\\s+animal\\s+-a\\s+(?<animal>.+?)\\s+-n\\s+(?<name>.+)"),
    Pet("pet\\s+-n\\s+(?<name>.+)"),
    ShowAnimalsInfo("animals"),
    CheatFriendShipAnimal("cheat\\s+set\\s+friendship\\s+-n\\s+(?<animalName>.+?)\\s+-c\\s+(?<amount>\\d+)"),
    ShepherdAnimal("shepherd\\s+animals\\s+-n\\s+(?<animalName>.+?)\\s+-l\\s+<(?<x>\\d+)\\s*,\\s*(?<y>\\d+)>"), // todo
    FeedAnimal("feed\\s+hay\\s+-n\\s+(?<animalName>.+)"),
    ShowAnimalProduceInfo("produces"),
    CollectAnimalProduce("collect\\s+produce\\s+-n\\s+(?<name>.+)"),
    SellAnimal("sell\\s+animal\\s+-n\\s+(?<name>.+)"),

    Fishing("fishing\\s+-p\\s+(?<fishingPole>.+)"), // todo

    // Artisan Controller: Aynaz (optional)
    ArtisanUse("artisan\\s+use\\s+-a\\s+(?<artisanName>.+?)\\s+-i\\s+(?<item1Name>.+)"),
    ArtisanGet("artisan\\s+get\\s+(?<artisanName>.+)"),

    // Shop Controller: Parsa -> Nafiseh, Aynaz
    ShowShopProduct("show\\s+all\\s+products"),
    ShowShopAvailableProduct("show\\s+all\\s+available\\s+products"),
    Purchase("purchase\\s+(?<productName>.+?)\\s+-n\\s+(?<count>\\d+)"),
    CheatAddCount("cheat\\s+add\\s+(?<count>\\d+)\\s+dollars"),
    SellProduct("sell\\s+(?<productName>.+?)\\s+-n\\s+(?<count>\\d+)"),


    FriendsShipPlayerList("friendships"),
    Talk("talk\\s+-u\\s+(?<username>.+?)\\s+-m\\s+(?<message>.+)"),
    TalkHistory("talk\\s+history\\s+-u\\s+(?<username>.+)"),
    Gift("gift\\s+-u\\s+(?<username>.+)\\s+-i\\s+(?<item>.+?)\\s+-a\\s+(?<amount>\\d+)"),
    GiftList("gift\\s+list"),
    GiftRate("gift\\s+rate\\s+-i\\s+(?<giftNumber>\\d+)\\s+-r\\s+(?<rate>\\d+)"),
    GiftHistory("gift\\s+history\\s+-u\\s+(?<username>.+)"),
    Hug("hug\\s+-u\\s+(?<username>.+)"),
    Flower("flower\\s+-u\\s+(?<username>.+)"),

    AskMarriage("ask\\s+marriage\\s+-u\\s+(?<username>.+?)\\s+-r\\s+(?<ring>.+)"),
    RespondMarriage("respond\\s+(–accept|–reject)\\s+-u\\s+(?<username>.+)"),

    TradeMenu("start\\s+trade"),
    Trade("trade\\s+-u\\s+(?<username>.+?)\\s+-t\\s+(?<type>.+?)\\s+-i\\s+(?<item>.+?)\\s+-a\\s+(?<amount>\\d+)\\s+(-p\\s+(?<price>.+)|-ti\\s+(?<targetItem>.+?)\\s+-ta\\s+(?<targetAmount>.+))"),
    TradeLIst("trade\\s+list"),
    TradeRespond("trade\\s+response\\s+(–accept|–reject)\\s+-i\\s+(?<id>\\d+)"),
    TradeHistory("trade\\s+history"),

    MeetNPC("meet\\s+NPC\\s+(?<npcName>.+)"),
    GiftNPC("gift\\s+NPC\\s+(?<npcName>.+?)\\s+-i\\s+(?<item>.+)"),
    FriendShipNPCList("friendship\\s+NPC\\s+list"),
    QuestList("quests\\s+list"),
    QuestFinish("quests\\s+finish\\s+-i\\s+(?<index>\\d+)"),

    //Game Controller: Nafiseh
    CurrentMenu ("show\\s+current\\s+menu");

    private final String pattern;

    GameMenuCommand(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
