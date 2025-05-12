package Model.Time;

public class DateAndTime {
    private int hour; // (9-24):00
    private int day; // 1-28
    private Weather weather;

    public DateAndTime(int hour, int day, Weather weather) {
        this.hour = hour;
        this.day = day;
        this.weather = weather;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public Weather getWeather() {
        return weather;
    }

    public Season getSeason() {
        int s = this.day % 28;
        return Season.values()[s];
    }

    public WeekDay getDayOfWeek() {
        int w = this.day % 7;
        return WeekDay.values()[w];
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
