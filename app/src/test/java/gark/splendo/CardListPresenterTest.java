package gark.splendo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import gark.splendo.cardlist.CardListPresenter;
import gark.splendo.cardlist.CardListPresenterImpl;
import gark.splendo.cardlist.ui.CardListView;
import gark.splendo.model.Card;
import gark.splendo.network.NetworkManager;
import gark.splendo.repo.CardRepository;
import gark.splendo.repo.RepositoryCallback;

import static org.mockito.Mockito.verify;

/**
 * Covers the test scenarios for class {@link gark.splendo.cardlist.CardListPresenter}
 */

@RunWith(MockitoJUnitRunner.class)
public class CardListPresenterTest {

    @Mock
    private CardRepository mCardRepository;
    @Mock
    private NetworkManager mNetworkManager;
    @Mock
    private CardListView mView;

    private CardListPresenter mPresenter;

    @Before
    public void setUp() {
        mPresenter = new CardListPresenterImpl(mCardRepository, mNetworkManager);
        mPresenter.onAttach(mView);
    }

    /**
     * action: {@code CardDetailPresenter} init
     * assert: Callback is set.
     * assert: request card list from local storage executed.
     */
    @Test
    public void testOnCardListPresenterInitialization() {
        verify(mCardRepository).setCallback((RepositoryCallback) mPresenter);
        verify(mCardRepository).requestCards();
    }

    /**
     * action: request card.
     * assert: {@link NetworkManager} "requestCards" is been executed.
     */
    @Test
    public void testRequestCardFromNetwork() {
        mPresenter.requestCards();
        verify(mNetworkManager).requestCards();
    }

    /**
     * action: onCardListChanged callback triggered
     * assert: onCardsLoaded method from view called.
     */
    @Test
    public void testOnDataFromLocalStorageCome() {
        RepositoryCallback callback = ((RepositoryCallback) mPresenter);

        ArrayList<Card> list = new ArrayList<>();
        list.add(new Card());
        callback.onCardListChanged(list);

        verify(mView).onCardsLoaded(list);
    }

    /**
     * action: Card item selected.
     * assert: view navigates to detail screen.
     */
    @Test
    public void testOnCardItemPressed() {
        mPresenter.openDetailScreen(27);
        verify(mView).navigateToDetailScreen(27);
    }
}