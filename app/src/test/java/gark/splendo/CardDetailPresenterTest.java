package gark.splendo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import gark.splendo.detail.CardDetailPresenter;
import gark.splendo.detail.CardDetailPresenterImpl;
import gark.splendo.detail.ui.CardDetailView;
import gark.splendo.model.Card;
import gark.splendo.repo.CardRepository;
import gark.splendo.repo.RepositoryCallback;

import static org.mockito.Mockito.verify;

/**
 * Covers the test scenarios for class {@link CardDetailPresenter}
 */

@RunWith(MockitoJUnitRunner.class)
public class CardDetailPresenterTest {

    @Mock
    private CardRepository mCardRepository;
    @Mock
    private CardDetailView mCardDetailView;

    private CardDetailPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new CardDetailPresenterImpl(mCardRepository);
        mPresenter.onAttach(mCardDetailView);
    }

    /**
     * action: {@code CardDetailPresenter} init
     * assert: Callback is set.
     * assert: request card list from local storage executed.
     */
    @Test
    public void testOnCardDetailInitialization() {
        verify(mCardRepository).setCallback((RepositoryCallback) mPresenter);
        verify(mCardRepository).requestCards();
    }

    /**
     * action: toggle selected card as favorite
     * assert: {@code CardRepository} calls "toggleFavouriteState" method.
     */
    @Test
    public void testToggleSelectedCardAsFavorite() {
        mPresenter.toggleFavouriteCardState("card_id");
        verify(mCardRepository).toggleFavouriteState("card_id");
    }

    /**
     * action: onCardListChanged callback triggered
     * assert: onCardsLoaded method from view called.
     */
    @Test
    public void testOnCardsLoadedCallbackCalled() {
        RepositoryCallback callback = (RepositoryCallback) mPresenter;

        ArrayList<Card> list = new ArrayList<>();
        list.add(new Card());

        callback.onCardListChanged(list);
        verify(mCardDetailView).onCardsLoaded(list);
    }
}