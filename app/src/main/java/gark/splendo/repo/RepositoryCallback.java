package gark.splendo.repo;

import java.util.List;

import gark.splendo.model.Card;

/**
 * Interface that notifies presenter with repository changes.
 */
public interface RepositoryCallback {

    /**
     * Notify presenter with new cards list.
     *
     * @param list list of new cards.
     */
    void onCardListChanged(final List<Card> list);
}
