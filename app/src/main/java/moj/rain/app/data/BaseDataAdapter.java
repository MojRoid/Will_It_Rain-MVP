package moj.rain.app.data;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public abstract class BaseDataAdapter<SOURCE, DESTINATION> {

    public interface Callback<DESTINATION> {

        void onDataAdapted(DESTINATION data);

        void onDataAdaptError(Throwable throwable);
    }

    private final Scheduler computationScheduler;
    private final Scheduler mainThreadScheduler;
    private Disposable disposable;

    public BaseDataAdapter(Scheduler computationScheduler, Scheduler mainThreadScheduler) {
        this.computationScheduler = computationScheduler;
        this.mainThreadScheduler = mainThreadScheduler;
    }

    protected boolean isValid(DESTINATION destination) {
        return destination != null;
    }

    @Nullable
    protected abstract DESTINATION transform(@Nullable SOURCE source);

    public void transform(@Nullable SOURCE source, @NonNull Callback<DESTINATION> callback) {
        if (source == null) {
            Timber.w("No data to adapt.");
            return;
        }

        disposable = Observable.just(source)
                .map(this::transform)
                .filter(this::isValid)
                .subscribeOn(computationScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(callback::onDataAdapted, callback::onDataAdaptError);
    }

    public void cancel() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}