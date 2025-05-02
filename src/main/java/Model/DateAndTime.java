package Model;

import Model.Enum.Season;
import Model.Enum.WeekDay;

public class DateAndTime {
    private int hour; // HH:MM
    private int day;//
    private WeekDay dayOfWeek; // Monday Tuesday Wednesday Thursday Friday Saturday Sunday
    private Season season; // Enum Season types

    private String hourToString () {
        return String.format("%02d" , hour) + ":" + "00";
    }

    private String dateToString () {
        return "";
    }

    public DateAndTime(int hour, int day, WeekDay weekDay, Season season) {
        this.hour = hour;
        this.day = day;
        this.dayOfWeek = weekDay;
        this.season = season;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public Season getSeason() {
        return season;
    }

    public WeekDay getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setDayOfWeek(WeekDay dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setSeason(int seasonNumber) {
        Season s = null;
        if (seasonNumber == 1) {s = Season.Spring;}
        else if (seasonNumber == 2) {s = Season.Summer;}
        else if (seasonNumber == 3) {s = Season.Fall;}
        else if (seasonNumber == 4) {s = Season.Winter;}
        this.season = s;
    }

}
