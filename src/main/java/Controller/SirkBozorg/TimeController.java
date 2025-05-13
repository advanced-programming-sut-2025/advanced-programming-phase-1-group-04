package Controller.SirkBozorg;

import Model.App;
import Model.Result;
import Model.Time.Weather;

public class TimeController {
    public static Result time(String command) {
        String time = String.format("Time: %02d:00", App.getCurrentGame().getCurrentTime().getHour());
        String day = "Date: " + App.getCurrentGame().getCurrentTime().getDay();
        return switch (command) {
            case "time" -> new Result(true, time);
            case "date" -> new Result(true, day);
            case "datetime" -> new Result(true, time + ", " + day);
            case "day of the week" ->
                new Result(true, "Day of the week: " + App.getCurrentGame().getCurrentTime().getDayOfWeek());
            default -> new Result(true, "tekh");
        };

    }

    public static Result cheatTime(String stringTime) {
        int time = Integer.parseInt(stringTime);
        if (time < 1) {
            return new Result(false, "You can't turn back the clock. Sorry");
        } else if (App.getCurrentGame().getCurrentTime().getHour() + time > 23) {
            return new Result(false, "Time should be between 9-24");
        }

        // TODO: night controller
        App.getCurrentGame().getCurrentTime().addHour(time);
        return new Result(true, "Time successfully changed to " + App.getCurrentGame().getCurrentTime().getHour() + ":00");
    }

    public static Result cheatDate(String stringDate) {
        int date = Integer.parseInt(stringDate);
        if (date < 1) {
            return new Result(false, "Date should be greater than 1");
        }
        // TODO: night controller
        App.getCurrentGame().getCurrentTime().addDay(date);
        return new Result(true, "Date successfully changed to " + App.getCurrentGame().getCurrentTime().getDay());
    }

    public static Result season() {
        return new Result(true, App.getCurrentGame().getCurrentTime().getSeason().name());
    }

    public static Result weather() {
        return new Result(true, App.getCurrentGame().getCurrentTime().getWeather().name());
    }

    public static Result weatherForecast() {
        return new Result(true, App.getCurrentGame().getTomorrowWeather().name());
    }

    public static Result cheatThor(String stringX, String stringY) {
        int x = Integer.parseInt(stringX);
        int y = Integer.parseInt(stringY);
        if ((x < 0 || x >= 90) || (y < 0 || y >= 120)) {
            return new Result(false, "Mashti x,y bein (0,0) - (89, 119)");
        }

        //TODO: night controller
        return new Result(true, "Tomorrow you see impact of thor in (" + x + ", " + y + ")");
    }

    public static Result cheatWeather(String type) {
        Weather weather = pharseWeather(type);
        if (weather == null) {
            return new Result(false, "Type is invalid!");
        } else if (!App.getCurrentGame().getCurrentTime().getSeason().getWeathers().contains(weather)) {
            return new Result(false, "You can't use this weather in your season!" +
                "\nValid weathers: " + App.getCurrentGame().getCurrentTime().getSeason().getWeathers());
        }

        App.getCurrentGame().setTomorrowWeather(weather);
        return new Result(true, "Tomorrow weather successfully changed to " + type);
    }

    private static Weather pharseWeather(String input) {
        for (Weather weather : Weather.values()) {
            if (weather.name().equalsIgnoreCase(input)) {
                return weather;
            }
        }
        return null;
    }
}
