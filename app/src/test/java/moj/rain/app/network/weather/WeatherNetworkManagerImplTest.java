package moj.rain.app.network.weather;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import moj.rain.BuildConfig;
import moj.rain.app.network.api.DarkSkyApi;

import static moj.rain.TestConstants.LATITUDE_1;
import static moj.rain.TestConstants.LONGITUDE_1;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class WeatherNetworkManagerImplTest {

    @Mock
    private DarkSkyApi darkSkyApi;

    private WeatherNetworkManagerImpl weatherNetworkManager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        weatherNetworkManager = new WeatherNetworkManagerImpl(darkSkyApi);
    }

    @Test
    public void getWeather() throws Exception {
        whenGetWeatherIsCalled();
        thenCallDarkSkyApi();
    }

    private void whenGetWeatherIsCalled() {
        weatherNetworkManager.getWeather(LATITUDE_1, LONGITUDE_1);
    }

    private void thenCallDarkSkyApi() {
        String key = BuildConfig.DARK_SKY_API_KEY;
        String excludes = "currently,daily,alerts,flags";
        String units = "uk2";
        then(darkSkyApi).should(times(1)).getWeather(key, LATITUDE_1, LONGITUDE_1, excludes, units);
    }
}