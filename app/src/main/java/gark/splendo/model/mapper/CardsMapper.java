package gark.splendo.model.mapper;

import java.util.List;

import gark.splendo.model.Card;

/**
 * Interface that describes cards mapper behaviour logic.
 */

public interface CardsMapper {

    /**
     * filter all cards Deathrattle type
     *
     * @param cards all legendary cards.
     * @return filtered cards by Deathrattle type.
     */
    List<Card> mapDeathRattleCards(final List<Card> cards);

}
