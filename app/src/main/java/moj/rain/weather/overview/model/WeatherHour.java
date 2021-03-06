package moj.rain.weather.overview.model;

import com.google.auto.value.AutoValue;

import org.joda.time.DateTime;

@AutoValue
public abstract class WeatherHour {

    public abstract DateTime getHour();

    public abstract String getIcon();

    public abstract int getPrecipIntensity();

    public abstract int getPrecipProbability();

    public abstract int getTemperature();

    public static Builder builder() {
        return new AutoValue_WeatherHour.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder setHour(DateTime hour);

        public abstract Builder setIcon(String icon);

        public abstract Builder setPrecipIntensity(int precipIntensity);

        public abstract Builder setPrecipProbability(int precipProbability);

        public abstract Builder setTemperature(int temperature);

        public abstract WeatherHour build();
    }
}
