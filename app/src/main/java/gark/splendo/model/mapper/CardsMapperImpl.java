package gark.splendo.model.mapper;

import android.support.annotation.WorkerThread;

import java.util.ArrayList;
import java.util.List;

import gark.splendo.model.Card;
import gark.splendo.model.Mechanics;

/**
 * Implementation of {@code CardsMapper}
 */

public class CardsMapperImpl implements CardsMapper {

    private final static String DEATCHRATTLE = "Deathrattle";

    @WorkerThread
    @Override
    public List<Card> mapDeathRattleCards(final List<Card> cards) {
        final List<Card> filteredList = new ArrayList<>();

        for (Card card : cards) {
            if (card.getMechanics() != null) {
                List<Mechanics> list = card.getMechanics();
                for (Mechanics item : list) {
                    if (item != null && DEATCHRATTLE.equals(item.getName())) {
                        filteredList.add(card);
                        break;
                    }
                }
            }
        }
        return filteredList;
    }
}
