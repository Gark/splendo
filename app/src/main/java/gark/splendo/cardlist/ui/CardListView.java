package gark.splendo.cardlist.ui;

import java.util.List;

import gark.splendo.mvp.PresenterView;
import gark.splendo.model.Card;

/**
 * Interface that describes card list UI behaviour.
 */
public interface CardListView extends PresenterView {

    /**
     * Notifies about changes in card list screen
     *
     * @param cards legendary cards.
     */
    void onCardsLoaded(final List<Card> cards);

    /**
     * Navigate to detail screen
     *
     * @param position selected position
     */
    void navigateToDetailScreen(final int position);
}
