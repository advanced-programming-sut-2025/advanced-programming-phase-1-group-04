package Model.Time;

public enum Weather {
    Sunny(1),
    Rain(2),
    Storm(3),
    Snow(4);

    private final int number;

    Weather(int number) {
        this.number = number;
    }
}
