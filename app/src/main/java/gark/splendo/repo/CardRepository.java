package gark.splendo.repo;

import java.util.List;

import gark.splendo.model.Card;

/**
 * Interface that describes local storage behaviour logic.
 */

public interface CardRepository {

    /**
     * Request card list from database.
     */
    void requestCards();

    /**
     * Method that stores card list data.
     *
     * @param cards legendary cards list.
     */
    void saveCards(final List<Card> cards);

    /**
     * Toggle selected card as favorite.
     *
     * @param cardId card id.
     */
    void toggleFavouriteState(String cardId);
}
