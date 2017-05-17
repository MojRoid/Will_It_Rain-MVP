package moj.rain.app.network.parsing;


import android.support.annotation.NonNull;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
public abstract class AutoValueGsonFactory implements TypeAdapterFactory {

    @NonNull
    public static TypeAdapterFactory create() {
        return new AutoValueGson_AutoValueGsonFactory();
    }
}