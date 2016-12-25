package gark.splendo.cardlist;

import gark.splendo.cardlist.ui.CardListView;
import gark.splendo.mvp.Presenter;

/**
 * Interface that describes legendary card list screen business logic.
 */
public interface CardListPresenter extends Presenter<CardListView> {

    /**
     * Request available cards from inner architecture level.
     */
    void requestCards();

    /**
     * Navigate to the detail screen.
     *
     * @param position position of selected legendary card.
     */
    void openDetailScreen(final int position);
}
