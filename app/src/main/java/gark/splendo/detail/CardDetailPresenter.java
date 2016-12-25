package gark.splendo.detail;

import gark.splendo.detail.ui.CardDetailView;
import gark.splendo.mvp.BasePresenter;
import gark.splendo.mvp.Presenter;

/**
 *
 */

public interface CardDetailPresenter extends Presenter<CardDetailView> {

    /**
     *
     */
    void toggleFavouriteCardState(final String cardId);
}
