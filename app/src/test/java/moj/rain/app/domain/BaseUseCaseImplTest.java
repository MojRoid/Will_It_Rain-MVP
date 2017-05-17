package moj.rain.app.domain;


import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;


public class BaseUseCaseImplTest {

    @Mock
    private Disposable disposable1;
    @Mock
    private Disposable disposable2;
    @Mock
    private CompositeDisposable compositeDisposable;

    private BaseUseCaseImpl baseUseCase;

    private BaseUseCaseImpl getSpy() {
        return spy(new BaseUseCaseImpl(compositeDisposable) {
            @Override
            public void trackDisposable(@NonNull Disposable disposable) {
                super.trackDisposable(disposable);
            }

            @Override
            public void cleanUp() {
                super.cleanUp();
            }
        });
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        baseUseCase = getSpy();
    }

    @Test
    public void trackDisposable() throws Exception {
        whenDisposablesAreTracked();
        thenDisposablesShouldBeAddedToTheCompositeDisposable();
    }

    @Test
    public void cleanUp() throws Exception {
        whenUseCasesAreCleanedUp();
        thenTheCompositeDisposableShouldBeCleared();
    }

    private void whenUseCasesAreCleanedUp() {
        baseUseCase.cleanUp();
    }

    private void whenDisposablesAreTracked() {
        baseUseCase.trackDisposable(disposable1);
        baseUseCase.trackDisposable(disposable2);
    }

    private void thenDisposablesShouldBeAddedToTheCompositeDisposable() {
        then(compositeDisposable).should(times(1)).add(disposable1);
        then(compositeDisposable).should(times(1)).add(disposable2);
        then(compositeDisposable).shouldHaveNoMoreInteractions();
    }

    private void thenTheCompositeDisposableShouldBeCleared() {
        then(compositeDisposable).should(times(1)).clear();
        then(compositeDisposable).shouldHaveNoMoreInteractions();
    }
}