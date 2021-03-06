package moj.rain.app.repository.repos.weather;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import moj.rain.app.network.model.weather.Weather;
import moj.rain.app.network.managers.weather.WeatherNetworkManager;

import static moj.rain.TestConstants.LATITUDE_1;
import static moj.rain.TestConstants.LONGITUDE_1;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class WeatherNetworkRepositoryImplTest {

    @Mock
    private WeatherNetworkManager networkManager;
    @Mock
    private Weather weather;

    private WeatherNetworkRepositoryImpl weatherNetworkRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        weatherNetworkRepository = new WeatherNetworkRepositoryImpl(networkManager);
    }

    @Test
    public void getWeather() throws Exception {
        givenNetworkManagerWillReturnWeather();
        whenGetWeatherIsCalled();
        thenRequestWeatherFromNetwork();
    }

    private void givenNetworkManagerWillReturnWeather() {
        given(networkManager.getWeather(anyDouble(), anyDouble())).willReturn(Observable.just(weather));
    }

    private void whenGetWeatherIsCalled() {
        weatherNetworkRepository.getWeather(LATITUDE_1, LONGITUDE_1);
    }

    private void thenRequestWeatherFromNetwork() {
        then(networkManager).should(times(1)).getWeather(LATITUDE_1, LONGITUDE_1);
        then(networkManager).shouldHaveNoMoreInteractions();
    }
}