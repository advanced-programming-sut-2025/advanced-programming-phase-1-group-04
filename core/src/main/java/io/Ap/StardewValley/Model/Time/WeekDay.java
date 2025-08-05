package io.Ap.StardewValley.Model.Time;

public enum WeekDay {
    Saturday("Sat."),
    Sunday("Sun."),
    Monday("Mon."),
    Tuesday("Tue."),
    Wednesday("Wed."),
    Thursday("Thu."),
    Friday("Fri.");

    private final String abbreviation;

    WeekDay(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
