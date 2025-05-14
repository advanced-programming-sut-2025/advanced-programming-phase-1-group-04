package Model.Map;

public class Stone implements Item{
    @Override
    public String getName() {
        return "Stone";
    }

    @Override
    public int getPrice() {
        return 10;
    }
}
