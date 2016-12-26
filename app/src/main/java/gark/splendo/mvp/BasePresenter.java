package gark.splendo.mvp;

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

/**
 * Basic presenter implementation.
 */
public abstract class BasePresenter<T extends PresenterView> implements Presenter<T> {

    @Nullable
    protected T mView;

    @CallSuper
    @Override
    public void onAttach(final T view) {
        mView = view;
    }

    @CallSuper
    @Override
    public void onDetach() {
        mView = null;
    }
}