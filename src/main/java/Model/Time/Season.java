package Model.Time;

public enum Season {
    Spring(1),
    Summer(2),
    Fall(3),
    Winter(4);

    private int number;

    Season (int num) {number = num;}

    public int getNumber () {return number;}
}
