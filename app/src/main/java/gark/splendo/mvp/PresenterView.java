package gark.splendo.mvp;

import android.app.Activity;
import android.support.annotation.Nullable;

/**
 * Marker interface for views.
 */
public interface PresenterView {

    @Nullable
    Activity getActivity();

}