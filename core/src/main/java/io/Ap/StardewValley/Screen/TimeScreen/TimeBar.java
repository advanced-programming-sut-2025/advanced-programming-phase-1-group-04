package io.Ap.StardewValley.Screen.TimeScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import io.Ap.StardewValley.Model.App;
import io.Ap.StardewValley.Model.Time.DateAndTime;
import io.Ap.StardewValley.Model.Time.Season;
import io.Ap.StardewValley.Model.Time.Weather;
import io.Ap.StardewValley.StardewValley;

import java.util.EnumMap;
import java.util.Map;


public class TimeBar {
    private final Map<Weather, Image> weathersImage = new EnumMap<>(Weather.class);
    private final Map<Season, Image> seasonsImage = new EnumMap<>(Season.class);


    private final Group group;

    private final Label timeLabel;
    private final Label dateLabel;
    private Image currentSeason;
    private Image currentWeather;

    {
        for (Weather weather : Weather.values()) {
            Image image = new Image(new Texture(Gdx.files.internal( "time/" + weather.name() + ".png")));
            weathersImage.put(weather, image);
        }

        for (Season season : Season.values()) {
            Image image = new Image(new Texture(Gdx.files.internal("time/" + season.name() + ".png")));
            seasonsImage.put(season, image);
        }
    }

    public TimeBar() {
        Skin skin = StardewValley.getSkin();
        group = new Group();

        float scale  = 1.2f;
        DateAndTime time = App.getGame().getCurrentTime();

        Image background = new Image(new Texture("time/timeBar.png"));
        background.setSize(background.getWidth() * scale, background.getHeight() * scale);

        group.addActor(background);

        // time:
        timeLabel = new Label(time.getFormattedTime(), skin);
        timeLabel.setPosition(185 , 110);
        group.addActor(timeLabel);

        // date:
        dateLabel = new Label(time.getDayOfWeek().getAbbreviation() + " " + time.getDay(), skin);
        dateLabel.setPosition(185, 220);
        group.addActor(dateLabel);

        // season:
        currentSeason = seasonsImage.get(time.getSeason());
        currentSeason.setSize(currentSeason.getWidth() * scale, currentSeason.getHeight() * scale);
        currentSeason.setPosition(139, 168);
        group.addActor(currentSeason);

        // weather
        currentWeather = weathersImage.get(time.getWeather());
        currentWeather.setSize(currentWeather.getWidth() * scale, currentWeather.getHeight() * scale);
        currentWeather.setPosition(254, 168);
        group.addActor(currentWeather);

        group.setSize(background.getWidth(), background.getHeight());
    }

    public void updateTime() {
        DateAndTime time = App.getGame().getCurrentTime();
        timeLabel.setText(time.getFormattedTime());
        dateLabel.setText(time.getDayOfWeek().getAbbreviation() + " " + time.getDay());
    }

    public void updateSeason(Season season) {
        currentSeason.setDrawable(seasonsImage.get(season).getDrawable());
    }

    public void updateWeather() {
        currentWeather.setDrawable(weathersImage.get(App.getGame().getCurrentTime().getWeather()).getDrawable());
    }

    public Group getGroup() {
        return group;
    }
}

