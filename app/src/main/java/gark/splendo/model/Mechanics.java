package gark.splendo.model;

import android.support.annotation.VisibleForTesting;

import com.google.gson.annotations.SerializedName;

public class Mechanics {
    @SerializedName("name")
    private String mName;

    public Mechanics() {

    }

    @VisibleForTesting
    public Mechanics(final String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }
}
