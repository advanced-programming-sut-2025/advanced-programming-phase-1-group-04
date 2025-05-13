package Model.Time;

public class DateAndTime {
    private int hour; // (9-24):00
    private int day;
    private Weather weather;

    public DateAndTime(int hour, int day, Weather weather) {
        this.hour = hour;
        this.day = day;
        this.weather = weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void addHour(int hour) {
        this.hour += hour;
    }

    public void addDay(int day) {
        this.day += day;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Weather getWeather() {
        return weather;
    }

    public Season getSeason() {
        int s = ((this.day - 1) / 28) % 4;
        return Season.values()[s];
    }

    public WeekDay getDayOfWeek() {
        int w = (this.day - 1) % 7;
        return WeekDay.values()[w];
    }
}
