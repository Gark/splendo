package gark.splendo;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import gark.splendo.detail.CardDetailPresenter;
import gark.splendo.detail.CardDetailPresenterImpl;
import gark.splendo.detail.ui.CardDetailView;
import gark.splendo.model.Card;
import gark.splendo.model.Mechanics;
import gark.splendo.model.mapper.CardsMapper;
import gark.splendo.model.mapper.CardsMapperImpl;
import gark.splendo.repo.CardRepository;
import gark.splendo.repo.RepositoryCallback;

import static org.mockito.Mockito.verify;

/**
 * Covers the test scenarios for class {@link CardsMapper}
 */

@RunWith(MockitoJUnitRunner.class)
public class CardMapperTest {

    private CardsMapper mMapper;

    @Before
    public void setUp() {
        mMapper = new CardsMapperImpl();
    }

    @Test
    public void testNoCardMatches() {
        ArrayList<Card> cardList = initCardList("another_type");

        List<Card> resultList = mMapper.mapDeathRattleCards(cardList);
        Assert.assertEquals(0, resultList.size());
    }

    @Test
    public void testOneCardMatches() {
        ArrayList<Card> cardList = initCardList("Deathrattle");

        List<Card> resultList = mMapper.mapDeathRattleCards(cardList);
        Assert.assertEquals(1, resultList.size());
    }

    @NonNull
    private ArrayList<Card> initCardList(final String type) {
        Mechanics mechanics = new Mechanics(type);
        ArrayList<Mechanics> mechanicList = new ArrayList<>();
        mechanicList.add(mechanics);


        Card card = new Card();
        card.setMechanics(mechanicList);
        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(card);

        return cardList;
    }
}