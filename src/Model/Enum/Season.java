package Model.Enum;

import java.util.Arrays;
import java.util.List;

enum Weather {
    Sunny,
    Rain,
    Storm,
    Snow;
}

public enum Season {
    Spring (Arrays.asList(Weather.Sunny, Weather.Rain, Weather.Storm), Arrays.asList()),
    Summer (Arrays.asList(Weather.Sunny, Weather.Rain, Weather.Storm), Arrays.asList()),
    Fall (Arrays.asList(Weather.Sunny, Weather.Rain, Weather.Storm), Arrays.asList()),
    Winter (List.of(Weather.Snow), Arrays.asList());

    private final List<Weather> weathers;
    private final List<String> suitableCrops;

    Season(List<Weather> weathers, List<String> suitableCrops) {
        this.weathers = weathers;
        this.suitableCrops = suitableCrops;
    }
}
