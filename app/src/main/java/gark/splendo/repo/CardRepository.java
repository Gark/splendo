package gark.splendo.repo;

import java.util.List;

import gark.splendo.model.Card;

/**
 * Java Doc Here
 */

public interface CardRepository {

    void requestCards();

    void saveCards(final List<Card> cards);

    void toggleFavouriteState(String cardId);
}
