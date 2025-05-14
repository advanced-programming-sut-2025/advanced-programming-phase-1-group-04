package Model.Map;


public class FarmBuilding {
    private final FarmBuildingType type;

    public FarmBuilding (FarmBuildingType type) {
        this.type = type;
    }

    public FarmBuildingType getType() {
        return type;
    }


    public int getCapacity() {
        return type.getCapacity();
    }

    public int getW() {
        return type.getW();
    }

    public int getL() {
        return type.getL();
    }

    public String getName() {
        return type.getName();
    }
}
