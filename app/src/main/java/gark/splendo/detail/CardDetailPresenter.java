package gark.splendo.detail;

import gark.splendo.detail.ui.CardDetailView;
import gark.splendo.mvp.BasePresenter;
import gark.splendo.mvp.Presenter;

/**
 * Interface that describes card detail screen business logic.
 */

public interface CardDetailPresenter extends Presenter<CardDetailView> {

    /**
     * Toggle card state.
     *
     * @param cardID id of selected card.
     */
    void toggleFavouriteCardState(final String cardID);
}
